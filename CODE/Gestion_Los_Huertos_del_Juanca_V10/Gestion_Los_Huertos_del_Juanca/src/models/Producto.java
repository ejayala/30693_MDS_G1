/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class Producto {
    private int idProducto;              
    private String codigoProducto;       
    private String nombreProducto;
    private String tipoEmpaque;
    private String tamanoProducto;
    private double precioVenta;
    private int stockProducto;          
    private String estadoProducto; 

    public Producto(int idProducto, String codigoProducto, String nombreProducto, String tipoEmpaque, String tamanoProducto, double precioVenta, int stockProducto, String estadoProducto) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.tipoEmpaque = tipoEmpaque;
        this.tamanoProducto = tamanoProducto;
        this.precioVenta = precioVenta;
        this.stockProducto = stockProducto;
        this.estadoProducto = estadoProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public String getTamanoProducto() {
        return tamanoProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public void setTamanoProducto(String tamanoProducto) {
        this.tamanoProducto = tamanoProducto;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
    
}


