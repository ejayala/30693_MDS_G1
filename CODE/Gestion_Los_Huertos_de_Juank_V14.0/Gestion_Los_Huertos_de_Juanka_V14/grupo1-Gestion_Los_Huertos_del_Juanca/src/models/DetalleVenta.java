package models;

public class DetalleVenta {

    private String idVenta;
    private String codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;

    // Se corrigió agregando nombreProducto al constructor para evitar valores null
    public DetalleVenta(String idVenta, String codigoProducto, String nombreProducto, int cantidad, double precioUnitario) {
        this.idVenta = idVenta;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }

    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }
}