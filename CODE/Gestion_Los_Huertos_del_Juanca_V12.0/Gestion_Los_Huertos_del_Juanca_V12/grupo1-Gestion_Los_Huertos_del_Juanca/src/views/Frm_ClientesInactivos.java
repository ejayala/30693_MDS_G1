package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Frm_ClientesInactivos extends JFrame {

    public JButton btnBuscar;
    public JButton btnCambiarEstado;
    public JButton btnSalir;
    public JTable tblClientes;
    public JTextField txtBusqueda;

    private JLabel lblDescripcion;
    private JLabel lblEstado;
    private JPanel panelPrincipal;
    private JScrollPane scrollTabla;

    public Frm_ClientesInactivos() {
        initComponents();
        setTitle("Gestion de Clientes Inactivos");
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        txtBusqueda = new JTextField();
        btnBuscar = new JButton();
        btnCambiarEstado = new JButton();
        btnSalir = new JButton();
        scrollTabla = new JScrollPane();
        tblClientes = new JTable();
        lblDescripcion = new JLabel();
        lblEstado = new JLabel();

        // El cierre se controla unicamente desde btnSalir; la X de la ventana no hace nada.
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        panelPrincipal.setBackground(new Color(235, 248, 238));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 3, true),
                " GESTION DE CLIENTES INACTIVOS ", 0, 0,
                new Font("Segoe UI", Font.BOLD, 13), new Color(0, 102, 0)));

        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescripcion.setText("Muestra los clientes inactivos registrados y permite cambiar su estado.");

        txtBusqueda.setHorizontalAlignment(JTextField.CENTER);
        txtBusqueda.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "BUSCAR POR ID O CEDULA"));

        btnBuscar.setBackground(new Color(0, 102, 0));
        btnBuscar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnBuscar.setForeground(new Color(204, 255, 204));
        btnBuscar.setText("BUSCAR");

        btnCambiarEstado.setBackground(new Color(0, 102, 0));
        btnCambiarEstado.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnCambiarEstado.setForeground(new Color(204, 255, 204));
        btnCambiarEstado.setText("CAMBIAR ESTADO");

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(e -> dispose());

        tblClientes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Estado"}
        ));
        scrollTabla.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "CLIENTES REGISTRADOS"));
        scrollTabla.setViewportView(tblClientes);

        lblEstado.setText("Estado inicial: se muestran unicamente los clientes inactivos.");

        GroupLayout panelPrincipalLayout = new GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
                panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(lblDescripcion, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSalir))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(txtBusqueda, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnBuscar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCambiarEstado))
                                        .addComponent(scrollTabla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEstado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
                panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSalir)
                                        .addComponent(lblDescripcion))
                                .addGap(12, 12, 12)
                                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnBuscar)
                                                .addComponent(btnCambiarEstado)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEstado)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }
}
