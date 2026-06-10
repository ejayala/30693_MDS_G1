/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import models.ClientePotenciales;

/**
 *
 * @author User
 */
public class ClientePotencialRepository {

    private final String archivo = "base_datos/clientes_potenciales.txt";

    public ArrayList<ClientePotenciales> listar() {
        ArrayList<ClientePotenciales> lista = new ArrayList<>();
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
                lista.add(new ClientePotenciales(datos[0], datos[1], datos[2], datos[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean modificar(String codigoOriginal, ClientePotenciales cp) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean encontrado = false;
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
                if (datos[0].equals(codigoOriginal)) {
                    lineas.add(cp.getCodigoClientePotencial() + ";" + cp.getNombreClientePotencial() + ";"
                            + cp.getApellidoClientePotencial() + ";" + cp.getTelefonoClientePotencial());
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
            for (String l : lineas) {
                pw.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return encontrado;
    }

    public boolean eliminar(String codigoEliminar) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean eliminado = false;
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
                if (!datos[0].equals(codigoEliminar)) {
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
            for (String l : lineas) {
                pw.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return eliminado;
    }

    public boolean guardar(ClientePotenciales cp) {
        try (FileWriter fw = new FileWriter(archivo, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println(cp.getCodigoClientePotencial() + ";" + cp.getNombreClientePotencial() + ";"
                    + cp.getApellidoClientePotencial() + ";" + cp.getTelefonoClientePotencial());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generarNuevoCodigo() {
        int ultimoNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] datos = linea.split(";");
                if (datos.length < 1) {
                    continue;
                }
                String codigo = datos[0].replace("CP", "").trim();
                int num = Integer.parseInt(codigo);
                if (num > ultimoNum) {
                    ultimoNum = num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CP" + (ultimoNum + 1);
    }

    public boolean existeTelefono(String telefono, String codigoExcluir) {
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
                // Si codigoExcluir está vacío es registro nuevo, no excluir nada
                if (datos[3].equals(telefono)) {
                    if (codigoExcluir.isEmpty() || !datos[0].equals(codigoExcluir)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
