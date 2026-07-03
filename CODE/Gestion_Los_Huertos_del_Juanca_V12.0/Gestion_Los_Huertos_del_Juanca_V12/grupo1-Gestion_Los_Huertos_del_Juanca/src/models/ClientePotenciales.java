/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class ClientePotenciales {
    private String codigoClientePotencial;
    private String nombreClientePotencial;
    private String apellidoClientePotencial;
    private String telefonoClientePotencial;

    public ClientePotenciales(String codigoClientePotencial, String nombreClientePotencial, String apellidoClientePotencial, String telefonoClientePotencial) {
        this.codigoClientePotencial = codigoClientePotencial;
        this.nombreClientePotencial = nombreClientePotencial;
        this.apellidoClientePotencial = apellidoClientePotencial;
        this.telefonoClientePotencial = telefonoClientePotencial;
    }

    public String getCodigoClientePotencial() {
        return codigoClientePotencial;
    }

    public String getNombreClientePotencial() {
        return nombreClientePotencial;
    }

    public String getApellidoClientePotencial() {
        return apellidoClientePotencial;
    }

    public String getTelefonoClientePotencial() {
        return telefonoClientePotencial;
    }

    public void setCodigoClientePotencial(String codigoClientePotencial) {
        this.codigoClientePotencial = codigoClientePotencial;
    }

    public void setNombreClientePotencial(String nombreClientePotencial) {
        this.nombreClientePotencial = nombreClientePotencial;
    }

    public void setApellidoClientePotencial(String apellidoClientePotencial) {
        this.apellidoClientePotencial = apellidoClientePotencial;
    }

    public void setTelefonoClientePotencial(String telefonoClientePotencial) {
        this.telefonoClientePotencial = telefonoClientePotencial;
    }

   
    
    
}
