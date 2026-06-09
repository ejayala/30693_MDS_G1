/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.ClienteRepository;
import controllers.ClienteService;
import controllers.ProductoRepository;
import controllers.ProductoService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Producto;

/**
 *
 * @author Esteban
 */
public class Frm_Principal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Principal.class.getName());

    /**
     * Creates new form Frm_Principal
     */
    public Frm_Principal() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btns_estado = new javax.swing.ButtonGroup();
        btns_estadocl = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tablaModulos = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txt_idproducto = new javax.swing.JTextField();
        txt_codproducto = new javax.swing.JTextField();
        txt_nomproducto = new javax.swing.JTextField();
        txt_tpempaque = new javax.swing.JTextField();
        txt_tmproducto = new javax.swing.JTextField();
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
        btn_buscar = new javax.swing.JButton();
        txt_IDprodbuscar = new javax.swing.JTextField();
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
        btn_buscarcli = new javax.swing.JButton();
        txt_IDclibuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        jd_fechaCumpleanios = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_salida = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 3, true), " LOS HUERTOS DEL JUANK     ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 1, 13), new java.awt.Color(204, 0, 0))); // NOI18N

        tablaModulos.setBackground(new java.awt.Color(255, 255, 255));
        tablaModulos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 0), new java.awt.Color(204, 0, 0)));
        tablaModulos.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 2, true));

        txt_idproducto.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_idproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "ID PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_codproducto.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_codproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "CODIGO DE PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_nomproducto.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_nomproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "NOMBRE DE PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_tpempaque.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_tpempaque.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tpempaque.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "TIPO DE EMPAQUE  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_tmproducto.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_tmproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tmproducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "TAMAÑO DE PRODUCTO  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        txt_precioVenta.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_precioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_precioVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "PRECIO DE VENTA  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N
        txt_precioVenta.addActionListener(this::txt_precioVentaActionPerformed);

        btns_estado.add(btn_disponible);
        btn_disponible.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_disponible.setForeground(new java.awt.Color(153, 0, 0));
        btn_disponible.setText("DISPONIBLE");

        btns_estado.add(btn_nodisponible);
        btn_nodisponible.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_nodisponible.setForeground(new java.awt.Color(153, 0, 0));
        btn_nodisponible.setText("NO DISPONIBLE");
        btn_nodisponible.addActionListener(this::btn_nodisponibleActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("ESTADO:");

        spn_stock.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        spn_stock.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        spn_stock.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "STOCK  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        btn_guardar.setBackground(new java.awt.Color(153, 0, 0));
        btn_guardar.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 204, 204));
        btn_guardar.setText("GUARDAR");
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "PRODUCTOS  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

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

        btn_buscar.setBackground(new java.awt.Color(153, 0, 0));
        btn_buscar.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 204, 204));
        btn_buscar.setText("BUSCAR");

        txt_IDprodbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_IDprodbuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), " INGRESE ID DE PRODUCTO A BUSCAR   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_nomproducto, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                .addComponent(txt_idproducto, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                            .addComponent(txt_precioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_disponible)
                            .addComponent(btn_nodisponible)
                            .addComponent(jLabel1))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tmproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spn_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tpempaque, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_codproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txt_IDprodbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(btn_buscar))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btn_guardar)
                            .addGap(35, 35, 35)
                            .addComponent(btn_modificar)
                            .addGap(36, 36, 36)
                            .addComponent(btn_eliminar))))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nomproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tpempaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_codproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_precioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tmproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_disponible))
                    .addComponent(spn_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_nodisponible)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_IDprodbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tablaModulos.addTab("PRODUCTOS", null, jPanel2, "");

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));

        txt_IDcliente.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_IDcliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_IDcliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "ID CLIENTE  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        txt_nomcliente.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_nomcliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nomcliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "NOMBRE CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        txt_telefono.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "TELEFONO CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        txt_direccion.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "DIRECCIÓN CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        txt_apecliente.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_apecliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_apecliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "APELLIDO CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        txt_cedula.setFont(new java.awt.Font("Sylfaen", 2, 13)); // NOI18N
        txt_cedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cedula.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "CEDULA CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        btns_estadocl.add(btn_activo);
        btn_activo.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_activo.setForeground(new java.awt.Color(0, 102, 0));
        btn_activo.setText("ACTIVO");

        btns_estadocl.add(btn_inactivo);
        btn_inactivo.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_inactivo.setForeground(new java.awt.Color(0, 102, 0));
        btn_inactivo.setText("INACTIVO");
        btn_inactivo.addActionListener(this::btn_inactivoActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 0));
        jLabel2.setText("ESTADO:");

        btn_guardarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_guardarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_guardarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_guardarcli.setText("GUARDAR");

        btn_modificarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_modificarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_modificarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_modificarcli.setText("MODIFICAR");

        btn_eliminarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_eliminarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_eliminarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_eliminarcli.setText("ELIMINAR");

        btn_buscarcli.setBackground(new java.awt.Color(0, 102, 0));
        btn_buscarcli.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btn_buscarcli.setForeground(new java.awt.Color(204, 255, 204));
        btn_buscarcli.setText("BUSCAR");

        txt_IDclibuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), " INGRESE ID DE CLIENTE A BUSCAR   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "CLIENTES   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

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

        jd_fechaCumpleanios.setBackground(new java.awt.Color(255, 255, 255));
        jd_fechaCumpleanios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 2, true), "FECHA DE CUMPLEAÑOS   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 0))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_IDclibuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buscarcli))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btn_guardarcli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_modificarcli)
                                .addGap(40, 40, 40)
                                .addComponent(btn_eliminarcli)
                                .addGap(45, 45, 45))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_IDcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(txt_nomcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_apecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_inactivo)
                            .addComponent(btn_activo)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jd_fechaCumpleanios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_IDcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nomcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_apecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jd_fechaCumpleanios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_activo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_inactivo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardarcli)
                            .addComponent(btn_modificarcli)
                            .addComponent(btn_eliminarcli))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_IDclibuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_buscarcli))
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        tablaModulos.addTab("CLIENTES", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 3, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        tablaModulos.addTab("VENTAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 3, true));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        tablaModulos.addTab("REPORTES Y NOTAS DE VENTA", jPanel5);

        btn_salida.setBackground(new java.awt.Color(255, 0, 0));
        btn_salida.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btn_salida.setForeground(new java.awt.Color(255, 204, 204));
        btn_salida.setText("SALIR");
        btn_salida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 3, true));
        btn_salida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_salida.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_salida.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_salida.addActionListener(this::btn_salidaActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablaModulos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablaModulos)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txt_precioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioVentaActionPerformed

    private void btn_nodisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nodisponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nodisponibleActionPerformed

    private void btn_inactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_inactivoActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        Frm_Principal vista = new Frm_Principal();
        ClienteRepository dao = new ClienteRepository();
        ClienteService controlador = new ClienteService(vista, dao);
        ProductoRepository daoProducto = new ProductoRepository();
        ProductoService serviceProducto = new ProductoService(vista, daoProducto);
        vista.setVisible(true);
    }

    void cerrarVentana() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton btn_activo;
    public javax.swing.JButton btn_buscar;
    public javax.swing.JButton btn_buscarcli;
    public javax.swing.JRadioButton btn_disponible;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_eliminarcli;
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_guardarcli;
    public javax.swing.JRadioButton btn_inactivo;
    public javax.swing.JButton btn_modificar;
    public javax.swing.JButton btn_modificarcli;
    public javax.swing.JRadioButton btn_nodisponible;
    private javax.swing.JToggleButton btn_salida;
    private javax.swing.ButtonGroup btns_estado;
    private javax.swing.ButtonGroup btns_estadocl;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane;
    public javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jd_fechaCumpleanios;
    public javax.swing.JSpinner spn_stock;
    private javax.swing.JTabbedPane tablaModulos;
    public javax.swing.JTable tbl_clientes;
    public javax.swing.JTable tbl_productos;
    public javax.swing.JTextField txt_IDclibuscar;
    public javax.swing.JTextField txt_IDcliente;
    public javax.swing.JTextField txt_IDprodbuscar;
    public javax.swing.JTextField txt_apecliente;
    public javax.swing.JTextField txt_cedula;
    public javax.swing.JTextField txt_codproducto;
    public javax.swing.JTextField txt_direccion;
    public javax.swing.JTextField txt_idproducto;
    public javax.swing.JTextField txt_nomcliente;
    public javax.swing.JTextField txt_nomproducto;
    public javax.swing.JTextField txt_precioVenta;
    public javax.swing.JTextField txt_telefono;
    public javax.swing.JTextField txt_tmproducto;
    public javax.swing.JTextField txt_tpempaque;
    // End of variables declaration//GEN-END:variables
}
