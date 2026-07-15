package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Venta;

public class VentaRepository {

    private final String archivo = "base_datos/ventas.txt";

    public boolean guardarVenta(Venta venta) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(
                    venta.getIdVenta() + ";"
                    + venta.getFecha() + ";"
                    + venta.getIdCliente() + ";"
                    + venta.getTotal()
            );

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Venta> listarVentas() {

        ArrayList<Venta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                if (datos.length < 4) {
                    continue;
                }

                lista.add(
                        new Venta(
                                datos[0],
                                LocalDate.parse(datos[1]),
                                datos[2],
                                Double.parseDouble(datos[3])
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Venta buscarVenta(String idVentaBuscar) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                if (datos.length < 4) {
                    continue;
                }

                if (datos[0].equals(idVentaBuscar)) {

                    return new Venta(
                            datos[0],
                            LocalDate.parse(datos[1]),
                            datos[2],
                            Double.parseDouble(datos[3])
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String generarNuevoCodigo() {
        java.io.File directory = new java.io.File("base_datos");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        java.io.File file = new java.io.File(archivo);
        try {
            if (!file.exists()) {
                file.createNewFile();
                return "V0001";
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        int ultimoID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 1) continue;
                String numStr = datos[0].replaceAll("[^0-9]", "").trim();
                if (!numStr.isEmpty()) {
                    int id = Integer.parseInt(numStr);
                    if (id > ultimoID) ultimoID = id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("V%04d", ultimoID + 1);
    }
}