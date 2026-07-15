package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.Producto;
import views.Frm_CatalogoProductos;
import views.Frm_Principal;

public class ProductoService {

    private Frm_Principal vista;
    private ProductoRepository dao;
    private String rutaImagenSeleccionada = "";

    public ProductoService(Frm_Principal vista, ProductoRepository dao) {
        this.vista = vista;
        this.dao = dao;
        iniciarEventos();
        generarID();
        vista.txt_idproducto.setEnabled(false);
        vista.txt_codproducto.setEnabled(false);
        vista.btn_imagen.addActionListener(e -> seleccionarImagen());
        cargarTabla();
    }

    private void iniciarEventos() {
        vista.btn_guardar.addActionListener(e -> guardarProducto());
        vista.btn_modificar.addActionListener(e -> modificarProducto());
        vista.btn_eliminar.addActionListener(e -> eliminarProducto());
        vista.txt_IDprodbuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTablaProductos();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTablaProductos();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTablaProductos();
            }
        });
        vista.tbl_productos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cargarProductoDesdeTabla();
            }
        });
        vista.txt_precioVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // ✅ permite solo números y un punto decimal
                if (!Character.isDigit(c) && c != '.') {
                    e.consume(); // bloquea la tecla
                }

                // ✅ evita más de un punto decimal
                if (c == '.' && vista.txt_precioVenta.getText().contains(".")) {
                    e.consume();
                }
            }
        });

    }

    private boolean validarSoloLetras(String texto) {
        return texto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$");
    }

    private void seleccionarImagen() {
        JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Imágenes (JPG, PNG)", "jpg", "jpeg", "png"));
    int resultado = chooser.showOpenDialog(vista);

    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivoSeleccionado = chooser.getSelectedFile();
        String nombreArchivo = archivoSeleccionado.getName().replaceAll("\\s+", "_"); // ✅ normalizamos

        
        File destino = new File("src/recursos/productos_imagenes/" + nombreArchivo);

        try {
            Files.copy(archivoSeleccionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            rutaImagenSeleccionada = nombreArchivo; 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al copiar la imagen. Verifique permisos o nombre de archivo.");
        }
    }
        
    }

    private boolean validarDatos() {
        String nombre = vista.cmb_nomproducto.getSelectedItem().toString();
        String empaque = vista.cmb_tpempaque.getSelectedItem().toString();
        String tamano = vista.cmb_tmproducto.getSelectedItem().toString();
        String precio = vista.txt_precioVenta.getText().trim();

        int stock = (int) vista.spn_stock.getValue();

        if (nombre.equals("Seleccione")) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un producto válido");
            return false;
        }
        if (empaque.equals("Seleccione")) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un tipo de empaque válido");
            return false;
        }
        if (tamano.equals("Seleccione")) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un tamaño válido");
            return false;
        }
        try {
            double p = Double.parseDouble(precio);
            if (p <= 0) {
                JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido y mayor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El precio debe contener solo números");
            return false;
        }

        return true;
    }

    private void guardarProducto() {
        if (!validarDatos()) {
            return;
        }
        String estado = vista.btn_disponible.isSelected() ? "Disponible" : "No Disponible";
        Producto p = new Producto(
                Integer.parseInt(vista.txt_idproducto.getText()),
                vista.txt_codproducto.getText(),
                vista.cmb_nomproducto.getSelectedItem().toString(),
                vista.cmb_tpempaque.getSelectedItem().toString(),
                vista.cmb_tmproducto.getSelectedItem().toString(),
                Double.parseDouble(vista.txt_precioVenta.getText().trim()),
                (int) vista.spn_stock.getValue(),
                estado, rutaImagenSeleccionada
        );

        if (dao.buscarPorCodigo(p.getCodigoProducto()) != null) {
            JOptionPane.showMessageDialog(vista, "Ya exite un producto con ese Codigo");
            return;
        }
        if (dao.guardarProducto(p)) {
            JOptionPane.showMessageDialog(vista, "Producto guardado");
            vista.tbl_productos.setEnabled(false);
            cargarTabla();
            vista.tbl_productos.setEnabled(true);
            vista.tbl_productos.clearSelection();
            limpiarCampos();
            generarID();
        }
    }

    private void modificarProducto() {
        if (vista.txt_codproducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No es posible modificar sin cargar los datos del producto");
            return;
        }
        if (!validarDatos()) {
            return;
        }
        String estado = vista.btn_disponible.isSelected() ? "Disponible" : "No Disponible";
        String rutaActual = dao.buscarPorCodigo(vista.txt_codproducto.getText()).getRutaImagen();
        String ruta = rutaImagenSeleccionada.isEmpty() ? rutaActual : rutaImagenSeleccionada;
        Producto p = new Producto(
                Integer.parseInt(vista.txt_idproducto.getText()),
                vista.txt_codproducto.getText(),
                vista.cmb_nomproducto.getSelectedItem().toString(),
                vista.cmb_tpempaque.getSelectedItem().toString(),
                vista.cmb_tmproducto.getSelectedItem().toString(),
                Double.parseDouble(vista.txt_precioVenta.getText().trim()),
                (int) vista.spn_stock.getValue(),
                estado, ruta
        );
        if (dao.modificarProducto(p)) {
            JOptionPane.showMessageDialog(vista, "Producto modificado");
            vista.tbl_productos.setEnabled(false);
            cargarTabla();
            vista.tbl_productos.setEnabled(true);
            vista.tbl_productos.clearSelection();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado");
        }
    }

    private void eliminarProducto() {
        String codigo = vista.txt_codproducto.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Primero busque un producto");
            return;
        }
        int r = JOptionPane.showConfirmDialog(vista, "¿Eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }
        if (dao.eliminarProducto(codigo)) {
            JOptionPane.showMessageDialog(vista, "Producto eliminado");
            vista.tbl_productos.setEnabled(false);
            cargarTabla();
            vista.tbl_productos.setEnabled(true);
            vista.tbl_productos.clearSelection();
            limpiarCampos();
            generarID();
        } else {

            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
        }
    }

    private void filtrarTablaProductos() {
        String texto = vista.txt_IDprodbuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) vista.tbl_productos.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_productos.setRowSorter(sorter);

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            limpiarCampos();
            vista.tbl_productos.clearSelection();
            generarID();
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2));
        }
    }

    private void cargarProductoDesdeTabla() {
        int filaVista = vista.tbl_productos.getSelectedRow();
        if (filaVista == -1) {
            return;
        }

        int filaModelo = vista.tbl_productos.convertRowIndexToModel(filaVista);
        String codigo = vista.tbl_productos.getModel().getValueAt(filaModelo, 1).toString();

        Producto p = dao.buscarPorCodigo(codigo);
        if (p != null) {
            vista.txt_idproducto.setText(String.valueOf(p.getIdProducto()));
            vista.txt_codproducto.setText(p.getCodigoProducto());
            vista.cmb_nomproducto.setSelectedItem(p.getNombreProducto());
            vista.cmb_tpempaque.setSelectedItem(p.getTipoEmpaque());
            vista.cmb_tmproducto.setSelectedItem(p.getTamanoProducto());
            vista.txt_precioVenta.setText(String.valueOf(p.getPrecioVenta()));
            vista.spn_stock.setValue(p.getStockProducto());
            rutaImagenSeleccionada = p.getRutaImagen();

            if (p.getEstadoProducto().equals("Disponible")) {
                vista.btn_disponible.setSelected(true);
            } else {
                vista.btn_nodisponible.setSelected(true);
            }
        }
    }

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Empaque");
        modelo.addColumn("Tamaño");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Estado");
        for (Producto p : dao.listarProductos()) {
            modelo.addRow(new Object[]{
                p.getIdProducto(), p.getCodigoProducto(), p.getNombreProducto(),
                p.getTipoEmpaque(), p.getTamanoProducto(), p.getPrecioVenta(),
                p.getStockProducto(), p.getEstadoProducto()
            });
        }
        vista.tbl_productos.setModel(modelo);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_productos.setRowSorter(sorter);
        vista.tbl_productos.clearSelection();
    }

    private void limpiarCampos() {
        vista.cmb_nomproducto.setSelectedIndex(0);
        vista.cmb_tpempaque.setSelectedIndex(0);
        vista.cmb_tmproducto.setSelectedIndex(0);

        vista.txt_precioVenta.setText("");
        vista.spn_stock.setValue(0);
        vista.txt_IDprodbuscar.setText("");
        rutaImagenSeleccionada = "";

        vista.btn_disponible.setSelected(false);
        vista.btn_nodisponible.setSelected(false);
    }

    private void generarID() {
        int nuevoID = dao.generarNuevoID();
        String codigo = "PROD-" + nuevoID;
        vista.txt_idproducto.setEnabled(true);
        vista.txt_idproducto.setText(String.valueOf(nuevoID));
        vista.txt_idproducto.setEnabled(false);
        vista.txt_codproducto.setEnabled(true);
        vista.txt_codproducto.setText(codigo);
        vista.txt_codproducto.setEnabled(false);
        limpiarCampos();
        vista.tbl_productos.clearSelection();
    }

}
