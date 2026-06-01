/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

/**
 *
 * @author User
 */
public class ProductoRepository {
    private final String archivo = "base_datos/productos.txt";

    public ProductoRepository() {
        try {
            File file = new File(archivo);
            File directorio = file.getParentFile();
            if (directorio != null && !directorio.exists()) {
                directorio.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creando archivo: " + e.getMessage());
        }
    }

    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                productos.add(new Producto(
                    Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4],
                    Double.parseDouble(datos[5]), Integer.parseInt(datos[6]), datos[7]
                ));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
        }
        return productos;
    }

    public void guardar(List<Producto> productos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                pw.println(p.getIdProducto() + ";" + p.getCodigoProducto() + ";" + p.getNombreProducto() + ";" +
                           p.getTipoEmpaque() + ";" + p.getTamanoProducto() + ";" +
                           p.getPrecioVenta() + ";" + p.getStockProducto() + ";" + p.getEstadoProducto());
            }
        } catch (IOException e) {
            System.err.println("Error guardando archivo: " + e.getMessage());
        }
    }
}

