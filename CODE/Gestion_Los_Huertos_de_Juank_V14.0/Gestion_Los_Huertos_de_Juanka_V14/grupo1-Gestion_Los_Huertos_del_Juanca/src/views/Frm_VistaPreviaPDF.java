package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.Venta;
import models.DetalleVenta;
import models.Cliente;

public class Frm_VistaPreviaPDF extends JDialog {

    private JEditorPane visorHtml;
    private JButton btnConfirmarImpresion;
    private Venta ventaActual;
    private Cliente clienteActual;
    private ArrayList<DetalleVenta> detallesActuales;

    public Frm_VistaPreviaPDF(Window padre, Venta venta, Cliente cliente, ArrayList<DetalleVenta> detalles) {
        super(padre, "Vista Previa de Nota de Venta", ModalityType.APPLICATION_MODAL);
        this.ventaActual = venta;
        this.clienteActual = cliente;
        this.detallesActuales = detalles;
        initComponents();
        copiarIconoPrincipal();
        generarPlantillaFactura(venta, cliente, detalles);
        
        setSize(500, 650);
        setLocationRelativeTo(padre);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        visorHtml = new JEditorPane();
        visorHtml.setEditable(false);
        visorHtml.setContentType("text/html");
        visorHtml.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(visorHtml);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnConfirmarImpresion = new JButton("Exportar a PDF Real 📄");
        btnConfirmarImpresion.setBackground(new Color(0, 102, 102));
        btnConfirmarImpresion.setForeground(Color.WHITE);
        btnConfirmarImpresion.setFont(new Font("SansSerif", Font.BOLD, 13));
        
        btnConfirmarImpresion.addActionListener(e -> exportarPDF());
        
        panelBotones.add(btnConfirmarImpresion);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void copiarIconoPrincipal() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Frm_Principal) {
                java.awt.Image icono = ((Frm_Principal) window).getIconImage();
                if (icono != null) this.setIconImage(icono);
                break;
            }
        }
    }

    private String obtenerFechaLimpia() {
        try {
            Object fechaObj = ventaActual.getFecha();
            
            if (fechaObj instanceof java.util.Date) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
                return sdf.format((java.util.Date) fechaObj);
            }
            
            if (fechaObj instanceof java.time.LocalDate) {
                return ((java.time.LocalDate) fechaObj).toString().replace("-", "");
            }
            
            if (fechaObj instanceof String) {
                String fechaStr = (String) fechaObj;
                return fechaStr.replaceAll("[^0-9]", "");
            }
            
            return String.valueOf(System.currentTimeMillis());
            
        } catch (Exception e) {
            return String.valueOf(System.currentTimeMillis());
        }
    }

    private void exportarPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        
        String userHome = System.getProperty("user.home");
        File documentsDir = new File(userHome, "Documents");
        if (documentsDir.exists()) {
            fileChooser.setCurrentDirectory(documentsDir);
        }
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);
        
        String fechaLimpia = obtenerFechaLimpia();
        String nombreSugerido = "Nota_Venta_" + ventaActual.getIdVenta() + "_" + fechaLimpia + ".pdf";
        fileChooser.setSelectedFile(new File(nombreSugerido));
        
        int resultado = fileChooser.showSaveDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaArchivo = archivoSeleccionado.getAbsolutePath();
            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                rutaArchivo += ".pdf";
            }
            
            try {
                generarPDFReal(rutaArchivo);
                JOptionPane.showMessageDialog(this,
                    "✅ PDF guardado exitosamente en:\n" + rutaArchivo,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "❌ Error al generar el PDF:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private String obtenerFechaString() {
        try {
            Object fechaObj = ventaActual.getFecha();
            
            if (fechaObj instanceof java.util.Date) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                return sdf.format((java.util.Date) fechaObj);
            }
            
            if (fechaObj instanceof java.time.LocalDate) {
                java.time.LocalDate fecha = (java.time.LocalDate) fechaObj;
                return fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
            }
            
            if (fechaObj instanceof String) {
                return (String) fechaObj;
            }
            
            return fechaObj.toString();
            
        } catch (Exception e) {
            return "Fecha no disponible";
        }
    }

    private void generarPDFReal(String rutaArchivo) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
        document.open();

        // ============================================
        // 1. ENCABEZADO - DATOS DEL NEGOCIO
        // ============================================
        URL logoURL = getClass().getResource("/recursos/logo_hjk_230.png");
        com.itextpdf.text.Image logo = null;
        if (logoURL != null) {
            logo = com.itextpdf.text.Image.getInstance(logoURL);
            logo.scaleToFit(40, 40);
        }

        // Tabla para el encabezado (logo + nombre del negocio)
        PdfPTable headerTable = new PdfPTable(logo != null ? 2 : 1);
        headerTable.setWidthPercentage(100);
        headerTable.setSpacingAfter(5);
        
        if (logo != null) {
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerTable.addCell(logoCell);
        }
        
        // Celda con el nombre del negocio
        PdfPCell infoCell = new PdfPCell();
        infoCell.setBorder(Rectangle.NO_BORDER);
        infoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        Paragraph nombreNegocio = new Paragraph("Los Huertos del Juank", 
            FontFactory.getFont("Albertus Nova", 18, Font.BOLD, new com.itextpdf.text.BaseColor(200, 70, 70)));
        infoCell.addElement(nombreNegocio);
        
        // Datos del negocio (dueño)
        Paragraph datosNegocio = new Paragraph("RUC: 1726543210001 | Teléfono: 0998765432\nQuito - Ecuador",
            FontFactory.getFont("Albertus Nova", 9, new com.itextpdf.text.BaseColor(74, 53, 37)));
        infoCell.addElement(datosNegocio);
        
        headerTable.addCell(infoCell);
        document.add(headerTable);

        // Línea separadora
        Paragraph separador = new Paragraph("__________________________________________________");
        separador.setAlignment(Element.ALIGN_CENTER);
        separador.setSpacingAfter(10);
        document.add(separador);

        // ============================================
        // 2. DATOS DEL CLIENTE
        // ============================================
        String nombreCliente = (clienteActual != null) ? (clienteActual.getNombre() + " " + clienteActual.getApellido()) : "Consumidor Final";
        String cedulaCliente = (clienteActual != null) ? clienteActual.getCedula() : "9999999999";
        String direccion = (clienteActual != null) ? clienteActual.getDireccion() : "S/N";
        
        // Título "DATOS DEL CLIENTE"
        Paragraph tituloCliente = new Paragraph("DATOS DEL CLIENTE",
            FontFactory.getFont("Albertus Nova", 11, Font.BOLD, new com.itextpdf.text.BaseColor(74, 53, 37)));
        tituloCliente.setSpacingBefore(5);
        tituloCliente.setSpacingAfter(5);
        document.add(tituloCliente);
        
        // Tabla de datos del cliente
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingAfter(15);
        
        infoTable.addCell(createInfoCell("Cliente:", nombreCliente));
        infoTable.addCell(createInfoCell("Cédula/RUC:", cedulaCliente));
        infoTable.addCell(createInfoCell("Dirección:", direccion));
        infoTable.addCell(createInfoCell("Fecha:", obtenerFechaString()));
        
        document.add(infoTable);

        // ============================================
        // 3. TABLA DE PRODUCTOS
        // ============================================
        Paragraph tituloProductos = new Paragraph("DETALLE DE PRODUCTOS",
            FontFactory.getFont("Albertus Nova", 11, Font.BOLD, new com.itextpdf.text.BaseColor(74, 53, 37)));
        tituloProductos.setSpacingBefore(10);
        tituloProductos.setSpacingAfter(5);
        document.add(tituloProductos);

        PdfPTable productosTable = new PdfPTable(4);
        productosTable.setWidthPercentage(100);
        productosTable.setSpacingAfter(15);
        productosTable.setWidths(new float[]{40f, 15f, 20f, 25f});
        
        String[] headers = {"Producto", "Cant.", "P. Unit", "Subtotal"};
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, 
                FontFactory.getFont("Albertus Nova", 10, Font.BOLD, com.itextpdf.text.BaseColor.WHITE)));
            headerCell.setBackgroundColor(new com.itextpdf.text.BaseColor(110, 154, 68));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(5);
            productosTable.addCell(headerCell);
        }
        
        for (DetalleVenta d : detallesActuales) {
            double sub = d.getCantidad() * d.getPrecioUnitario();
            
            PdfPCell cellProducto = new PdfPCell(new Phrase(d.getNombreProducto(), 
                FontFactory.getFont("Albertus Nova", 9)));
            cellProducto.setPadding(5);
            productosTable.addCell(cellProducto);
            
            PdfPCell cellCantidad = new PdfPCell(new Phrase(String.valueOf(d.getCantidad()), 
                FontFactory.getFont("Albertus Nova", 9)));
            cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCantidad.setPadding(5);
            productosTable.addCell(cellCantidad);
            
            PdfPCell cellPrecio = new PdfPCell(new Phrase(String.format("$ %.2f", d.getPrecioUnitario()), 
                FontFactory.getFont("Albertus Nova", 9)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellPrecio.setPadding(5);
            productosTable.addCell(cellPrecio);
            
            PdfPCell cellSubtotal = new PdfPCell(new Phrase(String.format("$ %.2f", sub), 
                FontFactory.getFont("Albertus Nova", 9)));
            cellSubtotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellSubtotal.setPadding(5);
            productosTable.addCell(cellSubtotal);
        }
        
        document.add(productosTable);

        // ============================================
        // 4. TOTAL
        // ============================================
        Paragraph total = new Paragraph("TOTAL A PAGAR: $ " + String.format("%.2f", ventaActual.getTotal()),
            FontFactory.getFont("Albertus Nova", 16, Font.BOLD, new com.itextpdf.text.BaseColor(200, 70, 70)));
        total.setAlignment(Element.ALIGN_RIGHT);
        total.setSpacingBefore(10);
        document.add(total);

        // Línea separadora
        Paragraph separador2 = new Paragraph("__________________________________________________");
        separador2.setAlignment(Element.ALIGN_CENTER);
        separador2.setSpacingBefore(10);
        document.add(separador2);

        // ============================================
        // 5. PIE DE PÁGINA
        // ============================================
        Paragraph footer = new Paragraph("¡Gracias por apoyar a la economía local!",
            FontFactory.getFont("Albertus Nova", 10, new com.itextpdf.text.BaseColor(74, 53, 37)));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(10);
        document.add(footer);

        document.close();
    }

    private PdfPCell createInfoCell(String label, String value) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(2);
        
        Paragraph p = new Paragraph();
        p.add(new Phrase(label + " ", FontFactory.getFont("Albertus Nova", 10, Font.BOLD, new com.itextpdf.text.BaseColor(74, 53, 37))));
        p.add(new Phrase(value, FontFactory.getFont("Albertus Nova", 10, new com.itextpdf.text.BaseColor(74, 53, 37))));
        
        cell.addElement(p);
        return cell;
    }

    private void generarPlantillaFactura(Venta venta, Cliente cliente, ArrayList<DetalleVenta> detalles) {
        String nombreCliente = (cliente != null) ? (cliente.getNombre() + " " + cliente.getApellido()) : "Consumidor Final";
        String cedulaCliente = (cliente != null) ? cliente.getCedula() : "9999999999";
        String direccion = (cliente != null) ? cliente.getDireccion() : "S/N";

        URL logoURL = getClass().getResource("/recursos/logo_hjk_230.png");
        String logoPath = (logoURL != null) ? logoURL.toString() : "";

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head><style>");
        html.append("body { font-family: 'Albertus Nova', 'SansSerif', sans-serif; margin: 15px; color: #4A3525; background-color: #F4EBD4; }");
        html.append(".header { text-align: center; border-bottom: 3px solid #6E9A44; padding-bottom: 10px; }");
        html.append(".logo-container { display: flex; align-items: center; justify-content: center; gap: 8px; margin-bottom: 5px; }");
        html.append(".logo-img { height: 25px; width: 25px; object-fit: contain; }");
        html.append(".nombre-negocio { font-family: 'Albertus Nova', 'SansSerif', sans-serif; font-size: 22px; font-weight: bold; color: #C84646; white-space: nowrap; }");
        html.append(".sub-header { font-size: 11px; color: #4A3525; }");
        html.append(".info-cliente { margin-top: 15px; width: 100%; font-size: 12px; border: 1px solid #6E9A44; padding: 8px; background-color: #F4EBD4; }");
        html.append(".info-cliente td { padding: 2px 0; }");
        html.append(".titulo-cliente { font-size: 13px; font-weight: bold; color: #6E9A44; margin-top: 15px; }");
        html.append(".tabla-productos { width: 100%; border-collapse: collapse; margin-top: 20px; font-size: 12px; }");
        html.append(".tabla-productos th { background-color: #6E9A44; color: white; padding: 8px; text-align: left; }");
        html.append(".tabla-productos td { padding: 6px; border-bottom: 1px solid #4A3525; }");
        html.append(".tabla-productos tr:nth-child(even) { background-color: #F4EBD4; }");
        html.append(".total-container { text-align: right; margin-top: 15px; font-size: 16px; font-weight: bold; color: #C84646; padding: 10px; border-top: 2px solid #6E9A44; }");
        html.append(".footer { text-align: center; margin-top: 30px; font-size: 11px; color: #4A3525; }");
        html.append("</style></head>");
        html.append("<body>");

        // ============================================
        // 1. ENCABEZADO - DATOS DEL NEGOCIO
        // ============================================
        html.append("<div class='header'>");
        html.append("  <div class='logo-container'>");
        if (!logoPath.isEmpty()) {
            html.append("    <img src='").append(logoPath).append("' class='logo-img' alt='Logo Huertos del Juank' />");
        }
        html.append("    <span class='nombre-negocio'>Los Huertos del Juank</span>");
        html.append("  </div>");
        html.append("  <div class='sub-header'>RUC: 1726543210001 | Teléfono: 0998765432</div>");
        html.append("  <div class='sub-header'>Quito - Ecuador</div>");
        html.append("</div>");

        // ============================================
        // 2. DATOS DEL CLIENTE
        // ============================================
        html.append("<div class='titulo-cliente'>DATOS DEL CLIENTE</div>");
        html.append("<table class='info-cliente'>");
        html.append("  <tr><td><b>Cliente:</b> ").append(nombreCliente).append("</td>");
        html.append("      <td align='right'><b>Fecha:</b> ").append(obtenerFechaString()).append("</td></tr>");
        html.append("  <tr><td><b>Cédula/RUC:</b> ").append(cedulaCliente).append("</td>");
        html.append("      <td align='right'><b>Dirección:</b> ").append(direccion).append("</td></tr>");
        html.append("</table>");

        // ============================================
        // 3. TABLA DE PRODUCTOS
        // ============================================
        html.append("<div style='font-weight: bold; color: #6E9A44; margin-top: 15px;'>DETALLE DE PRODUCTOS</div>");
        html.append("<table class='tabla-productos'>");
        html.append("  <tr><th>Producto</th><th align='center'>Cant.</th><th>P. Unit</th><th>Subtotal</th></tr>");

        for (DetalleVenta d : detalles) {
            double sub = d.getCantidad() * d.getPrecioUnitario();
            html.append("  <tr>");
            html.append("    <td>").append(d.getNombreProducto()).append("</td>");
            html.append("    <td align='center'>").append(d.getCantidad()).append("</td>");
            html.append("    <td>$ ").append(String.format("%.2f", d.getPrecioUnitario())).append("</td>");
            html.append("    <td>$ ").append(String.format("%.2f", sub)).append("</td>");
            html.append("  </tr>");
        }
        html.append("</table>");

        // ============================================
        // 4. TOTAL
        // ============================================
        html.append("<div class='total-container'>");
        html.append("  TOTAL A PAGAR: $ ").append(String.format("%.2f", venta.getTotal()));
        html.append("</div>");

        // ============================================
        // 5. PIE DE PÁGINA
        // ============================================
        html.append("<div class='footer'>¡Gracias por apoyar a la economía local!</div>");
        html.append("</body></html>");

        visorHtml.setText(html.toString());
    }
    
    public JButton getBtnConfirmarImpresion() {
        return btnConfirmarImpresion;
    }
}