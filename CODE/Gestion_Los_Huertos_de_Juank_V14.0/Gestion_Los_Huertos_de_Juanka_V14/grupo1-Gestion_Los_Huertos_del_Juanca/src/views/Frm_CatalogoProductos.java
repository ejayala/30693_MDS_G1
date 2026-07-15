package views;

import controllers.ProductoRepository;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import models.Producto;

public class Frm_CatalogoProductos extends JFrame {
    private ProductoRepository dao;
    private JPanel panelCatalogo;
    private ProductoSelector selector; 

    public Frm_CatalogoProductos(ProductoRepository dao, ProductoSelector selector) {
        this.dao = dao;
        this.selector = selector;
        initComponents();
        cargarCatalogo();
        copiarIconoPrincipal(); // <-- NUEVO: Llamada para jalar el logo del Frm_Principal
        setLocationRelativeTo(null);
        setTitle("Catálogo de Productos");
        setSize(800, 600);
    }

    // --- NUEVO MÉTODO: Copia el icono asignado al Frm_Principal de manera dinámica ---
    private void copiarIconoPrincipal() {
        // Buscamos en todas las ventanas activas la instancia del Frm_Principal para clonar su logo
        for (Window window : Window.getWindows()) {
            if (window instanceof Frm_Principal) {
                Image iconoPrincipal = ((Frm_Principal) window).getIconImage();
                if (iconoPrincipal != null) {
                    this.setIconImage(iconoPrincipal);
                }
                break;
            }
        }
    }

    private void initComponents() {
        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10)); 
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
        panelCatalogo.removeAll(); 
        List<Producto> productos = dao.listarProductos();

        for (Producto p : productos) {
            JPanel tarjeta = new JPanel();
            tarjeta.setLayout(new BorderLayout());
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            tarjeta.setBackground(Color.WHITE);

            JLabel lblNombre = new JLabel(p.getNombreProducto(), SwingConstants.CENTER);
            lblNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblNombre.setForeground(new Color(60, 179, 113));

            JLabel lblImagen;
            if (p.getRutaImagen() != null && !p.getRutaImagen().isEmpty()) {
                java.net.URL imgURL = getClass().getResource(p.getRutaImagen());
                
                if (imgURL != null) {
                    ImageIcon icon = new ImageIcon(imgURL);
                    lblImagen = new JLabel(new ImageIcon(
                        icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                    lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    lblImagen = new JLabel("No encontrada ❌", SwingConstants.CENTER);
                }
            } else {
                lblImagen = new JLabel("Sin imagen 📷", SwingConstants.CENTER);
            }

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

            JPanel panelInferior = new JPanel(new BorderLayout());
            panelInferior.setBackground(Color.WHITE);
            panelInferior.add(txtCaracteristicas, BorderLayout.CENTER);

            JButton btnSeleccionar = new JButton("Seleccionar ✅");
            btnSeleccionar.setBackground(new Color(0, 153, 153));
            btnSeleccionar.setForeground(Color.WHITE);
            
            if ("Agotado".equalsIgnoreCase(p.getEstadoProducto()) || p.getStockProducto() <= 0) {
                btnSeleccionar.setEnabled(false);
                btnSeleccionar.setText("Agotado ❌");
            }

            btnSeleccionar.addActionListener(e -> {
                if (selector != null) {
                    selector.onProductoSeleccionado(p); 
                }
                this.dispose(); 
            });

            panelInferior.add(btnSeleccionar, BorderLayout.SOUTH);

            tarjeta.add(lblNombre, BorderLayout.NORTH);
            tarjeta.add(lblImagen, BorderLayout.CENTER);
            tarjeta.add(panelInferior, BorderLayout.SOUTH); 

            panelCatalogo.add(tarjeta);
        }

        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
}