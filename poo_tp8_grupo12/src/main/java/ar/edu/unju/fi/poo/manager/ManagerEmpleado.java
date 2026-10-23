package ar.edu.unju.fi.poo.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.poo.exceptions.EmpleadoException;
import ar.edu.unju.fi.poo.model.*;

public class ManagerEmpleado {
    private List<Empleado> empleados;

    public ManagerEmpleado() {
        this.empleados = new ArrayList<>();
    }

    public void cargarDatosPrueba() throws EmpleadoException {
        Profesional prof = new Profesional("1111-26", 38111222, "Carlos", LocalDate.of(2018, 5, 10), 2);
        prof.agregarTitulo(new Titulo(2015, "Ingeniería", "Universitario"));
        agregarEmpleado(prof);
        
        agregarEmpleado(new Administrativo("2222-26", 35444555, "Laura", LocalDate.of(2015, 8, 20), 1, 'A'));
        agregarEmpleado(new Administrativo("3333-26", 32666777, "Roberto", LocalDate.of(2010, 11, 5), 3, 'C'));
        agregarEmpleado(new Limpieza("4444-26", 40888999, "Marta", LocalDate.of(2022, 1, 10), 2));
        agregarEmpleado(new Limpieza("5555-26", 41000111, "Jorge", LocalDate.of(2023, 6, 1), 0));
    }

    public void agregarEmpleado(Empleado emp) throws EmpleadoException {
        if (emp != null) {
            for (Empleado e : this.empleados) {
                if (e.getDocumento() == emp.getDocumento()) {
                    throw new EmpleadoException("El documento " + emp.getDocumento() + " ya esta registrado.");
                }
            }

            String anioActual = String.valueOf(LocalDate.now().getYear()).substring(2);
            String regex = "^[0-9]{4,5}-" + anioActual + "$";
            if (!emp.getLegajo().matches(regex)) {
                throw new EmpleadoException("El legajo " + emp.getLegajo() + " debe tener formato NNNN-" + anioActual);
            }

            if (emp.getFechaIngreso().isAfter(LocalDate.now())) {
                throw new EmpleadoException("La fecha de ingreso no puede ser futura.");
            }

            if (emp.getCantidadHijos() < 0 || emp.getCantidadHijos() > 5) {
                throw new EmpleadoException("La cantidad de hijos debe estar entre 0 y 5.");
            }

            if (emp instanceof Profesional) {
                Profesional prof = (Profesional) emp;
                if (prof.getTitulos() == null || prof.getTitulos().isEmpty()) {
                    throw new EmpleadoException("El profesional debe tener un titulo registrado.");
                }
            }

            this.empleados.add(emp);
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public String obtenerSueldosTodos() {
        StringBuilder detalle = new StringBuilder();
        for (Empleado e : empleados) {
            detalle.append("Nombre: ").append(e.getNombre())
                   .append(" | Legajo: ").append(e.getLegajo())
                   .append(" | Sueldo Neto: $").append(e.calcularSueldoNeto()).append("\n");
        }
        return detalle.toString();
    }
    
    public Empleado buscarPorLegajo(String legajo) {
        for (Empleado e : empleados) {
            if (e.getLegajo().equals(legajo)) {
                return e;
            }
        }
        return null;
    }
    
    // NUEVOS MÉTODOS AGREGADOS PARA CUMPLIR CON LAS OPERACIONES FALTANTES AAAAAAAAAAA

    // Método para filtrar empleados administrativos por categoría (Punto E)
    public List<Administrativo> obtenerAdministrativosPorCategoria(char categoria) {
        List<Administrativo> filtrados = new ArrayList<>();
        for (Empleado e : this.empleados) {
            if (e instanceof Administrativo) {
                Administrativo adm = (Administrativo) e;
                if (adm.getCategoria() == categoria) {
                    filtrados.add(adm);
                }
            }
        }
        return filtrados;
    }

    // Método para calcular el neto acumulado según el tipo de empleado (Punto F) mas que nada todas las adiciones son para dejar mas completo al manager empleados antes con solo la busqueda de legajos y algunas mas vi que estaba medio desnudo y casi que el main se encargaba de algunas de las cosas usadas.
    public double calcularNetoAcumuladoPorTipo(int opcionTipo) {
        double acumulado = 0.0;
        for (Empleado e : this.empleados) {
            if (opcionTipo == 1 && e instanceof Profesional) {
                acumulado += e.calcularSueldoNeto();
            } else if (opcionTipo == 2 && e instanceof Administrativo) {
                acumulado += e.calcularSueldoNeto();
            } else if (opcionTipo == 3 && e instanceof Limpieza) {
                acumulado += e.calcularSueldoNeto();
            }
        }
        return acumulado;
    }
}