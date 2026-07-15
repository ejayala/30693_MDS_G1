package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.Cliente;
import models.Producto;
import models.Venta;
import models.DetalleVenta;
import views.Frm_Principal;
import controllers.VentaRepository;
import controllers.DetalleVentaRepository;
import controllers.ProductoRepository;
import controllers.ClienteRepository;

public class VentaService {

    private Frm_Principal vista;
    private VentaRepository ventaRepo;
    private DetalleVentaRepository detalleRepo;
    private ProductoRepository productoRepo;
    private ClienteRepository clienteRepo;
    
    private DefaultTableModel modeloCarrito;
    private DefaultTableModel modeloSugerencias;

    public VentaService(Frm_Principal vista, VentaRepository ventaRepo, DetalleVentaRepository detalleRepo, ProductoRepository productoRepo, ClienteRepository clienteRepo) {
        this.vista = vista;
        this.ventaRepo = ventaRepo;
        this.detalleRepo = detalleRepo;
        this.productoRepo = productoRepo;
        this.clienteRepo = clienteRepo;

        iniciarComponentesTablas();
        iniciarEventos();
        prepararCamposIniciales();
    }

    private void iniciarComponentesTablas() {
        modeloCarrito = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modeloCarrito.addColumn("Código");
        modeloCarrito.addColumn("Producto");
        modeloCarrito.addColumn("Precio Unit.");
        modeloCarrito.addColumn("Cantidad");
        modeloCarrito.addColumn("Subtotal");
        vista.tbl_carrito.setModel(modeloCarrito);

        modeloSugerencias = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modeloSugerencias.addColumn("ID");
        modeloSugerencias.addColumn("Nombre Completo");
        modeloSugerencias.addColumn("Cédula");
        vista.tbl_sugerenciasClientes.setModel(modeloSugerencias);
    }

