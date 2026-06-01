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
        cargarTabla();
        iniciarEventos();
        generarID();
        vista.txt_IDcliente.setEnabled(false);
    }

    private void iniciarEventos() {
        vista.btn_buscarcli.addActionListener(e -> buscarCliente());
        vista.btn_modificarcli.addActionListener(e -> modificarCliente());
        vista.btn_guardarcli.addActionListener(e -> guardarCliente());
        vista.btn_eliminarcli.addActionListener(e -> eliminarCliente());
    }

    private boolean validarCedula(String cedula) {

        if (!cedula.matches("\\d{10}")) {
            return false;
        }

        int provincia
                = Integer.parseInt(
                        cedula.substring(0, 2));

        if (provincia < 1 || provincia > 24) {
            return false;
        }

        int tercerDigito
                = Character.getNumericValue(
                        cedula.charAt(2));

        if (tercerDigito > 5) {
            return false;
        }

        int suma = 0;

        for (int i = 0; i < 9; i++) {

            int digito
                    = Character.getNumericValue(
                            cedula.charAt(i));

            if (i % 2 == 0) {

                digito *= 2;

                if (digito > 9) {
                    digito -= 9;
                }
            }

            suma += digito;
        }

        int verificador
                = (10 - (suma % 10)) % 10;

        int ultimoDigito
                = Character.getNumericValue(
                        cedula.charAt(9));

        return verificador == ultimoDigito;
    }

    private boolean validarTelefono(String telefono) {

        return telefono.matches("^09\\d{8}$");
    }

    private boolean validarApellido(String apellido) {

        return apellido.matches(
                "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    private boolean validarNombre(String nombre) {

        return nombre.matches(
                "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    private void buscarCliente() {
        String id = vista.txt_IDclibuscar.getText();
        Cliente cliente = dao.buscarPorId(id);
        if (cliente == null) {
            vista.txt_IDcliente.setText("");
            vista.txt_nomcliente.setText("");
            vista.txt_apecliente.setText("");
            vista.txt_cedula.setText("");
            vista.txt_telefono.setText("");
            vista.txt_direccion.setText("");
            return;
        }
        vista.txt_IDcliente.setText(cliente.getId());
        vista.txt_nomcliente.setText(cliente.getNombre());
        vista.txt_apecliente.setText(cliente.getApellido());
        vista.txt_cedula.setText(cliente.getCedula());
        vista.txt_telefono.setText(cliente.getTelefono());
        vista.txt_direccion.setText(cliente.getDireccion());
        if (cliente.getEstado().equals("Activo")) {
            vista.btn_activo.setSelected(true);
        } else {
            vista.btn_activo.setSelected(true);
        }
    }

    private void modificarCliente() {
        if (!validarDatosCliente()) {
            return;
        }
        String estado;
        if (vista.btn_activo.isSelected()) {
            estado = "Activo";
        } else {
            estado = "Inactivo";
        }
        Cliente cliente = new Cliente(vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText(),
                vista.txt_apecliente.getText(),
                vista.txt_cedula.getText(),
                vista.txt_telefono.getText(),
                vista.txt_direccion.getText(), estado);
        boolean bandera = dao.modificarCliente(cliente);
        if (bandera) {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente modificado");
            cargarTabla();
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
        }
    }

    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        ArrayList<Cliente> lista = dao.listarClientes();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApellido(),
                c.getCedula(),
                c.getTelefono(),
                c.getDireccion(),
                c.getEstado()
            });
        }
        vista.tbl_clientes.setModel(modelo);
    }

    private void limpiarCampos() {

        vista.txt_IDcliente.setText("");
        vista.txt_nomcliente.setText("");
        vista.txt_apecliente.setText("");
        vista.txt_cedula.setText("");
        vista.txt_telefono.setText("");
        vista.txt_direccion.setText("");
        vista.txt_IDclibuscar.setText("");

        vista.btn_activo.setSelected(false);
        vista.btn_inactivo.setSelected(false);
    }

    private void guardarCliente() {

        if (!validarDatosCliente()) {
            return;
        }

        String estado;

        if (vista.btn_activo.isSelected()) {
            estado = "Activo";
        } else {
            estado = "Inactivo";
        }

        Cliente cliente = new Cliente(vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText(),
                vista.txt_apecliente.getText(),
                vista.txt_cedula.getText(),
                vista.txt_telefono.getText(),
                vista.txt_direccion.getText(), estado);

        boolean guardado
                = dao.guardarCliente(cliente);

        if (guardado) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Cliente guardado");

            cargarTabla();
            limpiarCampos();
            generarID();
        }
    }

    private boolean validarDatosCliente() {

        String nombre = vista.txt_nomcliente.getText().trim();
        String apellido = vista.txt_apecliente.getText().trim();
        String telefono = vista.txt_telefono.getText().trim();
        String cedula = vista.txt_cedula.getText().trim();

        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(vista,
                    "Nombre inválido");
            return false;
        }

        if (!validarApellido(apellido)) {
            JOptionPane.showMessageDialog(vista,
                    "Apellido inválido");
            return false;
        }

        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(vista,
                    "Teléfono inválido");
            return false;
        }

        if (!cedula.isEmpty() && !validarCedula(cedula)) {
            JOptionPane.showMessageDialog(vista,
                    "Cédula ecuatoriana inválida");
            return false;
        }

        return true;
    }

    private void eliminarCliente() {

        String id
                = vista.txt_IDcliente.getText().trim();

        if (id.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Primero busque un cliente");

            return;
        }

        int respuesta
                = JOptionPane.showConfirmDialog(
                        vista,
                        "¿Eliminar este cliente?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado
                = dao.eliminarCliente(id);

        if (eliminado) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Cliente eliminado");

            cargarTabla();

            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    vista,
                    "No se pudo eliminar");
        }
    }

    private void generarID() {

        String nuevoID = dao.generarNuevoID();

        vista.txt_IDcliente.setText(nuevoID);
    }
}
