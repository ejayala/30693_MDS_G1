/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Producto;

/**
 *
 * @author User
 */
public class ProductoService {

    private ProductoRepository repositorio;
    private int contador;

    public ProductoService() {
        repositorio = new ProductoRepository();
        contador = repositorio.listar().size(); // inicia desde lo que ya existe
    }

    private String generarCodigo() {
        contador++;
        return String.format("PROD%03d", contador);
    }

    private boolean validarNombre(String nombre) {
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    private boolean validarTexto(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    private boolean validarPrecio(double precio) {
        return precio > 0;
    }

    private boolean validarStock(int stock) {
        return stock >= 0;
    }

    public String insertarProducto(String nombre, String empaque, String tamano, String precio, int stock, boolean disponible) {
        if (nombre.isEmpty() || empaque.isEmpty() || tamano.isEmpty() || precio.isEmpty()) {
            return "Error: campos vacíos";
        }
        if (!validarNombre(nombre)) {
            return "Error: el nombre solo debe contener letras";
        }
        double precioNum;
        try {
            precioNum = Double.parseDouble(precio);
        } catch (NumberFormatException e) {
            return "Error: el precio debe ser numérico";
        }
        if (!validarPrecio(precioNum)) {
            return "Error: el precio debe ser mayor a 0";
        }

        if (!validarStock(stock)) {
            return "Error: el stock debe ser un número positivo";
        }
        List<Producto> lista = repositorio.listar();
        int id = lista.size() + 1;
        String codigo = generarCodigo();
        String estado = disponible ? "Disponible" : "No disponible";

        Producto nuevo = new Producto(id, codigo, nombre, empaque, tamano, precioNum, stock, estado);
        lista.add(nuevo);
        repositorio.guardar(lista);
        return "Producto registrado correctamente";
    }

    public List<Producto> mostrarProductos() {
        return repositorio.listar();
    }

    public String modificarProducto(String codigo, String nombre, String empaque, String tamano, String precio, int stock, boolean disponible) {
        List<Producto> lista = repositorio.listar();
        for (Producto p : lista) {
            if (p.getCodigoProducto().equals(codigo)) {
                p.setNombreProducto(nombre);
                p.setTipoEmpaque(empaque);
                p.setTamanoProducto(tamano);
                p.setPrecioVenta(Double.parseDouble(precio));
                p.setStockProducto(stock);
                p.setEstadoProducto(disponible ? "Disponible" : "No disponible");
                repositorio.guardar(lista);
                return "Producto modificado correctamente";
            }
        }
        return "Código no encontrado";
    }

    public String eliminarProducto(String codigo) {
        List<Producto> lista = repositorio.listar();
        boolean eliminado = lista.removeIf(p -> p.getCodigoProducto().equals(codigo));
        if (eliminado) {
            repositorio.guardar(lista);
            return "Producto eliminado correctamente";
        }
        return "Código no encontrado";
    }
}
