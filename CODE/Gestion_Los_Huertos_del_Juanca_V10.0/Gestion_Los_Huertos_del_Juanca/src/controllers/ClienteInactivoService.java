package controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Cliente;
import views.Frm_ClientesInactivos;

public class ClienteInactivoService {

    private final Frm_ClientesInactivos vista;
    private final ClienteRepository dao;

    public ClienteInactivoService(Frm_ClientesInactivos vista, ClienteRepository dao) {
        this.vista = vista;
        this.dao = dao;
        iniciarEventos();
        cargarTabla();
    }

    private void iniciarEventos() {
        vista.btnBuscar.addActionListener(e -> cargarTabla());
        vista.cmbEstado.addActionListener(e -> cargarTabla());
        vista.btnDarBaja.addActionListener(e -> darDeBaja());
        vista.btnRestaurar.addActionListener(e -> restaurar());
        vista.btnDuplicados.addActionListener(e -> eliminarDuplicados());
        vista.tblClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarHistorialSeleccionado();
            }
        });
    }

    private void cargarTabla() {
        DefaultTableModel modelo = crearModelo();
        String busqueda = vista.txtBusqueda.getText().trim().toLowerCase();
        String estado = vista.cmbEstado.getSelectedItem().toString();

        for (Cliente cliente : dao.listarClientes()) {
            boolean coincideBusqueda = busqueda.isEmpty()
                    || cliente.getId().toLowerCase().contains(busqueda)
                    || cliente.getCedula().toLowerCase().contains(busqueda);
            boolean coincideEstado = estado.equals("Todos") || cliente.getEstado().equals(estado);
            if (coincideBusqueda && coincideEstado) {
                agregarFila(modelo, cliente);
            }
        }
        vista.tblClientes.setModel(modelo);
        vista.txtHistorial.setText("Seleccione un cliente para visualizar su historial de compras/ventas.");
    }

    private DefaultTableModel crearModelo() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        return modelo;
    }

    private void agregarFila(DefaultTableModel modelo, Cliente cliente) {
        modelo.addRow(new Object[]{
            cliente.getId(),
            cliente.getCedula(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getTelefono(),
            cliente.getDireccion(),
            cliente.getEstado()
        });
    }

    private Cliente obtenerSeleccionado() {
        int fila = vista.tblClientes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla");
            return null;
        }
        String id = vista.tblClientes.getValueAt(fila, 0).toString();
        return dao.buscarPorId(id);
    }

    private void darDeBaja() {
        Cliente cliente = obtenerSeleccionado();
        if (cliente == null) {
            return;
        }
        if ("Inactivo".equals(cliente.getEstado())) {
            JOptionPane.showMessageDialog(vista, "El cliente ya se encuentra inactivo");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(vista,
                "\u00bfEst\u00e1 seguro de dar de baja este cliente?",
                "Confirmar baja",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }
        cliente.setEstado("Inactivo");
        if (dao.modificarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente dado de baja. El historial se conserva.");
            cargarTabla();
        }
    }

    private void restaurar() {
        Cliente cliente = obtenerSeleccionado();
        if (cliente == null) {
            return;
        }
        cliente.setEstado("Activo");
        if (dao.modificarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Cliente restaurado como Activo");
            cargarTabla();
        }
    }

    private void eliminarDuplicados() {
        Map<String, ArrayList<Cliente>> porCedula = agruparPorCedula();
        StringBuilder detalle = new StringBuilder();
        int totalDuplicados = 0;

        for (Map.Entry<String, ArrayList<Cliente>> entrada : porCedula.entrySet()) {
            ArrayList<Cliente> grupo = entrada.getValue();
            if (grupo.size() > 1) {
                totalDuplicados += grupo.size() - 1;
                detalle.append("Cedula ").append(entrada.getKey()).append(": ");
                for (Cliente c : grupo) {
                    detalle.append(c.getId()).append(" ").append(c.getNombre()).append(" ").append(c.getApellido()).append(" | ");
                }
                detalle.append("\n");
            }
        }

        if (totalDuplicados == 0) {
            JOptionPane.showMessageDialog(vista, "No se encontraron clientes duplicados por cedula");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(vista,
                detalle + "\n\u00bfDesea unificar estos registros? El historial se conservar\u00e1",
                "Duplicados detectados",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        ArrayList<Cliente> unificados = new ArrayList<>();
        for (ArrayList<Cliente> grupo : porCedula.values()) {
            Cliente conservar = elegirRegistroAConservar(grupo);
            unificados.add(conservar);
        }

        if (dao.guardarClientes(unificados)) {
            JOptionPane.showMessageDialog(vista, "Registros duplicados unificados correctamente");
            cargarTabla();
        }
    }

    private Map<String, ArrayList<Cliente>> agruparPorCedula() {
        Map<String, ArrayList<Cliente>> porCedula = new LinkedHashMap<>();
        for (Cliente cliente : dao.listarClientes()) {
            porCedula.computeIfAbsent(cliente.getCedula(), clave -> new ArrayList<>()).add(cliente);
        }
        return porCedula;
    }

    private Cliente elegirRegistroAConservar(ArrayList<Cliente> grupo) {
        Cliente conservar = grupo.get(0);
        for (Cliente cliente : grupo) {
            if ("Activo".equals(cliente.getEstado())) {
                conservar = cliente;
                break;
            }
        }
        return conservar;
    }

    private void mostrarHistorialSeleccionado() {
        int fila = vista.tblClientes.getSelectedRow();
        if (fila < 0) {
            return;
        }
        String id = vista.tblClientes.getValueAt(fila, 0).toString();
        Cliente cliente = dao.buscarPorId(id);
        if (cliente == null) {
            return;
        }
        vista.txtHistorial.setText(
                "Cliente: " + cliente.getNombre() + " " + cliente.getApellido() + "\n"
                + "Cedula: " + cliente.getCedula() + "\n"
                + "Estado: " + cliente.getEstado() + "\n\n"
                + "Historial de compras/ventas:\n"
                + "- El registro del cliente se conserva al dar de baja.\n"
                + "- Cuando exista el modulo de ventas, las transacciones se asociaran al ID " + cliente.getId() + ".\n"
                + "- Si se fusionan duplicados, el historial se unifica en el registro seleccionado por el sistema.");
    }
}