    private void iniciarEventos() {
        vista.txt_buscarCliente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { actualizarSugerenciasClientes(); }
            @Override
            public void removeUpdate(DocumentEvent e) { actualizarSugerenciasClientes(); }
            @Override
            public void changedUpdate(DocumentEvent e) { actualizarSugerenciasClientes(); }
        });

        vista.tbl_sugerenciasClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarClienteDesdeTabla();
            }
        });

        vista.btn_agregarProducto.addActionListener(e -> agregarProductoAlCarrito());
        vista.btn_quitarProducto.addActionListener(e -> quitarProductoDelCarrito());
        vista.btn_finalizarVenta.addActionListener(e -> procesarVenta());
        vista.btn_verCatalogo.addActionListener(e -> abrirCatalogoProductos());
    }

    private void prepararCamposIniciales() {
        vista.txt_idVenta.setText(ventaRepo.generarNuevoCodigo());
        vista.txt_idVenta.setEnabled(false);
        vista.txt_fechaVenta.setText(LocalDate.now().toString());
        vista.txt_fechaVenta.setEnabled(false);
        vista.txt_totalVenta.setText("0.00");
        vista.txt_totalVenta.setEnabled(false);
        
        // Bloqueo total de campos de salida para evitar ediciones manuales accidentales
        vista.txt_nombreCliente.setEnabled(false);
        if (vista.txt_cedulaCliente != null) vista.txt_cedulaCliente.setEnabled(false);
        if (vista.txt_idClienteRef != null) vista.txt_idClienteRef.setEnabled(false); // Adicional: Bloqueo del ID
    }

    private void actualizarSugerenciasClientes() {
        String texto = vista.txt_buscarCliente.getText().trim();
        modeloSugerencias.setRowCount(0);

        if (texto.isEmpty()) {
            return;
        }

        ArrayList<Cliente> activos = clienteRepo.buscarClientesActivosPorNombre(texto);
        for (Cliente c : activos) {
            modeloSugerencias.addRow(new Object[]{
                c.getId(),
                c.getNombre() + " " + c.getApellido(),
                c.getCedula()
            });
        }
    }

    private void seleccionarClienteDesdeTabla() {
        int fila = vista.tbl_sugerenciasClientes.getSelectedRow();
        if (fila == -1) return;

        String idCliente = vista.tbl_sugerenciasClientes.getValueAt(fila, 0).toString();
        Cliente cliente = clienteRepo.buscarPorId(idCliente);

        if (cliente != null) {
            // Se llena automáticamente con Nombre y Apellido concatenados
            vista.txt_nombreCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
            if (vista.txt_cedulaCliente != null) vista.txt_cedulaCliente.setText(cliente.getCedula());
            if (vista.txt_idClienteRef != null) vista.txt_idClienteRef.setText(cliente.getId());
            
            vista.txt_buscarCliente.setText("");
            modeloSugerencias.setRowCount(0);
        }
    }

    private int buscarFilaProducto(String codigoProducto) {
        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            if (modeloCarrito.getValueAt(i, 0).toString().equals(codigoProducto)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarProductoAlCarrito() {
        String codigoProd = vista.txt_codigoProd.getText().trim();
        String cantStr = vista.txt_cantidadProd.getText().trim();

        if (!validarEntradasProducto(codigoProd, cantStr)) return;

        Producto prod = productoRepo.buscarPorCodigo(codigoProd);
        if (prod == null || !"Disponible".equalsIgnoreCase(prod.getEstadoProducto())) {
            JOptionPane.showMessageDialog(vista, "Producto no disponible o inexistente.");
            return;
        }

        int cantidadSolicitada = Integer.parseInt(cantStr);
        int filaExistente = buscarFilaProducto(codigoProd);

        if (filaExistente != -1) {
            int cantActual = Integer.parseInt(modeloCarrito.getValueAt(filaExistente, 3).toString());
            cantidadSolicitada += cantActual;
        }

        if (cantidadSolicitada > prod.getStockProducto()) {
            JOptionPane.showMessageDialog(vista, "Stock insuficiente. Máximo disponible: " + prod.getStockProducto());
            return;
        }

        actualizarFilaOAgregar(filaExistente, prod, cantidadSolicitada);
        calcularTotal();
        
        vista.txt_codigoProd.setText("");
        vista.txt_cantidadProd.setText("");
    }

    private void actualizarFilaOAgregar(int fila, Producto prod, int cantidad) {
        double subtotalCalculado = cantidad * prod.getPrecioVenta();
        if (fila != -1) {
            modeloCarrito.setValueAt(cantidad, fila, 3);
            modeloCarrito.setValueAt(subtotalCalculado, fila, 4);
        } else {
            modeloCarrito.addRow(new Object[]{
                prod.getCodigoProducto(),
                prod.getNombreProducto(),
                prod.getPrecioVenta(),
                cantidad,
                subtotalCalculado
            });
        }
    }

    private void calcularTotal() {
        double total = 0;
        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            total += Double.parseDouble(modeloCarrito.getValueAt(i, 4).toString());
        }
        vista.txt_totalVenta.setText(String.format("%.2f", total).replace(",", "."));
    }

    private void quitarProductoDelCarrito() {
        int fila = vista.tbl_carrito.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para remover.");
            return;
        }
        modeloCarrito.removeRow(fila);
        calcularTotal();
    }

    private boolean validarEntradasProducto(String codigo, String cantidad) {
        if (codigo.isEmpty() || cantidad.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Campos de producto incompletos.");
            return false;
        }
        if (!cantidad.matches("^[1-9]\\d*$")) {
            JOptionPane.showMessageDialog(vista, "Cantidad inválida.");
            return false;
        }
        return true;
    }

    private void procesarVenta() {
        String idCliente = vista.txt_idClienteRef.getText().trim();
        String idVenta = vista.txt_idVenta.getText();

        if (idCliente.isEmpty() || modeloCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "Validación fallida: Verifique el cliente y que el carrito no esté vacío.");
            return;
        }

        ArrayList<DetalleVenta> detallesATransaccionar = new ArrayList<>();

        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            String codProd = modeloCarrito.getValueAt(i, 0).toString();
            int cantVendida = Integer.parseInt(modeloCarrito.getValueAt(i, 3).toString());
            double precio = Double.parseDouble(modeloCarrito.getValueAt(i, 2).toString());
            String nomProd = modeloCarrito.getValueAt(i, 1).toString();

            Producto prod = productoRepo.buscarPorCodigo(codProd);
            if (prod == null || prod.getStockProducto() < cantVendida) {
                JOptionPane.showMessageDialog(vista, "Error crítico: El producto " + nomProd + " sufrió alteraciones de stock. Operación abortada.");
                return;
            }
            
            detallesATransaccionar.add(new DetalleVenta(idVenta, codProd, nomProd, cantVendida, precio));
        }

        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
            String codProd = modeloCarrito.getValueAt(i, 0).toString();
            int cantVendida = Integer.parseInt(modeloCarrito.getValueAt(i, 3).toString());
            
            Producto prod = productoRepo.buscarPorCodigo(codProd);
            prod.setStockProducto(prod.getStockProducto() - cantVendida);
            if (prod.getStockProducto() == 0) prod.setEstadoProducto("Agotado");
            productoRepo.modificarProducto(prod);
        }

        double totalVenta = Double.parseDouble(vista.txt_totalVenta.getText());
        Venta nuevaVenta = new Venta(idVenta, LocalDate.now(), idCliente, totalVenta);
        
        ventaRepo.guardarVenta(nuevaVenta);
        for (DetalleVenta dv : detallesATransaccionar) {
            detalleRepo.guardarDetalle(dv);
        }

        JOptionPane.showMessageDialog(vista, "¡Venta " + idVenta + " registrada exitosamente en archivos!");
        limpiarTodoFormulario();
    }

    private void limpiarTodoFormulario() {
        modeloCarrito.setRowCount(0);
        modeloSugerencias.setRowCount(0);
        vista.txt_buscarCliente.setText("");
        vista.txt_nombreCliente.setText("");
        vista.txt_idClienteRef.setText("");
        if (vista.txt_cedulaCliente != null) vista.txt_cedulaCliente.setText("");
        vista.txt_codigoProd.setText("");
        vista.txt_cantidadProd.setText("");
        prepararCamposIniciales();
    }

    private void abrirCatalogoProductos() {
        System.out.println("======> ¡MÉTODO INVOCADO DESDE VENTA_SERVICE! <======");
        views.Frm_CatalogoProductos catalogo = new views.Frm_CatalogoProductos(this.productoRepo, producto -> {
            vista.txt_codigoProd.setText(producto.getCodigoProducto());
            vista.txt_cantidadProd.requestFocus();
        });
        catalogo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        catalogo.setLocationRelativeTo(vista);
        catalogo.setVisible(true);
    }
}