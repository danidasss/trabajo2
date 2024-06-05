/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.empresa;

/**
 *
 * @author Daniel
 */
import java.util.ArrayList;

class Empresa {
    private ArrayList<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void eliminarEmpleado(int index) {
        if (index >= 0 && index < empleados.size()) {
            empleados.remove(index);
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
