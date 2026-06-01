package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

public class ClienteRepository {
    private final String archivo = "base_datos/clientes.txt";

    /** Busca un cliente por su ID. Devuelve null si no existe. */
    public Cliente buscarPorId(String idBuscar) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(idBuscar)) {
                    return new Cliente(datos[0], datos[1], datos[2],
                            datos[3], datos[4], datos[5], datos[6]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Devuelve todos los clientes del archivo. */
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                lista.add(new Cliente(datos[0], datos[1], datos[2],
                        datos[3], datos[4], datos[5], datos[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /** Agrega un nuevo cliente al archivo. Devuelve false si el ID ya existe. */
    public boolean guardarCliente(Cliente cliente) {
        if (buscarPorId(cliente.getId()) != null) {
            return false; // ID duplicado
        }
        try (PrintWriter pw = new PrintWriter(new java.io.FileWriter(archivo, true))) {
            pw.println(formatear(cliente));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Modifica los datos de un cliente existente (busca por ID). */
    public boolean modificarCliente(Cliente cliente) {
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(cliente.getId())) {
                    lineas.add(formatear(cliente));
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) return false;
        try (PrintWriter pw = new PrintWriter(archivo)) {
            for (String l : lineas) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Elimina un cliente por su ID. */
    public boolean eliminarCliente(String id) {
        List<String> lineas = new ArrayList<>();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos[0].equals(id)) {
                    datos[6] = "Inactivo";
                    lineas.add(String.join(";", datos));
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (!encontrado) return false;
        try (PrintWriter pw = new PrintWriter(archivo)) {
            for (String l : lineas) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // ── helpers ────────────────────────────────────────────────────────────────
    private String formatear(Cliente c) {
        return c.getId() + ";" + c.getNombre() + ";" + c.getApellido() + ";"
                + c.getCedula() + ";" + c.getTelefono() + ";"
                + c.getDireccion() + ";" + c.getEstado();
    }
}
