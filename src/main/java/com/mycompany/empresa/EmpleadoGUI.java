/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresa;

/**
 *
 * @author Daniel
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpleadoGUI extends JFrame {
    private JTextField nombreField, apellidoField, cedulaField, hijosField, horasField, antiguedadField, altaField, bajaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private Empresa empresa;

    public EmpleadoGUI() {
        empresa = new Empresa();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Empleados");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));
        
        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        inputPanel.add(apellidoField);

        inputPanel.add(new JLabel("Cédula:"));
        cedulaField = new JTextField();
        inputPanel.add(cedulaField);

        inputPanel.add(new JLabel("Cantidad de Hijos:"));
        hijosField = new JTextField();
        inputPanel.add(hijosField);

        inputPanel.add(new JLabel("Horas Trabajadas (Empleado por Hora):"));
        horasField = new JTextField();
        inputPanel.add(horasField);

        inputPanel.add(new JLabel("Antigüedad (Empleado Planta Permanente):"));
        antiguedadField = new JTextField();
        inputPanel.add(antiguedadField);

        inputPanel.add(new JLabel("Fecha de Alta (Empleado Temporal):"));
        altaField = new JTextField();
        inputPanel.add(altaField);

        inputPanel.add(new JLabel("Fecha de Baja (Empleado Temporal):"));
        bajaField = new JTextField();
        inputPanel.add(bajaField);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Nombre", "Apellido", "Cédula", "Hijos", "Sueldo"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Guardar");
        JButton clearButton = new JButton("Limpiar");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEmpleado();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void guardarEmpleado() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cedula = cedulaField.getText();
        int hijos = Integer.parseInt(hijosField.getText());
        int horas = horasField.getText().isEmpty() ? 0 : Integer.parseInt(horasField.getText());
        int antiguedad = antiguedadField.getText().isEmpty() ? 0 : Integer.parseInt(antiguedadField.getText());
        String fechaAlta = altaField.getText();
        String fechaBaja = bajaField.getText();

        Empleado empleado;
        if (!horasField.getText().isEmpty()) {
            empleado = new EmpleadoPorHora(nombre, apellido, cedula, hijos, horas);
        } else if (!altaField.getText().isEmpty() && !bajaField.getText().isEmpty()) {
            empleado = new EmpleadoTemporal(nombre, apellido, cedula, hijos, fechaAlta, fechaBaja);
        } else {
            empleado = new EmpleadoPlantaPermanente(nombre, apellido, cedula, hijos, antiguedad);
        }

        empresa.agregarEmpleado(empleado);
        tableModel.addRow(new Object[]{nombre, apellido, cedula, hijos, empleado.calcularSueldo()});
        
        int option = JOptionPane.showConfirmDialog(this, "¿Desea guardar la información?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            nombreField.setText("");
            apellidoField.setText("");
            cedulaField.setText("");
            hijosField.setText("");
            horasField.setText("");
            antiguedadField.setText("");
            altaField.setText("");
            bajaField.setText("");
            JOptionPane.showMessageDialog(this, "Datos almacenados correctamente.");
        }
    }

    private void limpiarCampos() {
        int option = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea limpiar todos los parámetros?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            nombreField.setText("");
            apellidoField.setText("");
            cedulaField.setText("");
            hijosField.setText("");
            horasField.setText("");
            antiguedadField.setText("");
            altaField.setText("");
            bajaField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmpleadoGUI gui = new EmpleadoGUI();
            gui.setVisible(true);
        });
    }
}

