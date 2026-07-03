package controllers;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Cliente;
import views.Frm_ClientesInactivos;

public class ClienteInactivoService {

    private final Frm_ClientesInactivos vista;
    private final ClienteRepository dao;
    private final Runnable onCambioEstado;

    public ClienteInactivoService(Frm_ClientesInactivos vista, ClienteRepository dao, Runnable onCambioEstado) {
        this.vista = vista;
        this.dao = dao;
        this.onCambioEstado = onCambioEstado;
        iniciarEventos();
        cargarTabla();
    }

    private void iniciarEventos() {
        vista.btnBuscar.addActionListener(e -> buscar());
        vista.btnCambiarEstado.addActionListener(e -> cambiarEstado());
    }

    /**
     * Accion del boton BUSCAR.
     * - Si el campo de busqueda esta vacio y no existen clientes inactivos,
     *   avisa que aun no hay clientes inactivos registrados.
     * - Si el campo de busqueda esta vacio pero si existen clientes
     *   inactivos, exige que primero se ingrese el numero de cedula.
     * - Si se ingreso una cedula y no hay coincidencias, avisa que no
     *   existen clientes inactivos con esa cedula.
     */
    private void buscar() {
        String busqueda = vista.txtBusqueda.getText().trim();

        if (busqueda.isEmpty()) {
            if (existenClientesInactivos()) {
                cargarTabla();
                JOptionPane.showMessageDialog(vista,
                        "Ingrese primero el numero de cedula para realizar la busqueda.",
                        "Dato requerido", JOptionPane.WARNING_MESSAGE);
            } else {
                cargarTabla();
                JOptionPane.showMessageDialog(vista,
                        "Aun no existen clientes inactivos registrados.",
                        "Sin resultados", JOptionPane.WARNING_MESSAGE);
            }
            return;
        }

        boolean hayResultados = cargarTabla();
        if (!hayResultados) {
            JOptionPane.showMessageDialog(vista,
                    "No existen clientes inactivos que coincidan con la busqueda \"" + busqueda + "\".",
                    "Sin resultados", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Indica si existe al menos un cliente con estado Inactivo,
     * sin tener en cuenta el texto de busqueda.
     */
    private boolean existenClientesInactivos() {
        for (Cliente cliente : dao.listarClientes()) {
            if ("Inactivo".equals(cliente.getEstado())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Carga la tabla con los clientes inactivos que coincidan con el texto
     * de busqueda. Devuelve true si se encontro al menos un cliente.
     */
    private boolean cargarTabla() {
        DefaultTableModel modelo = crearModelo();
        String busqueda = vista.txtBusqueda.getText().trim().toLowerCase();
        boolean hayResultados = false;

        for (Cliente cliente : dao.listarClientes()) {
            boolean coincideBusqueda = busqueda.isEmpty()
                    || cliente.getId().toLowerCase().contains(busqueda)
                    || cliente.getCedula().toLowerCase().contains(busqueda);
            // Esta tabla solo gestiona clientes Inactivos; los Activos se
            // muestran y administran desde la ventana principal de Clientes.
            boolean esInactivo = "Inactivo".equals(cliente.getEstado());
            if (coincideBusqueda && esInactivo) {
                agregarFila(modelo, cliente);
                hayResultados = true;
            }
        }
        vista.tblClientes.setModel(modelo);
        return hayResultados;
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

    private void cambiarEstado() {
        Cliente cliente = obtenerSeleccionado();
        if (cliente == null) {
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(vista,
                "\u00bfDesea cambiar el estado del cliente a Activo?",
                "Confirmar cambio de estado",
                JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        cliente.setEstado("Activo");
        if (dao.modificarCliente(cliente)) {
            JOptionPane.showMessageDialog(vista, "Estado del cliente actualizado a Activo");
            cargarTabla();
            // Notifica a la ventana principal para que el cliente reaparezca
            // de inmediato en la tabla de Clientes (estado Activo).
            if (onCambioEstado != null) {
                onCambioEstado.run();
            }
        }
    }
}
