package controllers;

import models.Cliente;
import controllers.ClienteRepository;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.ClientePotenciales;
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
        javax.swing.SwingUtilities.invokeLater(() -> {
            verificarCumpleaniosHoy();
        });
    }

    private void iniciarEventos() {
        vista.btn_modificarcli.addActionListener(e -> modificarCliente());
        vista.btn_guardarcli.addActionListener(e -> guardarCliente());
        vista.btn_eliminarcli.addActionListener(e -> eliminarCliente());
        vista.txt_IDclibuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTablaClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTablaClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTablaClientes();
            }
        });
        vista.tbl_clientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cargarClienteDesdeTabla();
            }
        });
        vista.txt_telefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        vista.txt_cedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        vista.txt_nomcliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    e.consume();
                }
            }
        });
        vista.txt_apecliente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    e.consume();
                }
            }
        });
    }

    private boolean validarCedula(String cedula) {
        if (!cedula.matches("\\d{10}")) {
            return false;
        }
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }
        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito > 5) {
            return false;
        }
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
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

    private boolean validarDatos(String idActual) {
        String nombre = vista.txt_nomcliente.getText().trim();
        String apellido = vista.txt_apecliente.getText().trim();
        String telefono = vista.txt_telefono.getText().trim();
        String cedula = vista.txt_cedula.getText().trim();
        String direccion = vista.txt_direccion.getText().trim();

        if (nombre.isEmpty() || !validarNombre(nombre)) {
            JOptionPane.showMessageDialog(vista, "Nombre inválido, Campos Vacios");
            return false;
        }
        if (apellido.isEmpty() || !validarApellido(apellido)) {
            JOptionPane.showMessageDialog(vista, "Apellido inválido, Campos Vacios");
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
        if (!cedula.isEmpty()) { 
            if (!validarCedula(cedula)) {
                JOptionPane.showMessageDialog(vista, "Cédula ecuatoriana inválida.");
                return false;
            }
            if (dao.existeCedula(cedula, idActual)) {
                JOptionPane.showMessageDialog(vista, "Error: Ya existe un cliente registrado con el número de cédula: " + cedula);
                return false;
            }
        }
        if (!vista.btn_activo.isSelected() && !vista.btn_inactivo.isSelected()) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar el estado del cliente");
            return false;
        }
        return true;
    }

    private void filtrarTablaClientes() {
        String texto = vista.txt_IDclibuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) vista.tbl_clientes.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_clientes.setRowSorter(sorter);

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            limpiarCampos();
            vista.tbl_clientes.clearSelection();
            generarID();
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2));
        }
    }

    private void cargarClienteDesdeTabla() {
        int filaVista = vista.tbl_clientes.getSelectedRow();
        if (filaVista == -1) {
            return;
        }

        int filaModelo = vista.tbl_clientes.convertRowIndexToModel(filaVista);
        String id = vista.tbl_clientes.getModel().getValueAt(filaModelo, 0).toString();

        Cliente cliente = dao.buscarPorId(id);
        if (cliente != null) {
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
            if (cliente.getFechaCumpleanios() != null) {
                vista.jd_fechaCumpleanios.setDate(
                        java.sql.Date.valueOf(cliente.getFechaCumpleanios()));
            } else {
                vista.jd_fechaCumpleanios.setDate(null);
            }
        }
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
        if (cliente.getFechaCumpleanios() != null) {
            vista.jd_fechaCumpleanios.setDate(java.sql.Date.valueOf(cliente.getFechaCumpleanios()));
        } else {
            vista.jd_fechaCumpleanios.setDate(null);
        }
    }

    private void guardarCliente() {
        if (!validarDatos(vista.txt_IDcliente.getText())) {
            return;
        }
        if (dao.buscarPorId(vista.txt_IDcliente.getText()) != null) {
            JOptionPane.showMessageDialog(vista,
                    "Este cliente ya existe. Use MODIFICAR para editar sus datos.");
            return;
        }
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        java.util.Date fechaData = vista.jd_fechaCumpleanios.getDate();
        java.time.LocalDate fechaCumple = (fechaData != null)
                ? new java.sql.Date(fechaData.getTime()).toLocalDate()
                : null;

        Cliente cliente = new Cliente(
                vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado,
                fechaCumple);

        if (dao.guardarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente guardado");
            vista.tbl_clientes.setEnabled(false);
            cargarTabla();
            vista.tbl_clientes.setEnabled(true);
            vista.tbl_clientes.clearSelection();
            limpiarCampos();
            generarID();
        }
    }

    private void modificarCliente() {
        if (vista.txt_IDcliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla para modificar");
            return;
        }
        if (!validarDatos(vista.txt_IDcliente.getText())) {
            return;
        }
        String estado = vista.btn_activo.isSelected() ? "Activo" : "Inactivo";
        java.util.Date fechaData = vista.jd_fechaCumpleanios.getDate();
        java.time.LocalDate fechaCumple = (fechaData != null)
                ? new java.sql.Date(fechaData.getTime()).toLocalDate()
                : null;

        Cliente cliente = new Cliente(
                vista.txt_IDcliente.getText(),
                vista.txt_nomcliente.getText().trim(),
                vista.txt_apecliente.getText().trim(),
                vista.txt_cedula.getText().trim(),
                vista.txt_telefono.getText().trim(),
                vista.txt_direccion.getText().trim(),
                estado,
                fechaCumple);

        if (dao.modificarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente modificado");
            vista.tbl_clientes.setEnabled(false);
            cargarTabla();
            vista.tbl_clientes.setEnabled(true);
            vista.tbl_clientes.clearSelection();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
        }
    }

    private void eliminarCliente() {
        if (vista.txt_IDcliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla para eliminar");
            return;
        }
        int r = JOptionPane.showConfirmDialog(vista, "¿Eliminar este cliente?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }
        if (dao.eliminarCliente(vista.txt_IDcliente.getText())) {
            JOptionPane.showMessageDialog(vista, "Cliente eliminado");
            vista.tbl_clientes.setEnabled(false);
            cargarTabla();
            vista.tbl_clientes.setEnabled(true);
            vista.tbl_clientes.clearSelection();
            limpiarCampos();
            generarID();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        modelo.addColumn("Cumpleaños");

        for (Cliente c : dao.listarClientes()) {
            if (!"Activo".equals(c.getEstado())) {
                continue;
            }
            String cedulaTabla = (c.getCedula() == null || c.getCedula().trim().isEmpty()) ? "S/N" : c.getCedula();

            modelo.addRow(new Object[]{
                c.getId(), 
                c.getNombre(), 
                c.getApellido(),
                cedulaTabla,
                c.getTelefono(), 
                c.getDireccion(), 
                c.getEstado(),
                c.getFechaCumpleanios()
            });
        }
        vista.tbl_clientes.setModel(modelo);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_clientes.setRowSorter(sorter);
        vista.tbl_clientes.clearSelection();
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
        vista.jd_fechaCumpleanios.setDate(null);
    }

    private void generarID() {
        vista.txt_IDcliente.setEnabled(true);
        vista.txt_IDcliente.setText(dao.generarNuevoID());
        vista.txt_IDcliente.setEnabled(false);
    }

    private void verificarCumpleaniosHoy() {
        java.time.LocalDate hoy = java.time.LocalDate.now();
        int mesHoy = hoy.getMonthValue();
        int diaHoy = hoy.getDayOfMonth();

        java.util.ArrayList<Cliente> clientes = dao.listarClientes();

        for (Cliente c : clientes) {
            if (c.getFechaCumpleanios() != null && c.getEstado().equals("Activo")) {
                int mesCliente = c.getFechaCumpleanios().getMonthValue();
                int diaCliente = c.getFechaCumpleanios().getDayOfMonth();
                if (mesHoy == mesCliente && diaHoy == diaCliente) {
                    String mensaje = "🎉 " + c.getNombre() + " " + c.getApellido() + " cumple años el día de hoy. 🎉";
                    mostrarAlertaEsquina(mensaje);
                }
            }
        }
    }

    private void mostrarAlertaEsquina(String mensaje) {
        JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        javax.swing.JDialog dialogo = optionPane.createDialog(vista, "Alerta de Cumpleaños");
        dialogo.setLocation(10, 10);
        dialogo.setVisible(true);
        dialogo.dispose();
    }

    public void cargarClientePotencial(ClientePotenciales cp) {
        vista.txt_nomcliente.setText(cp.getNombreClientePotencial());
        vista.txt_apecliente.setText(cp.getApellidoClientePotencial());
        vista.txt_telefono.setText(cp.getTelefonoClientePotencial());

        generarID();
        vista.txt_cedula.setText("");
        vista.txt_direccion.setText("");
        vista.btn_activo.setSelected(false);
        vista.btn_inactivo.setSelected(false);
        vista.jd_fechaCumpleanios.setDate(null);

    }
}
