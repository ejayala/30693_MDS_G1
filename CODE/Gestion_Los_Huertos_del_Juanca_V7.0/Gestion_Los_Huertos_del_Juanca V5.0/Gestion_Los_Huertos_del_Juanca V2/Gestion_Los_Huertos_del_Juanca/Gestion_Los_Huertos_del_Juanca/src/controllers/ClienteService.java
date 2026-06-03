package controllers;

import models.Cliente;
import controllers.ClienteRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.Frm_Principal;

public class ClienteService {
private Frm_Principal vista;
    private ClienteRepository dao;

    public ClienteService(Frm_Principal vista, ClienteRepository dao) {
        this.vista = vista;
        this.dao = dao;
        iniciarEventos();
        generarID();
        vista.txt_IDcliente.setEnabled(false);
        cargarTabla();
    }

    private void iniciarEventos() {
        vista.btn_buscarcli.addActionListener(e -> buscarCliente());
        vista.btn_modificarcli.addActionListener(e -> modificarCliente());
        vista.btn_guardarcli.addActionListener(e -> guardarCliente());
        vista.btn_eliminarcli.addActionListener(e -> eliminarCliente());
    }

    private boolean validarCedula(String cedula) {
        if (!cedula.matches("\\d{10}")) return false;
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) return false;
        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito > 5) return false;
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) digito -= 9;
            }
            suma += digito;
        }
        int verificador = (10 - (suma % 10)) % 10;
        return verificador == Character.getNumericValue(cedula.charAt(9));
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^09\\d{8}$");
    }

    private boolean validarNombre(String nombre) {
        return nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    private boolean validarApellido(String apellido) {
        return apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    private boolean validarDatos() {
        String nombre = vista.txt_nomcliente.getText().trim();
        String apellido = vista.txt_apecliente.getText().trim();
        String telefono = vista.txt_telefono.getText().trim();
        String cedula = vista.txt_cedula.getText().trim();
        String direccion = vista.txt_direccion.getText().trim();

        if (nombre.isEmpty() || !validarNombre(nombre)) {
            JOptionPane.showMessageDialog(vista, "Nombre inválido, solo letras");
            return false;
        }
        if (apellido.isEmpty() || !validarApellido(apellido)) {
            JOptionPane.showMessageDialog(vista, "Apellido inválido, solo letras");
            return false;
        }
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Dirección requerida");
            return false;
        }
        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(vista, "Teléfono inválido, debe empezar con 09 y tener 10 dígitos");
            return false;
        }
        if (!cedula.isEmpty() && !validarCedula(cedula)) {
            JOptionPane.showMessageDialog(vista, "Cédula ecuatoriana inválida");
            return false;
        }
        return true;
    }

    private void buscarCliente() {
        String id = vista.txt_IDclibuscar.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un ID para buscar");
            return;
        }
        Cliente cliente = dao.buscarPorId(id);
        if (cliente == null) {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
            return;
        }
        vista.txt_IDcliente.setEnabled(true);
        vista.txt_IDcliente.setText(cliente.getId());
        vista.txt_IDcliente.setEnabled(false);
        vista.txt_nomcliente.setText(cliente.getNombre());
        vista.txt_apecliente.setText(cliente.getApellido());
        vista.txt_cedula.setText(cliente.getCedula());
        vista.txt_telefono.setText(cliente.getTelefono());
        vista.txt_direccion.setText(cliente.getDireccion());
        if (cliente.getEstado().equals("Activo")) {
            vista.btn_activo.setSelected(true);
        } else {
            vista.btn_inactivo.setSelected(true);
        }
    }

    private void guardarCliente() {
        if (!validarDatos()) return;
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        Cliente cliente = new Cliente(
                vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado);
        if (dao.guardarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado");
            cargarTabla();
            limpiarCampos();
            generarID();
        }
    }

    private void modificarCliente() {
        if (!validarDatos()) return;
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        Cliente cliente = new Cliente(
                vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado);
        if (dao.modificarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente modificado");
            cargarTabla();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
        }
    }

    private void eliminarCliente() {
        String id = vista.txt_IDcliente.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Primero busque un cliente");
            return;
        }
        int r = JOptionPane.showConfirmDialog(vista, "¿Eliminar este cliente?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;
        if (dao.eliminarCliente(id)) {
            JOptionPane.showMessageDialog(vista, "Cliente eliminado");
            cargarTabla();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
        }
    }

    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        for (Cliente c : dao.listarClientes()) {
            modelo.addRow(new Object[]{
                c.getId(), c.getNombre(), c.getApellido(),
                c.getCedula(), c.getTelefono(), c.getDireccion(), c.getEstado()
            });
        }
        vista.tbl_clientes.setModel(modelo);
    }

    private void limpiarCampos() {
        vista.txt_nomcliente.setText("");
        vista.txt_apecliente.setText("");
        vista.txt_cedula.setText("");
        vista.txt_telefono.setText("");
        vista.txt_direccion.setText("");
        vista.txt_IDclibuscar.setText("");
        vista.btn_activo.setSelected(false);
        vista.btn_inactivo.setSelected(false);
    }

    private void generarID() {
        vista.txt_IDcliente.setEnabled(true);
        vista.txt_IDcliente.setText(dao.generarNuevoID());
        vista.txt_IDcliente.setEnabled(false);
    }
     
}
