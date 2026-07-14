/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private Consumer<ClientePotenciales> onCrearCliente;

    public ClientePotencialService(Frm_CliPotenciales vista,
            ClientePotencialRepository dao,
            Consumer<ClientePotenciales> onCrearCliente) {
        this.vista = vista;
        this.dao = dao;
        this.onCrearCliente = onCrearCliente;
        iniciarEventos();
        cargarTabla();
        generarCodigo();
    }

    private void iniciarEventos() {
        vista.btn_guardar.addActionListener(e -> guardar());
        vista.btn_modificar.addActionListener(e -> modificar());
        vista.btn_eliminar.addActionListener(e -> eliminar());
        vista.btm_crearCliente.addActionListener(e -> crearCliente());

        vista.txt_buscarPotencial.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }
        });

        vista.tbl_clientesPot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int fila = vista.tbl_clientesPot.getSelectedRow();
                if (fila >= 0) {
                    int filaModelo = vista.tbl_clientesPot.convertRowIndexToModel(fila);
                    codigoSeleccionado = vista.tbl_clientesPot.getModel()
                            .getValueAt(filaModelo, 0).toString();
                    vista.txt_nombre.setText(vista.tbl_clientesPot.getModel()
                            .getValueAt(filaModelo, 1).toString());
                    vista.txt_apellido.setText(vista.tbl_clientesPot.getModel()
                            .getValueAt(filaModelo, 2).toString());
                    vista.txt_telefono.setText(vista.tbl_clientesPot.getModel()
                            .getValueAt(filaModelo, 3).toString());
                }
            }
        });

        vista.txt_nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    e.consume();
                }
            }
        });

        vista.txt_apellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != '\b') {
                    e.consume();
                }
            }
        });
        vista.txt_telefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
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
            vista.tbl_clientesPot.setEnabled(false);
            cargarTabla();
            vista.tbl_clientesPot.setEnabled(true);
            vista.tbl_clientesPot.clearSelection();
            limpiarCampos();
            generarCodigo();
        }

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
            vista.tbl_clientesPot.setEnabled(false);
            cargarTabla();
            vista.tbl_clientesPot.setEnabled(true);
            vista.tbl_clientesPot.clearSelection();
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
            vista.tbl_clientesPot.setEnabled(false);
            cargarTabla();
            vista.tbl_clientesPot.setEnabled(true);
            vista.tbl_clientesPot.clearSelection();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo eliminar");
        }
    }

    private void crearCliente() {
        if (codigoSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente potencial de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(vista,
                "¿Desea convertir este cliente potencial en cliente activo?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) {
            return;
        }

        ClientePotenciales cp = null;
        for (ClientePotenciales c : dao.listar()) {
            if (c.getCodigoClientePotencial().equals(codigoSeleccionado)) {
                cp = c;
                break;
            }
        }
        if (cp == null) {
            JOptionPane.showMessageDialog(vista, "No se encontró el cliente potencial");
            return;
        }

        dao.eliminar(codigoSeleccionado);

        if (onCrearCliente != null) {
            onCrearCliente.accept(cp);
        }

        JOptionPane.showMessageDialog(vista,
                "Datos cargados en módulo de clientes. Complete la información faltante.");
        vista.tbl_clientesPot.setEnabled(false);
        cargarTabla();
        vista.tbl_clientesPot.setEnabled(true);
        vista.tbl_clientesPot.clearSelection();
        limpiarCampos();
        vista.dispose();
    }

    private void filtrarTabla() {
        String texto = vista.txt_buscarPotencial.getText().trim();
        DefaultTableModel modelo = (DefaultTableModel) vista.tbl_clientesPot.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_clientesPot.setRowSorter(sorter);
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            limpiarCampos();
            vista.tbl_clientesPot.clearSelection();
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2));
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
                cp.getCodigoClientePotencial(),
                cp.getNombreClientePotencial(),
                cp.getApellidoClientePotencial(),
                cp.getTelefonoClientePotencial()
            });
        }
        vista.tbl_clientesPot.setModel(modelo);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        vista.tbl_clientesPot.setRowSorter(sorter);
        vista.tbl_clientesPot.clearSelection();
    }

    private void limpiarCampos() {
        codigoSeleccionado = "";
        vista.txt_nombre.setText("");
        vista.txt_apellido.setText("");
        vista.txt_telefono.setText("");
        vista.txt_buscarPotencial.setText("");
        vista.tbl_clientesPot.clearSelection();
    }

    private void generarCodigo() {
        codigoGenerado = dao.generarNuevoCodigo();
    }
    
}
