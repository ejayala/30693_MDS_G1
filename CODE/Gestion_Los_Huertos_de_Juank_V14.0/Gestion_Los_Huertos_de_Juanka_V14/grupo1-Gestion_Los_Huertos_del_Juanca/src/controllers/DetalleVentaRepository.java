package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import models.DetalleVenta;

public class DetalleVentaRepository {

    private final String archivo = "base_datos/detalle_ventas.txt";

    public boolean guardarDetalle(DetalleVenta detalle) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(
                    detalle.getIdVenta() + ";"
                    + detalle.getCodigoProducto() + ";"
                    + detalle.getNombreProducto() + ";"
                    + detalle.getCantidad() + ";"
                    + detalle.getPrecioUnitario()
            );

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<DetalleVenta> listarDetalles() {
        ArrayList<DetalleVenta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                // Cambiado a < 5 porque guardas 5 columnas
                if (datos.length < 5) continue; 

                lista.add(
                        new DetalleVenta(
                                datos[0], // idVenta
                                datos[1], // codigoProducto
                                datos[2], // nombreProducto
                                Integer.parseInt(datos[3]), // cantidad
                                Double.parseDouble(datos[4]) // precioUnitario
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<DetalleVenta> buscarPorVenta(String idVenta) {
        ArrayList<DetalleVenta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
                // Cambiado a < 5 porque guardas 5 columnas
                if (datos.length < 5) continue; 

                if (datos[0].equals(idVenta)) {
                    lista.add(new DetalleVenta(
                            datos[0], // idVenta
                            datos[1], // codigoProducto
                            datos[2], // nombreProducto
                            Integer.parseInt(datos[3]), // cantidad
                            Double.parseDouble(datos[4]) // precioUnitario
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}