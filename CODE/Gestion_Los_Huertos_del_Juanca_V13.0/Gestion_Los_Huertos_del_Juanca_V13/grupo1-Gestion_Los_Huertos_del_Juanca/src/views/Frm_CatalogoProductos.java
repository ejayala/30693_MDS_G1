/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controllers.ProductoRepository;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import models.Producto;

/**
 *
 * @author User
 */
public class Frm_CatalogoProductos  extends JFrame {
     private ProductoRepository dao;
    private JPanel panelCatalogo;

    public Frm_CatalogoProductos(ProductoRepository dao) {
        this.dao = dao;
        initComponents();
        cargarCatalogo();
        setLocationRelativeTo(null);
        setTitle("Catálogo de Productos");
        setSize(800, 600);
    }

    private void initComponents() {
        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columnas
        panelCatalogo.setBackground(new Color(240, 248, 255));

        JScrollPane scroll = new JScrollPane(panelCatalogo);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().setLayout(new BorderLayout());
        JLabel lblTitulo = new JLabel("CATÁLOGO DE PRODUCTOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 102, 102));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(lblTitulo, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void cargarCatalogo() {
        panelCatalogo.removeAll(); // limpiar antes de cargar
        List<Producto> productos = dao.listarProductos();

        for (Producto p : productos) {
            JPanel tarjeta = new JPanel();
            tarjeta.setLayout(new BorderLayout());
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            tarjeta.setBackground(Color.WHITE);

            // Nombre
            JLabel lblNombre = new JLabel(p.getNombreProducto(), SwingConstants.CENTER);
            lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblNombre.setForeground(new Color(60, 179, 113));

            // Imagen
            JLabel lblImagen;
            if (p.getRutaImagen() != null && !p.getRutaImagen().isEmpty()) {
                ImageIcon icon = new ImageIcon(p.getRutaImagen());
                lblImagen = new JLabel(new ImageIcon(
                    icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
            } else {
                lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
            }

            // Características
            JTextArea txtCaracteristicas = new JTextArea();
            txtCaracteristicas.setEditable(false);
            txtCaracteristicas.setBackground(Color.WHITE);
            txtCaracteristicas.setFont(new Font("SansSerif", Font.PLAIN, 12));
            txtCaracteristicas.setText(
                "Código: " + p.getCodigoProducto() + "\n" +
                "Empaque: " + p.getTipoEmpaque() + "\n" +
                "Tamaño: " + p.getTamanoProducto() + "\n" +
                "Precio: $" + p.getPrecioVenta() + "\n" +
                "Stock: " + p.getStockProducto() + "\n" +
                "Estado: " + p.getEstadoProducto()
            );

            tarjeta.add(lblNombre, BorderLayout.NORTH);
            tarjeta.add(lblImagen, BorderLayout.CENTER);
            tarjeta.add(txtCaracteristicas, BorderLayout.SOUTH);

            panelCatalogo.add(tarjeta);
        }

        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
    
}
