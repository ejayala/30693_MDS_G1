/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.ClientePotenciales;
import views.Frm_CliPotenciales;

/**
 *
 * @author User
 */
public class ClientePotencialService {

    private Frm_CliPotenciales vista;
    private ClientePotencialRepository dao;
    private String codigoSeleccionado = "";
    private String codigoGenerado = "";

    public ClientePotencialService(Frm_CliPotenciales vista, ClientePotencialRepository dao) {
        this.vista = vista;
        this.dao = dao;
        iniciarEventos();
        cargarTabla();
        generarCodigo();
    }

    private void iniciarEventos() {
        vista.btn_modificar.addActionListener(e -> modificar());
        vista.btn_eliminar.addActionListener(e -> eliminar());

        vista.tbl_clientesPot.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = vista.tbl_clientesPot.getSelectedRow();
                if (fila >= 0) {
                    codigoSeleccionado = vista.tbl_clientesPot.getValueAt(fila, 0).toString();
                    vista.txt_nombre.setText(vista.tbl_clientesPot.getValueAt(fila, 1).toString());
                    vista.txt_apellido.setText(vista.tbl_clientesPot.getValueAt(fila, 2).toString());
                    vista.txt_telefono.setText(vista.tbl_clientesPot.getValueAt(fila, 3).toString());
                }
            }
        });
        vista.btn_guardar.addActionListener(e -> guardar());
        vista.btn_limpiar.addActionListener(e -> limpiarCampos());
    }

    private boolean validarNombre(String valor) {
        return valor.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^09\\d{8}$");
    }

    private boolean validarDatos() {
        String nombre = vista.txt_nombre.getText().trim();
        String apellido = vista.txt_apellido.getText().trim();
        String telefono = vista.txt_telefono.getText().trim();

        if (nombre.isEmpty() || !validarNombre(nombre)) {
            JOptionPane.showMessageDialog(vista, "Nombre inválido, solo letras");
            return false;
        }
        if (apellido.isEmpty() || !validarNombre(apellido)) {
            JOptionPane.showMessageDialog(vista, "Apellido inválido, solo letras");
            return false;
        }
        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(vista, "Teléfono inválido, debe empezar con 09 y tener 10 dígitos");
            return false;
        }
        if (dao.existeTelefono(telefono, codigoSeleccionado)) {
            JOptionPane.showMessageDialog(vista, "Ya existe un cliente potencial con ese teléfono");
            return false;
        }

        return true;
    }

    private void guardar() {
        if (dao.existeTelefono(vista.txt_telefono.getText().trim(), "")) {
        JOptionPane.showMessageDialog(vista, "Ya existe un cliente potencial con ese teléfono");
        return;
    }
        if (!validarDatos()) {
            return;
        }
        ClientePotenciales cp = new ClientePotenciales(
                codigoGenerado,
                vista.txt_nombre.getText().trim(),
                vista.txt_apellido.getText().trim(),
                vista.txt_telefono.getText().trim());
        if (dao.guardar(cp)) {
            JOptionPane.showMessageDialog(vista, "Cliente potencial guardado");
            cargarTabla();
            limpiarCampos();
            generarCodigo();
        }
    }

    private void generarCodigo() {
        codigoGenerado = dao.generarNuevoCodigo();
    }

    private void modificar() {
        if (codigoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla");
            return;
        }
        if (!validarDatos()) {
            return;
        }
        ClientePotenciales cp = new ClientePotenciales(
                codigoSeleccionado,
                vista.txt_nombre.getText().trim(),
                vista.txt_apellido.getText().trim(),
                vista.txt_telefono.getText().trim());
        if (dao.modificar(codigoSeleccionado, cp)) {
            JOptionPane.showMessageDialog(vista, "Cliente potencial modificado");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo modificar");
        }
    }

    private void eliminar() {
        if (codigoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(vista, "¿Eliminar este cliente potencial?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }
        if (dao.eliminar(codigoSeleccionado)) {
            JOptionPane.showMessageDialog(vista, "Cliente potencial eliminado");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
        }
    }

    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Teléfono");
        for (ClientePotenciales cp : dao.listar()) {
            modelo.addRow(new Object[]{
                cp.getCodigoClientePotencial(), cp.getNombreClientePotencial(), cp.getApellidoClientePotencial(), cp.getTelefonoClientePotencial()
            });
        }
        vista.tbl_clientesPot.setModel(modelo);
    }

    private void limpiarCampos() {
        codigoSeleccionado = "";
        vista.txt_nombre.setText("");
        vista.txt_apellido.setText("");
        vista.txt_telefono.setText("");
    }
}
