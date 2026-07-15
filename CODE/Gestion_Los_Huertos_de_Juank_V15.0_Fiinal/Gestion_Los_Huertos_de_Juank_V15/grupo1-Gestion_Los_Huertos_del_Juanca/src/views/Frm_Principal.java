/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.ClientePotencialRepository;
import controllers.ClientePotencialService;
import controllers.ClienteInactivoService;
import controllers.ClienteRepository;
import controllers.ClienteService;
import controllers.DetalleVentaRepository;
import controllers.ProductoRepository;
import controllers.ProductoService;
import controllers.ReporteService;
import controllers.VentaRepository;
import controllers.VentaService;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ClientePotenciales;
import models.Producto;

/**
 *
 * @author Esteban
 */
public class Frm_Principal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Principal.class.getName());

    // Referencia al controlador de la tabla principal de clientes, para poder
    // refrescarla cuando un cliente cambia de estado desde la ventana de
    // Gestion de Clientes Inactivos.
    private ClienteService clienteService;
    private ReporteService controladorReportes;
    private Runnable onCambioEstado;

    /**
     * Creates new form Frm_Principal
     */
    public Frm_Principal() {
        initComponents();
        try {
            this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/logo_hjk_230.png")).getImage());
        } catch (Exception e) {
            System.err.println("No se pudo cargar el ícono de la ventana: " + e.getMessage());
        }
        
        ClienteRepository clienteRepo = new ClienteRepository();
        ProductoRepository productoRepo = new ProductoRepository();
        VentaRepository ventaRepo = new VentaRepository();
        DetalleVentaRepository detalleRepo = new DetalleVentaRepository();
        
        this.clienteService = new ClienteService(this, clienteRepo);
        new ProductoService(this, productoRepo);
        new VentaService(this, ventaRepo, detalleRepo, productoRepo, clienteRepo);
        
        // 🛠️ CORRECCIÓN: Asignamos la instancia a la variable de clase controladorReportes
        this.controladorReportes = new ReporteService(this, ventaRepo, detalleRepo, clienteRepo);
        
        this.onCambioEstado = () -> {
            if (this.clienteService != null) {
                this.clienteService.cargarTabla(); // Se ejecutará sin tirar NullPointerException
            }
        };
        
        this.tbp_pestanas.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                // Obtenemos el índice de la pestaña seleccionada
                int pestañaSeleccionada = tbp_pestanas.getSelectedIndex();
                
                // Si es la pestaña 3 (Reportes) y ya está inicializado el controlador, recargamos
                if (pestañaSeleccionada == 3 && controladorReportes != null) { 
                    controladorReportes.cargarTablaVentasCompleta(); 
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btns_estado = new javax.swing.ButtonGroup();
        btns_estadocl = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tbp_pestanas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txt_idproducto = new javax.swing.JTextField();
        txt_codproducto = new javax.swing.JTextField();
        txt_precioVenta = new javax.swing.JTextField();
        btn_disponible = new javax.swing.JRadioButton();
        btn_nodisponible = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        spn_stock = new javax.swing.JSpinner();
        btn_guardar = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tbl_productos = new javax.swing.JTable();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        txt_IDprodbuscar = new javax.swing.JTextField();
        cmb_nomproducto = new javax.swing.JComboBox<>();
        cmb_tmproducto = new javax.swing.JComboBox<>();
        cmb_tpempaque = new javax.swing.JComboBox<>();
        btn_imagen = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_IDcliente = new javax.swing.JTextField();
        txt_nomcliente = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_apecliente = new javax.swing.JTextField();
        txt_cedula = new javax.swing.JTextField();
        btn_activo = new javax.swing.JRadioButton();
        btn_inactivo = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        btn_guardarcli = new javax.swing.JButton();
        btn_modificarcli = new javax.swing.JButton();
        btn_eliminarcli = new javax.swing.JButton();
        txt_IDclibuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        btn_clientesPotenciales = new javax.swing.JButton();
        btn_clientesInactivos = new javax.swing.JButton();
        jd_fechaCumpleanios = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        btn_verCatalogo = new javax.swing.JButton();
        txt_buscarCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sugerenciasClientes = new javax.swing.JTable();
        txt_totalVenta = new javax.swing.JTextField();
        txt_idVenta = new javax.swing.JTextField();
        txt_cedulaCliente = new javax.swing.JTextField();
        txt_idClienteRef = new javax.swing.JTextField();
        txt_nombreCliente = new javax.swing.JTextField();
        txt_fechaVenta = new javax.swing.JTextField();
        txt_codigoProd = new javax.swing.JTextField();
        txt_cantidadProd = new javax.swing.JTextField();
        btn_agregarProducto = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_carrito = new javax.swing.JTable();
        btn_quitarProducto = new javax.swing.JButton();
        btn_finalizarVenta = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txt_buscarReporteVenta = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_reporteVentas = new javax.swing.JTable();
        btn_exportarExcel = new javax.swing.JButton();
        jd_fechaInicio = new com.toedter.calendar.JDateChooser();
        jd_fechaFin = new com.toedter.calendar.JDateChooser();
        btn_filtrarFechas = new javax.swing.JButton();
        btn_salida = new javax.swing.JToggleButton();
        bnt_minimizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Los Huertos del JuanK");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(74, 53, 37), 3, true), " LOS HUERTOS DEL JUANK     ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Albertus Nova Black", 1, 13), new java.awt.Color(74, 53, 37))); // NOI18N

        tbp_pestanas.setBackground(new java.awt.Color(255, 255, 255));
        tbp_pestanas.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0)));
        tbp_pestanas.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(200, 70, 70));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 2, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(1180, 430));

        txt_idproducto.setBackground(new java.awt.Color(200, 70, 70));
        txt_idproducto.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_idproducto.setForeground(new java.awt.Color(255, 255, 255));
        txt_idproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "ID PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txt_idproducto.addActionListener(this::txt_idproductoActionPerformed);

        txt_codproducto.setBackground(new java.awt.Color(200, 72, 70));
        txt_codproducto.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_codproducto.setForeground(new java.awt.Color(255, 255, 255));
        txt_codproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CODIGO DE PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_precioVenta.setBackground(new java.awt.Color(200, 72, 70));
        txt_precioVenta.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_precioVenta.setForeground(new java.awt.Color(255, 255, 255));
        txt_precioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_precioVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "PRECIO DE VENTA  ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txt_precioVenta.addActionListener(this::txt_precioVentaActionPerformed);

        btn_disponible.setBackground(new java.awt.Color(200, 72, 70));
        btns_estado.add(btn_disponible);
        btn_disponible.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_disponible.setForeground(new java.awt.Color(255, 255, 255));
        btn_disponible.setText("DISPONIBLE");

        btn_nodisponible.setBackground(new java.awt.Color(200, 72, 70));
        btns_estado.add(btn_nodisponible);
        btn_nodisponible.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_nodisponible.setForeground(new java.awt.Color(255, 255, 255));
        btn_nodisponible.setText("NO DISPONIBLE");
        btn_nodisponible.addActionListener(this::btn_nodisponibleActionPerformed);

        jLabel1.setBackground(new java.awt.Color(200, 72, 70));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ESTADO:");

        spn_stock.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        spn_stock.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        spn_stock.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "STOCK  ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        btn_guardar.setBackground(new java.awt.Color(153, 0, 0));
        btn_guardar.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 204, 204));
        btn_guardar.setText("GUARDAR");
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "PRODUCTOS  ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_productos.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        tbl_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane.setViewportView(tbl_productos);

        btn_modificar.setBackground(new java.awt.Color(153, 0, 0));
        btn_modificar.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_modificar.setForeground(new java.awt.Color(255, 204, 204));
        btn_modificar.setText("MODIFICAR");

        btn_eliminar.setBackground(new java.awt.Color(153, 0, 0));
        btn_eliminar.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(255, 204, 204));
        btn_eliminar.setText("ELIMINAR");

        txt_IDprodbuscar.setBackground(new java.awt.Color(200, 72, 70));
        txt_IDprodbuscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_IDprodbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_IDprodbuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), " INGRESE NOMBRE DE PRODUCTO A BUSCAR ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        cmb_nomproducto.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        cmb_nomproducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Frutilla", "Arándanos", "Frambuesa", "Miel" }));
        cmb_nomproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 72, 70), 2, true), "NOMBRE DE PRODUCTO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 72, 70))); // NOI18N

        cmb_tmproducto.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        cmb_tmproducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Extra Grande", "Grande", "Media", "Pequeña", " " }));
        cmb_tmproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 72, 70), 2, true), "TAMAÑO DE  PRODUCTO ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 72, 70))); // NOI18N
        cmb_tmproducto.addActionListener(this::cmb_tmproductoActionPerformed);

        cmb_tpempaque.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        cmb_tpempaque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Tarrina 500 gr", "Tarrina 125 gr", "Balde 12 kg", "Frasco 100 ml" }));
        cmb_tpempaque.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 72, 70), 2, true), "TIPO DE EMPAQUE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 72, 70))); // NOI18N

        btn_imagen.setBackground(new java.awt.Color(135, 0, 0));
        btn_imagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_imagen.setForeground(new java.awt.Color(255, 204, 204));
        btn_imagen.setLabel("IMAGEN DE PRODUCTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_codproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmb_tmproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmb_nomproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmb_tpempaque, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(btn_disponible))
                        .addGap(110, 110, 110)
                        .addComponent(spn_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_nodisponible)
                        .addGap(75, 75, 75)
                        .addComponent(btn_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btn_guardar)
                        .addGap(34, 34, 34)
                        .addComponent(btn_modificar)
                        .addGap(50, 50, 50)
                        .addComponent(btn_eliminar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txt_IDprodbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txt_codproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_tmproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_nomproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmb_tpempaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(btn_disponible))
                    .addComponent(spn_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_nodisponible)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btn_imagen)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar))
                .addGap(18, 18, 18)
                .addComponent(txt_IDprodbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tbp_pestanas.addTab("PRODUCTOS", null, jPanel2, "");

        jPanel3.setBackground(new java.awt.Color(110, 154, 68));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_IDcliente.setBackground(new java.awt.Color(110, 154, 68));
        txt_IDcliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_IDcliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_IDcliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_IDcliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "ID CLIENTE  ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_IDcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 9, 211, 45));

        txt_nomcliente.setBackground(new java.awt.Color(110, 154, 68));
        txt_nomcliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_nomcliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_nomcliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomcliente.setToolTipText("");
        txt_nomcliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "NOMBRE CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_nomcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 9, 211, 45));

        txt_telefono.setBackground(new java.awt.Color(110, 154, 68));
        txt_telefono.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "TELEFONO CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 211, 45));

        txt_direccion.setBackground(new java.awt.Color(110, 154, 68));
        txt_direccion.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_direccion.setForeground(new java.awt.Color(255, 255, 255));
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "DIRECCIÓN CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 114, 211, 45));

        txt_apecliente.setBackground(new java.awt.Color(110, 154, 68));
        txt_apecliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_apecliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_apecliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_apecliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "APELLIDO CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_apecliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 60, 211, 45));

        txt_cedula.setBackground(new java.awt.Color(110, 154, 68));
        txt_cedula.setFont(new java.awt.Font("Segoe UI Emoji", 1, 13)); // NOI18N
        txt_cedula.setForeground(new java.awt.Color(255, 255, 255));
        txt_cedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cedula.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CEDULA CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 66, 211, -1));

        btns_estadocl.add(btn_activo);
        btn_activo.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_activo.setForeground(new java.awt.Color(255, 255, 255));
        btn_activo.setText("ACTIVO");
        jPanel3.add(btn_activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        btns_estadocl.add(btn_inactivo);
        btn_inactivo.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_inactivo.setForeground(new java.awt.Color(255, 255, 255));
        btn_inactivo.setText("INACTIVO");
        btn_inactivo.addActionListener(this::btn_inactivoActionPerformed);
        jPanel3.add(btn_inactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ESTADO:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        btn_guardarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_guardarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_guardarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_guardarcli.setText("GUARDAR");
        jPanel3.add(btn_guardarcli, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        btn_modificarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_modificarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_modificarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_modificarcli.setText("MODIFICAR");
        jPanel3.add(btn_modificarcli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        btn_eliminarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_eliminarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_eliminarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_eliminarcli.setText("ELIMINAR");
        jPanel3.add(btn_eliminarcli, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        txt_IDclibuscar.setBackground(new java.awt.Color(110, 154, 68));
        txt_IDclibuscar.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_IDclibuscar.setForeground(new java.awt.Color(255, 255, 255));
        txt_IDclibuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), " INGRESE NOMBRE O APELLIDO DE CLIENTE A BUSCAR   ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.add(txt_IDclibuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 400, 50));

        jScrollPane1.setBackground(new java.awt.Color(110, 154, 68));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CLIENTES   ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_clientes.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        tbl_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_clientes);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 9, 671, 400));

        btn_clientesPotenciales.setBackground(new java.awt.Color(0, 102, 0));
        btn_clientesPotenciales.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_clientesPotenciales.setForeground(new java.awt.Color(204, 255, 204));
        btn_clientesPotenciales.setText("CLIENTES POTENCIALES");
        btn_clientesPotenciales.addActionListener(this::btn_clientesPotencialesActionPerformed);
        jPanel3.add(btn_clientesPotenciales, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        btn_clientesInactivos.setBackground(new java.awt.Color(0, 102, 0));
        btn_clientesInactivos.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_clientesInactivos.setForeground(new java.awt.Color(204, 255, 204));
        btn_clientesInactivos.setText("CLIENTES INACTIVOS");
        btn_clientesInactivos.addActionListener(this::btn_clientesInactivosActionPerformed);
        jPanel3.add(btn_clientesInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        jd_fechaCumpleanios.setBackground(new java.awt.Color(110, 154, 68));
        jd_fechaCumpleanios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "FECHA DE CUMPLEAÑOS", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jd_fechaCumpleanios.setForeground(new java.awt.Color(110, 154, 68));
        jPanel3.add(jd_fechaCumpleanios, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 211, 50));

        tbp_pestanas.addTab("CLIENTES", jPanel3);

        jPanel4.setBackground(new java.awt.Color(75, 119, 154));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 3, true));

        btn_verCatalogo.setBackground(new java.awt.Color(0, 153, 153));
        btn_verCatalogo.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_verCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        btn_verCatalogo.setText("CATALOGO DE PRODUCTOS");
        btn_verCatalogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        txt_buscarCliente.setBackground(new java.awt.Color(75, 119, 154));
        txt_buscarCliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_buscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_buscarCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_buscarCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "BUSQUEDA POR NOMBRE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CLIENTES", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_sugerenciasClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_sugerenciasClientes);

        txt_totalVenta.setEditable(false);
        txt_totalVenta.setBackground(new java.awt.Color(75, 119, 154));
        txt_totalVenta.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txt_totalVenta.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_totalVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), " TOTAL A PAGAR", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_idVenta.setBackground(new java.awt.Color(75, 119, 154));
        txt_idVenta.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txt_idVenta.setForeground(new java.awt.Color(255, 255, 255));
        txt_idVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "ID VENTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_cedulaCliente.setBackground(new java.awt.Color(75, 119, 154));
        txt_cedulaCliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_cedulaCliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_cedulaCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cedulaCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CEDULA CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_idClienteRef.setBackground(new java.awt.Color(75, 119, 154));
        txt_idClienteRef.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_idClienteRef.setForeground(new java.awt.Color(255, 255, 255));
        txt_idClienteRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idClienteRef.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "ID CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_nombreCliente.setBackground(new java.awt.Color(75, 119, 154));
        txt_nombreCliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_nombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "NOMBRE CLIENTE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_fechaVenta.setBackground(new java.awt.Color(75, 119, 154));
        txt_fechaVenta.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_fechaVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fechaVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "FECHA DE VENTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_codigoProd.setBackground(new java.awt.Color(75, 119, 154));
        txt_codigoProd.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_codigoProd.setForeground(new java.awt.Color(255, 255, 255));
        txt_codigoProd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigoProd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CODIGO PRODUCTO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txt_codigoProd.addActionListener(this::txt_codigoProdActionPerformed);

        txt_cantidadProd.setBackground(new java.awt.Color(75, 119, 154));
        txt_cantidadProd.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_cantidadProd.setForeground(new java.awt.Color(255, 255, 255));
        txt_cantidadProd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidadProd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "CANTIDAD PRODUCTO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        btn_agregarProducto.setBackground(new java.awt.Color(0, 153, 153));
        btn_agregarProducto.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_agregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btn_agregarProducto.setText("AGREGAR PRODUCTO");
        btn_agregarProducto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "DETALLE DE VENTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        tbl_carrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbl_carrito);

        btn_quitarProducto.setBackground(new java.awt.Color(0, 153, 153));
        btn_quitarProducto.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_quitarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btn_quitarProducto.setText("QUITAR PRODUCTO");
        btn_quitarProducto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        btn_finalizarVenta.setBackground(new java.awt.Color(0, 153, 153));
        btn_finalizarVenta.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_finalizarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btn_finalizarVenta.setText("FINALIZAR VENTA");
        btn_finalizarVenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_idVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(txt_idClienteRef))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_codigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_cedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_fechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_verCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_finalizarVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_quitarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_agregarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idClienteRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_codigoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_totalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_verCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_agregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_quitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_finalizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 51, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbp_pestanas.addTab("VENTAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(244, 235, 212));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 217, 207), 3));

        jPanel6.setBackground(new java.awt.Color(244, 235, 212));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 70, 70), 2, true), "BUSQUEDA DE VENTA", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 70, 70))); // NOI18N

        txt_buscarReporteVenta.setBackground(new java.awt.Color(244, 235, 212));
        txt_buscarReporteVenta.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        txt_buscarReporteVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_buscarReporteVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 70, 70), 2, true), "BUSQUEDA POR NOMBRE", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 70, 70))); // NOI18N

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 70, 70), 2, true), "VENTAS", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 70, 70))); // NOI18N

        tbl_reporteVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbl_reporteVentas);

        btn_exportarExcel.setBackground(new java.awt.Color(110, 154, 68));
        btn_exportarExcel.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_exportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_exportarExcel.setText("EXPORTAR A EXCEL");
        btn_exportarExcel.setBorder(null);
        btn_exportarExcel.addActionListener(this::btn_exportarExcelActionPerformed);

        jd_fechaInicio.setBackground(new java.awt.Color(243, 235, 212));
        jd_fechaInicio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 70, 70), 2, true), "FECHA INICIO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 70, 70))); // NOI18N

        jd_fechaFin.setBackground(new java.awt.Color(244, 235, 212));
        jd_fechaFin.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 70, 70), 2, true), "FECHA FIN", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(200, 70, 70))); // NOI18N

        btn_filtrarFechas.setBackground(new java.awt.Color(110, 154, 68));
        btn_filtrarFechas.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_filtrarFechas.setForeground(new java.awt.Color(255, 255, 255));
        btn_filtrarFechas.setText("FILTRAR POR FECHAS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_buscarReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jd_fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jd_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_filtrarFechas)
                        .addGap(18, 18, 18)
                        .addComponent(btn_exportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_buscarReporteVenta)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jd_fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jd_fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_exportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_filtrarFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbp_pestanas.addTab("REPORTES Y NOTAS DE VENTA", jPanel5);

        btn_salida.setBackground(new java.awt.Color(255, 0, 0));
        btn_salida.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_salida.setForeground(new java.awt.Color(255, 204, 204));
        btn_salida.setText("SALIR");
        btn_salida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 3, true));
        btn_salida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salida.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salida.addActionListener(this::btn_salidaActionPerformed);

        bnt_minimizar.setBackground(new java.awt.Color(0, 23, 181));
        bnt_minimizar.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bnt_minimizar.setForeground(new java.awt.Color(204, 255, 255));
        bnt_minimizar.setText("MINIMIZAR");
        bnt_minimizar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 2, true));
        bnt_minimizar.addActionListener(this::bnt_minimizarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbp_pestanas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bnt_minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_salida, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(bnt_minimizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbp_pestanas, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salidaActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btn_salidaActionPerformed

    private void btn_inactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_inactivoActionPerformed

    private void btn_clientesPotencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesPotencialesActionPerformed
      Frm_CliPotenciales ventanaPotenciales = new Frm_CliPotenciales();
    ClientePotencialRepository daoCP = new ClientePotencialRepository();

    
    ClientePotencialService serviceCP = new ClientePotencialService(
            ventanaPotenciales, daoCP, cp -> clienteService.cargarClientePotencial(cp));

    ventanaPotenciales.setLocationRelativeTo(this);
    ventanaPotenciales.setVisible(true);
    }//GEN-LAST:event_btn_clientesPotencialesActionPerformed

    private void btn_clientesInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesInactivosActionPerformed
        Frm_ClientesInactivos ventanaInactivos = new Frm_ClientesInactivos();
        ClienteRepository daoClientes = new ClienteRepository();
        Runnable onCambioEstado = () -> {
            if (clienteService != null) {
                clienteService.cargarTabla();
            }
        };
        ClienteInactivoService serviceInactivos = new ClienteInactivoService(ventanaInactivos, daoClientes, onCambioEstado);
        ventanaInactivos.setLocationRelativeTo(this);
        ventanaInactivos.setVisible(true);
    }//GEN-LAST:event_btn_clientesInactivosActionPerformed

    private void txt_idproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idproductoActionPerformed

    private void cmb_tmproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_tmproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_tmproductoActionPerformed

    private void btn_nodisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nodisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nodisponibleActionPerformed

    private void txt_precioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioVentaActionPerformed

    private void bnt_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_minimizarActionPerformed
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_bnt_minimizarActionPerformed

    private void txt_codigoProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoProdActionPerformed

    private void btn_exportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_exportarExcelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // El requerimiento estructural: El main solo arranca la vista original
        java.awt.EventQueue.invokeLater(() -> {
            Frm_Principal vista = new Frm_Principal();
            vista.setVisible(true);
        });
    }

    void cerrarVentana() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_minimizar;
    public javax.swing.JRadioButton btn_activo;
    public javax.swing.JButton btn_agregarProducto;
    private javax.swing.JButton btn_clientesInactivos;
    private javax.swing.JButton btn_clientesPotenciales;
    public javax.swing.JRadioButton btn_disponible;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_eliminarcli;
    public javax.swing.JButton btn_exportarExcel;
    public javax.swing.JButton btn_filtrarFechas;
    public javax.swing.JButton btn_finalizarVenta;
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_guardarcli;
    public javax.swing.JButton btn_imagen;
    public javax.swing.JRadioButton btn_inactivo;
    public javax.swing.JButton btn_modificar;
    public javax.swing.JButton btn_modificarcli;
    public javax.swing.JRadioButton btn_nodisponible;
    public javax.swing.JButton btn_quitarProducto;
    private javax.swing.JToggleButton btn_salida;
    public javax.swing.JButton btn_verCatalogo;
    private javax.swing.ButtonGroup btns_estado;
    private javax.swing.ButtonGroup btns_estadocl;
    public javax.swing.JComboBox<String> cmb_nomproducto;
    public javax.swing.JComboBox<String> cmb_tmproducto;
    public javax.swing.JComboBox<String> cmb_tpempaque;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public com.toedter.calendar.JDateChooser jd_fechaCumpleanios;
    public com.toedter.calendar.JDateChooser jd_fechaFin;
    public com.toedter.calendar.JDateChooser jd_fechaInicio;
    public javax.swing.JSpinner spn_stock;
    public javax.swing.JTable tbl_carrito;
    public javax.swing.JTable tbl_clientes;
    public javax.swing.JTable tbl_productos;
    public javax.swing.JTable tbl_reporteVentas;
    public javax.swing.JTable tbl_sugerenciasClientes;
    private javax.swing.JTabbedPane tbp_pestanas;
    public javax.swing.JTextField txt_IDclibuscar;
    public javax.swing.JTextField txt_IDcliente;
    public javax.swing.JTextField txt_IDprodbuscar;
    public javax.swing.JTextField txt_apecliente;
    public javax.swing.JTextField txt_buscarCliente;
    public javax.swing.JTextField txt_buscarReporteVenta;
    public javax.swing.JTextField txt_cantidadProd;
    public javax.swing.JTextField txt_cedula;
    public javax.swing.JTextField txt_cedulaCliente;
    public javax.swing.JTextField txt_codigoProd;
    public javax.swing.JTextField txt_codproducto;
    public javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_fechaVenta;
    public javax.swing.JTextField txt_idClienteRef;
    public javax.swing.JTextField txt_idVenta;
    public javax.swing.JTextField txt_idproducto;
    public javax.swing.JTextField txt_nombreCliente;
    public javax.swing.JTextField txt_nomcliente;
    public javax.swing.JTextField txt_precioVenta;
    public javax.swing.JTextField txt_telefono;
    public javax.swing.JTextField txt_totalVenta;
    // End of variables declaration//GEN-END:variables
}
