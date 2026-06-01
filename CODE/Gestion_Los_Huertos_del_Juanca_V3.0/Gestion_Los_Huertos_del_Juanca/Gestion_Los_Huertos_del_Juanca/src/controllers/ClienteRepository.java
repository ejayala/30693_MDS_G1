package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import models.Cliente;

public class ClienteRepository {
    private final String archivo = "base_datos/clientes.txt";
    public Cliente buscarPorId(String idBuscar) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(";");
                if (datos[0].equals(idBuscar)) {
                    return new Cliente(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean modificarCliente(Cliente cliente){
        java.util.List<String> lineas= new java.util.ArrayList<>();
        boolean bandera=false;
        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea=br.readLine())!=null){
                String datos[]=linea.split(";");
                if(datos[0].equals(cliente.getId())){
                    String newLine= cliente.getId()+";"+
                            cliente.getNombre()+";"+
                            cliente.getApellido()+";"+
                            cliente.getCedula()+";"+
                            cliente.getTelefono()+";"+
                            cliente.getDireccion()+";"+ 
                            cliente.getEstado();
                    lineas.add(newLine);
                    bandera=true;
                }else{
                    lineas.add(linea);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        try(java.io.PrintWriter pw= new java.io.PrintWriter(archivo)){
            for(String l: lineas){
                pw.println(l);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return bandera;
    }
    public ArrayList<Cliente>listarClientes(){
        ArrayList<Cliente> lista=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
            String linea;
            while((linea=br.readLine())!=null){
                String datos[]=linea.split(";");
                Cliente cliente=new Cliente(datos[0],datos[1],datos[2],
                        datos[3],datos[4],datos[5],datos[6]);
                lista.add(cliente);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
