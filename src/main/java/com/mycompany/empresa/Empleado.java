/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresa;

/**
 *
 * @author Daniel
 */
// Clase base Empleado
abstract class Empleado {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected int cantidadHijos;

    public Empleado(String nombre, String apellido, String cedula, int cantidadHijos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.cantidadHijos = cantidadHijos;
    }

    public abstract double calcularSueldo();
}

// Subclase EmpleadoPorHora
class EmpleadoPorHora extends Empleado {
    private int horasTrabajadas;
    private final double sueldoPorHora = 100;

    public EmpleadoPorHora(String nombre, String apellido, String cedula, int cantidadHijos, int horasTrabajadas) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return horasTrabajadas * sueldoPorHora;
    }
}

// Subclase EmpleadoTemporal
class EmpleadoTemporal extends Empleado {
    private String fechaAlta;
    private String fechaBaja;
    private final double sueldoBasico = 18000;

    public EmpleadoTemporal(String nombre, String apellido, String cedula, int cantidadHijos, String fechaAlta, String fechaBaja) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBasico + (1000 * cantidadHijos);
    }
}

// Subclase EmpleadoPlantaPermanente
class EmpleadoPlantaPermanente extends Empleado {
    private int antiguedad;
    private final double sueldoBasico = 20000;

    public EmpleadoPlantaPermanente(String nombre, String apellido, String cedula, int cantidadHijos, int antiguedad) {
        super(nombre, apellido, cedula, cantidadHijos);
        this.antiguedad = antiguedad;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBasico + (1000 * cantidadHijos) + (1000 * antiguedad);
    }
}

