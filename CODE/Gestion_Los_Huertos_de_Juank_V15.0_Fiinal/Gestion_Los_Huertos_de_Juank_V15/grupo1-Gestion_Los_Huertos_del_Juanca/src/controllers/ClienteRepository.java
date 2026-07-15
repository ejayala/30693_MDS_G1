package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Cliente;

public class ClienteRepository {

    private final String archivo = "base_datos/clientes.txt";

    public Cliente buscarPorId(String idBuscar) {
        for (Cliente c : listarClientes()) {
            if (c.getId().equals(idBuscar)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorCedula(String cedula) {
        for (Cliente c : listarClientes()) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Cliente> buscarClientesActivosPorNombre(String criterio) {
        ArrayList<Cliente> filtrados = new ArrayList<>();
        String filtro = criterio.toLowerCase().trim();
        
        for (Cliente c : listarClientes()) {
            if ("Activo".equalsIgnoreCase(c.getEstado())) {
                String nombreCompleto = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                if (nombreCompleto.contains(filtro) || c.getCedula().contains(filtro)) {
                    filtrados.add(c);
                }
            }
        }
        return filtrados;
    }

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                String fechaStr = datos[7];
                LocalDate fechaCumple = null;

                if (fechaStr != null && !fechaStr.trim().isEmpty() && !fechaStr.equalsIgnoreCase("null")) {
                    try {
                        fechaCumple = LocalDate.parse(fechaStr.trim());
                    } catch (java.time.format.DateTimeParseException e) {
                        System.err.println("Formato de fecha inválido omitido: " + fechaStr);
                        fechaCumple = null; 
                    }
                }
                lista.add(new Cliente(
                        datos[0], 
                        datos[1], 
                        datos[2],
                        datos[3], 
                        datos[4], 
                        datos[5], 
                        datos[6],
                        fechaCumple
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean modificarCliente(Cliente cliente) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (datos[0].equals(cliente.getId())) {
                    lineas.add(formatearCliente(cliente));
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

    public boolean guardarClientes(ArrayList<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
            for (Cliente cliente : clientes) {
                pw.println(formatearCliente(cliente));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarCliente(Cliente cliente) {
        try (FileWriter fw = new FileWriter(archivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(formatearCliente(cliente));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCliente(String idEliminar) {
        ArrayList<String> lineas = new ArrayList<>();
        boolean eliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (!datos[0].equals(idEliminar)) {
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

    public String generarNuevoID() {
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
        return String.format("%04d", ultimoID + 1);
    }
    
    public boolean existeCedula(String cedulaBuscar, String idActual) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(";");
                if (datos.length < 8) continue;
                if (datos[3].equals(cedulaBuscar) && !datos[0].equals(idActual)) {
                    return true; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String formatearCliente(Cliente cliente) {
        return cliente.getId() + ";" + cliente.getNombre() + ";"
                + cliente.getApellido() + ";" + cliente.getCedula() + ";"
                + cliente.getTelefono() + ";" + cliente.getDireccion() + ";"
                + cliente.getEstado() + ";" + cliente.getFechaCumpleanios();
    }
}