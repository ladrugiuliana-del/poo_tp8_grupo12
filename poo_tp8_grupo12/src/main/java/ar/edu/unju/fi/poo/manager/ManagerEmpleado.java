package ar.edu.unju.fi.poo.manager;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.poo.model.Empleado;

public class ManagerEmpleado {
    private List<Empleado> empleados;

    public ManagerEmpleado() {
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado emp) {
        if (emp != null) {
            this.empleados.add(emp);
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void mostrarSueldosTodos() {
        for (Empleado e : empleados) {
            System.out.println("Nombre: " + e.getNombre() + 
                               " | Legajo: " + e.getLegajo() + 
                               " | Sueldo Neto: $" + e.calcularSueldoNeto());
        }
    }
    
    public Empleado buscarPorLegajo(int legajo) {
        for (Empleado e : empleados) {
            if (e.getLegajo() == legajo) {
                return e;
            }
        }
        return null;
    }
}
