package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.Venta;
import models.DetalleVenta;
import models.Cliente;
import controllers.VentaRepository;
import controllers.DetalleVentaRepository;
import controllers.ClienteRepository;
import views.Frm_Principal;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import views.Frm_VistaPreviaPDF;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReporteService {

    private Frm_Principal vista;
    private VentaRepository ventaRepo;
    private DetalleVentaRepository detalleRepo;
    private ClienteRepository clienteRepo;
    
    private DefaultTableModel modeloTablaVentas;
    private boolean limpiandoCalendarios = false;
    private String ultimoIdVentaSeleccionado = ""; // Evita duplicados en ventanas

    public ReporteService(Frm_Principal vista, VentaRepository ventaRepo, DetalleVentaRepository detalleRepo, ClienteRepository clienteRepo) {
        this.vista = vista;
        this.ventaRepo = ventaRepo;
        this.detalleRepo = detalleRepo;
        this.clienteRepo = clienteRepo;

        iniciarComponentesTabla();
        iniciarEventos();
        cargarTablaVentasCompleta();
    }

    private void iniciarComponentesTabla() {
        modeloTablaVentas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        // Estructura A: Desglose analítico total por producto
        modeloTablaVentas.addColumn("ID Venta");
        modeloTablaVentas.addColumn("Fecha");
        modeloTablaVentas.addColumn("Cliente");
        modeloTablaVentas.addColumn("Producto");
        modeloTablaVentas.addColumn("Cantidad"); 
        modeloTablaVentas.addColumn("P. Unitario");
        modeloTablaVentas.addColumn("Subtotal");
        vista.tbl_reporteVentas.setModel(modeloTablaVentas);
    }

    private void iniciarEventos() {
        vista.txt_buscarReporteVenta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarHistorialVentas(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarHistorialVentas(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrarHistorialVentas(); }
        });
        
        vista.tbl_reporteVentas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarNotaDeVentaSeleccionada();
            }
        });
        
        vista.btn_exportarExcel.addActionListener(e -> exportarDatosAExcel());
        vista.btn_filtrarFechas.addActionListener(e -> filtrarVentasPorRangoFechas());
    }

    public void cargarTablaVentasCompleta() {
        ArrayList<Venta> todasLasVentas = ventaRepo.listarVentas();
        procesarYMostrarReporte(todasLasVentas, "");
    }

    private void filtrarHistorialVentas() {
        String criterio = vista.txt_buscarReporteVenta.getText().trim().toLowerCase();
        ArrayList<Venta> todasLasVentas = ventaRepo.listarVentas();
        procesarYMostrarReporte(todasLasVentas, criterio);
    }

    private void filtrarVentasPorRangoFechas() {
        if (limpiandoCalendarios) return;

        Date dateInicio = vista.jd_fechaInicio.getDate();
        Date dateFin = vista.jd_fechaFin.getDate();
        
        if (dateInicio == null || dateFin == null) {
            JOptionPane.showMessageDialog(vista, 
                "Por favor, seleccione ambas fechas (Inicio y Fin) para realizar el filtro.", 
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        LocalDate fechaInicio = dateInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaFin = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        if (fechaInicio.isAfter(fechaFin)) {
            JOptionPane.showMessageDialog(vista, 
                "La fecha de inicio no puede ser posterior a la fecha de fin.", 
                "Rango de Fechas Inválido", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Venta> ventasFiltradas = new ArrayList<>();
        ArrayList<Venta> todasLasVentas = ventaRepo.listarVentas();

        for (Venta v : todasLasVentas) {
            LocalDate fechaVenta = v.getFecha();
            if ((fechaVenta.isEqual(fechaInicio) || fechaVenta.isAfter(fechaInicio)) && 
                (fechaVenta.isEqual(fechaFin) || fechaVenta.isBefore(fechaFin))) {
                ventasFiltradas.add(v);
            }
        }

        procesarYMostrarReporte(ventasFiltradas, "");

        // Limpieza controlada de JCalendar con bandera anti-bucles
        limpiandoCalendarios = true;
        vista.jd_fechaInicio.setDate(null);
        vista.jd_fechaFin.setDate(null);
        limpiandoCalendarios = false;

        if (modeloTablaVentas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, 
                "No se encontraron ventas registradas en el rango de fechas seleccionado.", 
                "Sin Resultados", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void procesarYMostrarReporte(ArrayList<Venta> ventasAProcesar, String criterioCliente) {
        modeloTablaVentas.setRowCount(0);

        for (Venta v : ventasAProcesar) {
            Cliente c = clienteRepo.buscarPorId(v.getIdCliente());
            String nombreCliente = (c != null) ? (c.getNombre() + " " + c.getApellido()) : "Cliente no registrado";

            if (!criterioCliente.isEmpty() && 
                !v.getIdVenta().toLowerCase().contains(criterioCliente) && 
                !nombreCliente.toLowerCase().contains(criterioCliente)) {
                continue;
            }

            ArrayList<DetalleVenta> detalles = detalleRepo.buscarPorVenta(v.getIdVenta());
            for (DetalleVenta d : detalles) {
                double subtotalItem = d.getCantidad() * d.getPrecioUnitario();
                
                modeloTablaVentas.addRow(new Object[]{
                    v.getIdVenta(),
                    v.getFecha().toString(),
                    nombreCliente,
                    d.getNombreProducto(),
                    d.getCantidad(), 
                    String.format("$ %.2f", d.getPrecioUnitario()),
                    String.format("$ %.2f", subtotalItem)
                });
            }
        }
    }

    private void mostrarNotaDeVentaSeleccionada() {
        int fila = vista.tbl_reporteVentas.getSelectedRow();
        if (fila == -1) return;

        String idVenta = modeloTablaVentas.getValueAt(fila, 0).toString();

        // Control estricto: si vuelven a pulsar sobre la misma factura, bloqueamos duplicación
        if (idVenta.equals(ultimoIdVentaSeleccionado)) {
            return; 
        }
        ultimoIdVentaSeleccionado = idVenta;

        ArrayList<Venta> todas = ventaRepo.listarVentas();
        Venta ventaSeleccionada = null;
        for (Venta v : todas) {
            if (v.getIdVenta().equals(idVenta)) {
                ventaSeleccionada = v;
                break;
            }
        }

        if (ventaSeleccionada != null) {
            abrirVistaPreviaFactura(ventaSeleccionada);
        }
    }
    
    private void abrirVistaPreviaFactura(Venta ventaSeleccionada) {
        Cliente cliente = clienteRepo.buscarPorId(ventaSeleccionada.getIdCliente());
        ArrayList<DetalleVenta> detalles = detalleRepo.buscarPorVenta(ventaSeleccionada.getIdVenta());
        
        Frm_VistaPreviaPDF vistaPrevia = new Frm_VistaPreviaPDF(vista, ventaSeleccionada, cliente, detalles);
        
        // Liberar ID de control al exportar
        vistaPrevia.getBtnConfirmarImpresion().addActionListener(e -> {
            System.out.println("Llamando a la librería de iText para guardar el PDF en disco...");
            ultimoIdVentaSeleccionado = ""; 
            vistaPrevia.dispose();
        });

        // Liberar ID de control si cierran el JDialog con la 'X'
        vistaPrevia.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ultimoIdVentaSeleccionado = ""; 
            }
        });

        vistaPrevia.setVisible(true);
    }

    private void exportarDatosAExcel() {
        if (modeloTablaVentas.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "No hay datos en la tabla para exportar.", "Tabla Vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte de Ventas en Excel");
        String userHome = System.getProperty("user.home");
        File documentsDir = new File(userHome, "Documents");
        if (documentsDir.exists()) fileChooser.setCurrentDirectory(documentsDir);

        fileChooser.setFileFilter(new FileNameExtensionFilter("Libro de Excel (*.xlsx)", "xlsx"));
        fileChooser.setSelectedFile(new File("Reporte_Ventas_Huertos_Juank.xlsx"));

        if (fileChooser.showSaveDialog(vista) != JFileChooser.APPROVE_OPTION) return;

        File archivoSeleccionado = fileChooser.getSelectedFile();
        String rutaArchivo = archivoSeleccionado.getAbsolutePath();
        if (!rutaArchivo.toLowerCase().endsWith(".xlsx")) rutaArchivo += ".xlsx";

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // ====================================================================
            // ESTILOS CORPORATIVOS
            // ====================================================================
            XSSFFont fontTitulo = workbook.createFont();
            fontTitulo.setFontName("Arial");
            fontTitulo.setFontHeightInPoints((short) 16);
            fontTitulo.setBold(true);
            fontTitulo.setColor(new XSSFColor(new java.awt.Color(200, 70, 70), null)); // Rojo Corporativo

            XSSFCellStyle styleTitulo = workbook.createCellStyle();
            styleTitulo.setFont(fontTitulo);

            XSSFFont fontHeader = workbook.createFont();
            fontHeader.setFontName("Arial");
            fontHeader.setBold(true);
            fontHeader.setColor(new XSSFColor(new java.awt.Color(255, 255, 255), null));

            XSSFCellStyle styleHeader = workbook.createCellStyle();
            styleHeader.setFont(fontHeader);
            styleHeader.setFillForegroundColor(new XSSFColor(new java.awt.Color(110, 154, 68), null)); // Verde #6E9A44
            styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleHeader.setAlignment(HorizontalAlignment.CENTER);
            styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);

            XSSFCellStyle styleCelda = workbook.createCellStyle();
            styleCelda.setBorderBottom(BorderStyle.THIN);
            styleCelda.setBorderTop(BorderStyle.THIN);
            styleCelda.setBorderRight(BorderStyle.THIN);
            styleCelda.setBorderLeft(BorderStyle.THIN);

            XSSFCellStyle styleMoneda = workbook.createCellStyle();
            styleMoneda.cloneStyleFrom(styleCelda);
            styleMoneda.setDataFormat(workbook.createDataFormat().getFormat("$#,##0.00"));

            XSSFCellStyle styleCentrado = workbook.createCellStyle();
            styleCentrado.cloneStyleFrom(styleCelda);
            styleCentrado.setAlignment(HorizontalAlignment.CENTER);

            // ====================================================================
            // PESTAÑA 1: DETALLE DE VENTAS (Mapeo Estricto de Columnas)
            // ====================================================================
            XSSFSheet sheet1 = workbook.createSheet("Detalle de Ventas");
            sheet1.setDisplayGridlines(true);

            Row rowTitulo = sheet1.createRow(1);
            Cell cellTitulo = rowTitulo.createCell(1);
            cellTitulo.setCellValue("LOS HUERTOS DEL JUANK - REPORTE DE VENTAS");
            cellTitulo.setCellStyle(styleTitulo);

            Row rowSub = sheet1.createRow(2);
            rowSub.createCell(1).setCellValue("Historial analítico detallado por transacciones y productos");

            // Cabeceras exactas de tu JTable
            Row rowHeaders = sheet1.createRow(4);
            for (int i = 0; i < modeloTablaVentas.getColumnCount(); i++) {
                Cell cell = rowHeaders.createCell(i + 1);
                cell.setCellValue(modeloTablaVentas.getColumnName(i));
                cell.setCellStyle(styleHeader);
            }

            // Volcar datos mapeando de forma fija para evitar desfases
            int rowNum = 5;
            for (int i = 0; i < modeloTablaVentas.getRowCount(); i++) {
                Row row = sheet1.createRow(rowNum++);

                // Columna 0: ID Venta (Texto centrado)
                Cell cellId = row.createCell(1);
                cellId.setCellValue(modeloTablaVentas.getValueAt(i, 0).toString());
                cellId.setCellStyle(styleCentrado);

                // Columna 1: Fecha (Texto centrado)
                Cell cellFecha = row.createCell(2);
                cellFecha.setCellValue(modeloTablaVentas.getValueAt(i, 1).toString());
                cellFecha.setCellStyle(styleCentrado);

                // Columna 2: Cliente (Texto)
                Cell cellCliente = row.createCell(3);
                cellCliente.setCellValue(modeloTablaVentas.getValueAt(i, 2).toString());
                cellCliente.setCellStyle(styleCelda);

                // Columna 3: Producto (Texto)
                Cell cellProd = row.createCell(4);
                cellProd.setCellValue(modeloTablaVentas.getValueAt(i, 3).toString());
                cellProd.setCellStyle(styleCelda);

                // Columna 4: Cantidad (Numérico Puro para que Excel sume bien)
                Cell cellCant = row.createCell(5);
                int cantidad = Integer.parseInt(modeloTablaVentas.getValueAt(i, 4).toString());
                cellCant.setCellValue(cantidad);
                cellCant.setCellStyle(styleCentrado);

                // Columna 5: Precio Unitario (Numérico con formato Moneda)
                Cell cellPrecio = row.createCell(6);
                // 🛠️ CORRECCIÓN: Reemplazamos la coma decimal por un punto para no inflar el número
                String precioStr = modeloTablaVentas.getValueAt(i, 5).toString().replace("$", "").trim().replace(",", ".");
                cellPrecio.setCellValue(Double.parseDouble(precioStr));
                cellPrecio.setCellStyle(styleMoneda);

                // Columna 6: Subtotal calculado por Celda Matemática FÓRMULA (=E * F)
                Cell cellSub = row.createCell(7);
                cellSub.setCellFormula("F" + rowNum + "*G" + rowNum); // Fórmula dinámica de Excel
                cellSub.setCellStyle(styleMoneda);
            }

            for (int i = 1; i <= modeloTablaVentas.getColumnCount(); i++) {
                sheet1.autoSizeColumn(i);
            }

            // ====================================================================
            // PESTAÑA 2: ANÁLISIS ACUMULADO POR PRODUCTO Y GRÁFICO REAL
            // ====================================================================
            XSSFSheet sheet2 = workbook.createSheet("Análisis Estadístico");
            sheet2.setDisplayGridlines(true);

            Row rowTitulo2 = sheet2.createRow(1);
            Cell cellTitulo2 = rowTitulo2.createCell(1);
            cellTitulo2.setCellValue("RENDIMIENTO HISTÓRICO DE PRODUCTOS");
            cellTitulo2.setCellStyle(styleTitulo);

            Row rowHeaders2 = sheet2.createRow(4);
            Cell hProd = rowHeaders2.createCell(1); hProd.setCellValue("Producto"); hProd.setCellStyle(styleHeader);
            Cell hCant = rowHeaders2.createCell(2); hCant.setCellValue("Total Unidades"); hCant.setCellStyle(styleHeader);
            Cell hIng = rowHeaders2.createCell(3); hIng.setCellValue("Ingresos Totales"); hIng.setCellStyle(styleHeader);
            
            Map<String, Integer> acumuladoCantidades = new HashMap<>();
            Map<String, Double> acumuladoIngresos = new HashMap<>();

            for (int i = 0; i < modeloTablaVentas.getRowCount(); i++) {
                String producto = modeloTablaVentas.getValueAt(i, 3).toString();
                int cantidad = Integer.parseInt(modeloTablaVentas.getValueAt(i, 4).toString());
                String precioStr = modeloTablaVentas.getValueAt(i, 5).toString().replace("$", "").trim().replace(",", ".");
                double precioUnitario = Double.parseDouble(precioStr);

                acumuladoCantidades.put(producto, acumuladoCantidades.getOrDefault(producto, 0) + cantidad);
                acumuladoIngresos.put(producto, acumuladoIngresos.getOrDefault(producto, 0.0) + (cantidad * precioUnitario));
            }

            int rowNum2 = 5;
            int filaInicioDatos = 6;
            for (String producto : acumuladoCantidades.keySet()) {
                Row row = sheet2.createRow(rowNum2++);

                Cell c1 = row.createCell(1); c1.setCellValue(producto); c1.setCellStyle(styleCelda);
                Cell c2 = row.createCell(2); c2.setCellValue(acumuladoCantidades.get(producto)); c2.setCellStyle(styleCentrado);
                Cell c3 = row.createCell(3); c3.setCellValue(acumuladoIngresos.get(producto)); c3.setCellStyle(styleMoneda);
            }
            int filaFinDatos = rowNum2;

            sheet2.autoSizeColumn(1);
            sheet2.autoSizeColumn(2);
            sheet2.autoSizeColumn(3);

            // ====================================================================
            // 📈 CREACIÓN DEL GRÁFICO DE BARRAS REAL (NATIVO EXCEL)
            // ====================================================================
            XSSFDrawing drawing = sheet2.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 4, 13, 21);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("Unidades Totales Vendidas por Categoría");
            chart.setTitleOverlay(false);

            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("Productos");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("Cantidad Unidades");

            XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(sheet2, new CellRangeAddress(filaInicioDatos - 1, filaFinDatos - 1, 1, 1));
            XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(sheet2, new CellRangeAddress(filaInicioDatos - 1, filaFinDatos - 1, 2, 2));

            // Generar la serie de barras utilizando los valores numéricos corregidos
            XDDFBarChartData chartData = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            chartData.setBarDirection(BarDirection.COL); 

            XDDFBarChartData.Series series = (XDDFBarChartData.Series) chartData.addSeries(categories, values);
            series.setTitle("Unidades", null);

            chart.plot(chartData);

            try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(vista, "✅ Reporte Comercial y Gráfico Estadístico generados con éxito.\nArchivo guardado en: " + rutaArchivo, "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error crítico al escribir el archivo Excel:\n" + ex.getMessage(), "Error de Exportación", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
}