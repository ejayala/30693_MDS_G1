
package models;

import java.time.LocalDate;

public class Cliente {
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String direccion;
    private String estado;
    private LocalDate fechaCumpleanios;

    public Cliente(String id, String nombre, String apellido, String cedula, String telefono, String direccion, String estado, LocalDate fechaCumpleanios) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.fechaCumpleanios = fechaCumpleanios;
    }

    public LocalDate getFechaCumpleanios() {
        return fechaCumpleanios;
    }

    public void setFechaCumpleanios(LocalDate fechaCumpleanios) {
        this.fechaCumpleanios = fechaCumpleanios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
