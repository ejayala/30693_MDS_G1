package controllers;

import models.Cliente;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import views.Frm_Principal;

public class ClienteService {
    private final Frm_Principal vista;
    private final ClienteRepository dao;

    public ClienteService(Frm_Principal vista, ClienteRepository dao) {
        this.vista = vista;
        this.dao = dao;
        cargarTabla();
        iniciarEventos();
    }

    // ── Eventos ────────────────────────────────────────────────────────────────
    private void iniciarEventos() {
        vista.btn_buscarcli.addActionListener(e -> buscarCliente());
        vista.btn_guardarcli.addActionListener(e -> guardarCliente());
        vista.btn_modificarcli.addActionListener(e -> modificarCliente());
        vista.btn_eliminarcli.addActionListener(e -> eliminarCliente());
    }

    // ── Buscar ─────────────────────────────────────────────────────────────────
    private void buscarCliente() {
        String id = vista.txt_IDclibuscar.getText().trim();
        if (id.isEmpty() || vista.txt_nomcliente.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Complete ID y Nombre.");
            return;
        }
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Ingrese un ID para buscar.");
            return;
        }
        Cliente cliente = dao.buscarPorId(id);
        if (cliente == null) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente no encontrado.");
            limpiarCamposCliente();
            return;
        }
        cargarCampos(cliente);
    }

    // ── Guardar (insertar nuevo) ───────────────────────────────────────────────
    private void guardarCliente() {
        String id = vista.txt_IDcliente.getText().trim();
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista, "El ID del cliente no puede estar vacío.");
            return;
        }
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        Cliente cliente = new Cliente(
                id,
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado);
        boolean ok = dao.guardarCliente(cliente);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente registrado correctamente.");
            limpiarCamposCliente();
            cargarTabla();
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista, "Error: ya existe un cliente con ese ID.");
        }
    }

    // ── Modificar ─────────────────────────────────────────────────────────────
    private void modificarCliente() {
        String id = vista.txt_IDcliente.getText().trim();
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Busque primero el cliente que desea modificar.");
            return;
        }
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        Cliente cliente = new Cliente(
                id,
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado);
        boolean ok = dao.modificarCliente(cliente);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente modificado correctamente.");
            limpiarCamposCliente();
            cargarTabla();
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista, "Error: cliente no encontrado.");
        }
    }

    // ── Eliminar ──────────────────────────────────────────────────────────────
    private void eliminarCliente() {
        String id = vista.txt_IDcliente.getText().trim();
        if (id.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Busque primero el cliente que desea eliminar.");
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(vista,
                "¿Está seguro de eliminar al cliente con ID: " + id + "?",
                "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm != javax.swing.JOptionPane.YES_OPTION) return;

        boolean ok = dao.eliminarCliente(id);
        if (ok) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente eliminado correctamente.");
            limpiarCamposCliente();
            cargarTabla();
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista, "Error: cliente no encontrado.");
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private void cargarCampos(Cliente c) {
        vista.txt_IDcliente.setText(c.getId());
        vista.txt_nomcliente.setText(c.getNombre());
        vista.txt_apecliente.setText(c.getApellido());
        vista.txt_cedula.setText(c.getCedula());
        vista.txt_telefono.setText(c.getTelefono());
        vista.txt_direccion.setText(c.getDireccion());
        if ("Activo".equals(c.getEstado())) {
            vista.btn_activo.setSelected(true);
        } else {
            vista.btn_inactivo.setSelected(true);
        }
    }

    private void limpiarCamposCliente() {
        vista.txt_IDclibuscar.setText("");
        vista.txt_IDcliente.setText("");
        vista.txt_nomcliente.setText("");
        vista.txt_apecliente.setText("");
        vista.txt_cedula.setText("");
        vista.txt_telefono.setText("");
        vista.txt_direccion.setText("");
        vista.btn_activo.setSelected(false);
        vista.btn_inactivo.setSelected(false);
    }

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Apellido", "Cédula", "Teléfono", "Dirección", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        ArrayList<Cliente> lista = dao.listarClientes();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId(), c.getNombre(), c.getApellido(),
                c.getCedula(), c.getTelefono(), c.getDireccion(), c.getEstado()
            });
        }
        vista.tbl_clientes.setModel(modelo);
    }
}
