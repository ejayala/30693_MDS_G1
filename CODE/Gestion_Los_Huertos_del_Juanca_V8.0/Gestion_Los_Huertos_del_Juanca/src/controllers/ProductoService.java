/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Producto;
import views.Frm_Principal;

/**
 *
 * @author User
 */
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

    private boolean validarNombre(String nombre) {
        return nombre.matches("^(?=.*[A-Za-zÁÉÍÓÚáéíóúÑñ])[A-Za-zÁÉÍÓÚáéíóúÑñ0-9 ]+$");
    }

    private boolean validarPrecio(String precio) {
        try {
            double p = Double.parseDouble(precio);
            return p > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarDatos() {
        String nombre = vista.txt_nomproducto.getText().trim();
        String empaque = vista.txt_tpempaque.getText().trim();
        String tamano = vista.txt_tmproducto.getText().trim();
        String precio = vista.txt_precioVenta.getText().trim();
        int stock = (int) vista.spn_stock.getValue();

        if (nombre.isEmpty() || !validarNombre(nombre)) {
            JOptionPane.showMessageDialog(vista, "Nombre de producto inválido");
            return false;
        }
        if (empaque.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Tipo de empaque requerido");
            return false;
        }
        if (tamano.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Tamaño requerido");
            return false;
        }
        if (!validarPrecio(precio)) {
            JOptionPane.showMessageDialog(vista, "Precio inválido, debe ser un número mayor a 0");
            return false;
        }
        if (stock < 0) {
            JOptionPane.showMessageDialog(vista, "Stock no puede ser negativo");
            return false;
        }
        return true;
    }

    private void guardarProducto() {
        if (!validarDatos()) return;
        String estado = vista.btn_disponible.isSelected() ? "Disponible" : "No Disponible";
        Producto p = new Producto(
            Integer.parseInt(vista.txt_idproducto.getText()),
            vista.txt_codproducto.getText(),
            vista.txt_nomproducto.getText().trim(),
            vista.txt_tpempaque.getText().trim(),
            vista.txt_tmproducto.getText().trim(),
            Double.parseDouble(vista.txt_precioVenta.getText().trim()),
            (int) vista.spn_stock.getValue(),
            estado);
        if (dao.guardarProducto(p)) {
            JOptionPane.showMessageDialog(vista, "Producto guardado");
            cargarTabla();
            limpiarCampos();
            generarID();
        }
    }

    private void modificarProducto() {
        if (!validarDatos()) return;
        String estado = vista.btn_disponible.isSelected() ? "Disponible" : "No Disponible";
        Producto p = new Producto(
            Integer.parseInt(vista.txt_idproducto.getText()),
            vista.txt_codproducto.getText(),
            vista.txt_nomproducto.getText().trim(),
            vista.txt_tpempaque.getText().trim(),
            vista.txt_tmproducto.getText().trim(),
            Double.parseDouble(vista.txt_precioVenta.getText().trim()),
            (int) vista.spn_stock.getValue(),
            estado);
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
        int r = JOptionPane.showConfirmDialog(vista, "¿Eliminar este producto?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;
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
        vista.txt_idproducto.setEnabled(true);
        vista.txt_idproducto.setText(String.valueOf(p.getIdProducto()));
        vista.txt_idproducto.setEnabled(false);
        vista.txt_codproducto.setEnabled(true);
        vista.txt_codproducto.setText(p.getCodigoProducto());
        vista.txt_codproducto.setEnabled(false);
        vista.txt_nomproducto.setText(p.getNombreProducto());
        vista.txt_tpempaque.setText(p.getTipoEmpaque());
        vista.txt_tmproducto.setText(p.getTamanoProducto());
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
            public boolean isCellEditable(int row, int column) { return false; }
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
        vista.txt_nomproducto.setText("");
        vista.txt_tpempaque.setText("");
        vista.txt_tmproducto.setText("");
        vista.txt_precioVenta.setText("");
        vista.spn_stock.setValue(0);
        vista.txt_IDprodbuscar.setText("");
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
