package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Frm_ClientesInactivos extends JFrame {

    public JButton btnBuscar;
    public JButton btnDarBaja;
    public JButton btnDuplicados;
    public JButton btnRestaurar;
    public JComboBox<String> cmbEstado;
    public JTable tblClientes;
    public JTextArea txtHistorial;
    public JTextField txtBusqueda;

    private JButton btnSalir;
    private JLabel lblDescripcion;
    private JLabel lblEstado;
    private JPanel panelPrincipal;
    private JScrollPane scrollHistorial;
    private JScrollPane scrollTabla;

    public Frm_ClientesInactivos() {
        initComponents();
        setTitle("Gestion de Clientes Inactivos");
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        txtBusqueda = new JTextField();
        cmbEstado = new JComboBox<>();
        btnBuscar = new JButton();
        btnDarBaja = new JButton();
        btnRestaurar = new JButton();
        btnDuplicados = new JButton();
        btnSalir = new JButton();
        scrollTabla = new JScrollPane();
        tblClientes = new JTable();
        scrollHistorial = new JScrollPane();
        txtHistorial = new JTextArea();
        lblDescripcion = new JLabel();
        lblEstado = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new Color(235, 248, 238));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 3, true),
                " GESTION DE CLIENTES INACTIVOS ", 0, 0,
                new Font("Segoe UI", Font.BOLD, 13), new Color(0, 102, 0)));

        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescripcion.setText("Identifica clientes inactivos o duplicados, conserva historial y mantiene la lista organizada.");

        txtBusqueda.setHorizontalAlignment(JTextField.CENTER);
        txtBusqueda.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "BUSCAR POR ID O CEDULA"));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Todos", "Activo", "Inactivo"}));
        cmbEstado.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "ESTADO"));

        btnBuscar.setBackground(new Color(0, 102, 0));
        btnBuscar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnBuscar.setForeground(new Color(204, 255, 204));
        btnBuscar.setText("BUSCAR");

        btnDarBaja.setBackground(new Color(0, 102, 0));
        btnDarBaja.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnDarBaja.setForeground(new Color(204, 255, 204));
        btnDarBaja.setText("DAR DE BAJA");

        btnRestaurar.setBackground(new Color(0, 102, 0));
        btnRestaurar.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnRestaurar.setForeground(new Color(204, 255, 204));
        btnRestaurar.setText("RESTAURAR");

        btnDuplicados.setBackground(new Color(0, 102, 0));
        btnDuplicados.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        btnDuplicados.setForeground(new Color(204, 255, 204));
        btnDuplicados.setText("ELIMINAR DUPLICADOS");

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(e -> dispose());

        tblClientes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Estado"}
        ));
        scrollTabla.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "CLIENTES REGISTRADOS"));
        scrollTabla.setViewportView(tblClientes);

        txtHistorial.setColumns(20);
        txtHistorial.setRows(5);
        txtHistorial.setEditable(false);
        txtHistorial.setLineWrap(true);
        txtHistorial.setWrapStyleWord(true);
        txtHistorial.setText("Seleccione un cliente para visualizar su historial de compras/ventas.");
        scrollHistorial.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 0), 2, true),
                "HISTORIAL DE COMPRAS / VENTAS"));
        scrollHistorial.setViewportView(txtHistorial);

        lblEstado.setText("Estado inicial: tabla cargada, detalle vacio hasta seleccionar un registro.");

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
                                                .addComponent(cmbEstado, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnBuscar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnDarBaja)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnRestaurar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnDuplicados))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(scrollHistorial))
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
                                        .addComponent(cmbEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnBuscar)
                                                .addComponent(btnDarBaja)
                                                .addComponent(btnRestaurar)
                                                .addComponent(btnDuplicados)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollHistorial, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
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
