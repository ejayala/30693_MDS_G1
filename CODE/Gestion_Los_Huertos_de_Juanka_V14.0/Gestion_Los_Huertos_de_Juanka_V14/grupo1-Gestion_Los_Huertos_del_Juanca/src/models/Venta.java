package models;

import java.time.LocalDate;

public class Venta {

    private String idVenta;
    private LocalDate fecha;
    private String idCliente;
    private double total;

    public Venta(String idVenta, LocalDate fecha, String idCliente, double total) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.total = total;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}