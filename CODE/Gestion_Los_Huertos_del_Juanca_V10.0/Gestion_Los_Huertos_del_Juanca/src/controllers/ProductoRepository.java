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

    public Producto buscarPorCodigo(String codigoBuscar) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (datos[1].equals(codigoBuscar)) {
                    return new Producto(
                        Integer.parseInt(datos[0]), datos[1], datos[2],
                        datos[3], datos[4], Double.parseDouble(datos[5]),
                        Integer.parseInt(datos[6]), datos[7]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                lista.add(new Producto(
                    Integer.parseInt(datos[0]), datos[1], datos[2],
                    datos[3], datos[4], Double.parseDouble(datos[5]),
                    Integer.parseInt(datos[6]), datos[7]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardarProducto(Producto p) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(p.getIdProducto() + ";" + p.getCodigoProducto() + ";"
                + p.getNombreProducto() + ";" + p.getTipoEmpaque() + ";"
                + p.getTamanoProducto() + ";" + p.getPrecioVenta() + ";"
                + p.getStockProducto() + ";" + p.getEstadoProducto());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarProducto(Producto p) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (datos[1].equals(p.getCodigoProducto())) {
                    lineas.add(p.getIdProducto() + ";" + p.getCodigoProducto() + ";"
                        + p.getNombreProducto() + ";" + p.getTipoEmpaque() + ";"
                        + p.getTamanoProducto() + ";" + p.getPrecioVenta() + ";"
                        + p.getStockProducto() + ";" + p.getEstadoProducto());
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
            for (String l : lineas) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return encontrado;
    }

    public boolean eliminarProducto(String codigoEliminar) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean eliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (!datos[1].equals(codigoEliminar)) {
                    lineas.add(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
            for (String l : lineas) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return eliminado;
    }

    public int generarNuevoID() {
        int ultimoID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 1) continue;
                int id = Integer.parseInt(datos[0].trim());
                if (id > ultimoID) ultimoID = id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimoID + 1;
    }
}

