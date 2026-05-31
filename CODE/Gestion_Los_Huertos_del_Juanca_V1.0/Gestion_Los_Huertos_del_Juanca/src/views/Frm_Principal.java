/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;


import javax.swing.JOptionPane;

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
        tbl_productos = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
        txt_IDprodbuscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_salida = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 3, true), " LOS HUERTOS DEL JUANCA     ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Verdana", 1, 13), new java.awt.Color(204, 0, 0))); // NOI18N

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

        tbl_productos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "PRODUCTOS  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_productos.setViewportView(jTable1);

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
        txt_IDprodbuscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true), "INGRESE ID A BUSCAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_modificar)
                                .addGap(44, 44, 44)
                                .addComponent(btn_eliminar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_IDprodbuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buscar)
                                .addGap(6, 6, 6)))
                        .addGap(90, 90, 90))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_guardar)
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
                                    .addComponent(txt_codproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)))
                .addComponent(tbl_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
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
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbl_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tablaModulos.addTab("PRODUCTOS", null, jPanel2, "");

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 51), 3, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(684, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        tablaModulos.addTab("CLIENTES", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 3, true));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1172, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );

        tablaModulos.addTab("VENTAS", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 3, true));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1172, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
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
                        .addComponent(btn_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Frm_Principal().setVisible(true));
    }
    
    
    void cerrarVentana(){
        int respuesta=JOptionPane.showConfirmDialog(this,"¿Está seguro que desea salir?","Confirmar Salida",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(respuesta==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JRadioButton btn_disponible;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JRadioButton btn_nodisponible;
    private javax.swing.JToggleButton btn_salida;
    private javax.swing.ButtonGroup btns_estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JSpinner spn_stock;
    private javax.swing.JTabbedPane tablaModulos;
    private javax.swing.JScrollPane tbl_productos;
    private javax.swing.JTextField txt_IDprodbuscar;
    private javax.swing.JTextField txt_codproducto;
    private javax.swing.JTextField txt_idproducto;
    private javax.swing.JTextField txt_nomproducto;
    private javax.swing.JTextField txt_precioVenta;
    private javax.swing.JTextField txt_tmproducto;
    private javax.swing.JTextField txt_tpempaque;
    // End of variables declaration//GEN-END:variables
}
