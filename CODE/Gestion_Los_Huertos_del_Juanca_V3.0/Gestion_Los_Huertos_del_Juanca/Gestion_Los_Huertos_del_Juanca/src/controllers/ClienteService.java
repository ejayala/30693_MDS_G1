package controllers;

import models.Cliente;
import controllers.ClienteRepository;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import views.Frm_Principal;
public class ClienteService{
    private Frm_Principal vista;
    private ClienteRepository dao;

    public ClienteService(Frm_Principal vista,ClienteRepository dao) {
        this.vista = vista;
        this.dao = dao;
        cargarTabla();
        iniciarEventos();
    }
    private void iniciarEventos() {
        vista.btn_buscarcli.addActionListener(e -> buscarCliente());
        vista.btn_modificarcli.addActionListener(e -> modificarCliente());
    }
    private void buscarCliente(){
        String id=vista.txt_IDclibuscar.getText();
        Cliente cliente=dao.buscarPorId(id);
        if(cliente==null){
            vista.txt_IDcliente.setText("");
            vista.txt_nomcliente.setText("");
            vista.txt_apecliente.setText("");
            vista.txt_cedula.setText("");
            vista.txt_telefono.setText("");
            vista.txt_direccion.setText("");
            return;
        }
        vista.txt_IDcliente.setText(cliente.getId());
        vista.txt_nomcliente.setText(cliente.getNombre());
        vista.txt_apecliente.setText(cliente.getApellido());
        vista.txt_cedula.setText(cliente.getCedula());
        vista.txt_telefono.setText(cliente.getTelefono());
        vista.txt_direccion.setText(cliente.getDireccion());
        if(cliente.getEstado().equals("Activo")){
            vista.btn_activo.setSelected(true);
        }else{
            vista.btn_activo.setSelected(true);
        }
    }
    private void modificarCliente(){
        String estado;
        if(vista.btn_activo.isSelected()){
            estado="Activo";
        }else{
            estado="Inactivo";
        }
        Cliente cliente= new Cliente(vista.txt_IDcliente.getText(), 
                vista.txt_nomcliente.getText(),
                vista.txt_apecliente.getText(),
                vista.txt_cedula.getText(),
                vista.txt_telefono.getText(),
                vista.txt_direccion.getText(), estado);
        boolean bandera=dao.modificarCliente(cliente);
        if(bandera){
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente modificado");
            cargarTabla();
        }else{
            javax.swing.JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
        }
    }
    private void cargarTabla(){
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Cedula");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Estado");
        ArrayList<Cliente> lista=dao.listarClientes();
        for(Cliente c: lista){
            modelo.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApellido(),
                c.getCedula(),
                c.getTelefono(),
                c.getDireccion(),
                c.getEstado()
            });
        }
        vista.tbl_clientes.setModel(modelo);
    }
}