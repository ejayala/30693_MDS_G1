package controllers;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Producto;
import views.Frm_Principal;

public class ProductoService {

    private Frm_Principal vista;
    private ProductoRepository dao;

    public ProductoService(Frm_Principal vista, ProductoRepository dao) {
        this.vista = vista;
        this.dao = dao;
        iniciarEventos();
        generarID();
        vista.txt_idproducto.setEnabled(false);
        vista.txt_codproducto.setEnabled(false);
        cargarTabla();
    }

    private void iniciarEventos() {
        vista.btn_guardar.addActionListener(e -> guardarProducto());
        vista.btn_modificar.addActionListener(e -> modificarProducto());
        vista.btn_eliminar.addActionListener(e -> eliminarProducto());
        vista.btn_buscar.addActionListener(e -> buscarProducto());
    }

    private boolean validarSoloLetras(String texto) {
        return texto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$");
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
        if (stock < 0) {
            JOptionPane.showMessageDialog(vista, "Stock no puede ser negativo");
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
                estado
        );

        if (dao.buscarPorCodigo(p.getCodigoProducto()) != null) {
            JOptionPane.showMessageDialog(vista, "Ya exite un producto con ese Codigo");
            return;
        }
        if (dao.guardarProducto(p)) {
            JOptionPane.showMessageDialog(vista, "Producto guardado");
            cargarTabla();
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
        Producto p = new Producto(
                Integer.parseInt(vista.txt_idproducto.getText()),
                vista.txt_codproducto.getText(),
                vista.cmb_nomproducto.getSelectedItem().toString(),
                vista.cmb_tpempaque.getSelectedItem().toString(),
                vista.cmb_tmproducto.getSelectedItem().toString(),
                Double.parseDouble(vista.txt_precioVenta.getText().trim()),
                (int) vista.spn_stock.getValue(),
                estado
        );
        if (dao.modificarProducto(p)) {
            JOptionPane.showMessageDialog(vista, "Producto modificado");
            cargarTabla();
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
            cargarTabla();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
        }
    }

    private void buscarProducto() {
        String codigo = vista.txt_IDprodbuscar.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un código para buscar");
            return;
        }
        Producto p = dao.buscarPorCodigo(codigo);
        if (p == null) {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado");
            return;
        }
        vista.txt_idproducto.setText(String.valueOf(p.getIdProducto()));
        vista.txt_codproducto.setText(p.getCodigoProducto());
        vista.cmb_nomproducto.setSelectedItem(p.getNombreProducto());
        vista.cmb_tpempaque.setSelectedItem(p.getTipoEmpaque());
        vista.cmb_tmproducto.setSelectedItem(p.getTamanoProducto());
        vista.txt_precioVenta.setText(String.valueOf(p.getPrecioVenta()));
        vista.spn_stock.setValue(p.getStockProducto());
        if (p.getEstadoProducto().equals("Disponible")) {
            vista.btn_disponible.setSelected(true);
        } else {
            vista.btn_nodisponible.setSelected(true);
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
    }

    private void limpiarCampos() {
        vista.cmb_nomproducto.setSelectedIndex(0);
        vista.cmb_tpempaque.setSelectedIndex(0);
        vista.cmb_tmproducto.setSelectedIndex(0);

        // 🔎 Limpiar campos numéricos y de texto
        vista.txt_precioVenta.setText("");
        vista.spn_stock.setValue(0);
        vista.txt_IDprodbuscar.setText("");

        // 🔎 Resetear estado (radio buttons)
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
    }
}
