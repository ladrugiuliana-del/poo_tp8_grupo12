package ar.edu.unju.fi.poo.main;

import ar.edu.unju.fi.poo.manager.ManagerEmpleado;
import ar.edu.unju.fi.poo.model.*;
import ar.edu.unju.fi.poo.exceptions.EmpleadoException; // Faltaba importar la excepción

import java.time.LocalDate;
import java.util.Scanner;

public class MainEmpleados {

    public static void main(String[] args) {
        ManagerEmpleado manager = new ManagerEmpleado();
        Scanner scanner = new Scanner(System.in);

        // Punto 1. CORRECCIÓN AAAAA: Los legajos ahora son String con formato NNNN-AA
        Profesional prof1 = new Profesional("1001-26", 38111222, "Carlos Gómez", LocalDate.of(2018, 5, 10), 2);
        prof1.agregarTitulo(new Titulo(2015, "ingenieria en Sistemas", "Universitario"));
        prof1.agregarTitulo(new Titulo(2020, "diplomatura en Datos", "Universitario"));

        Profesional prof2 = new Profesional("1002-26", 39222333, "Ana Martínez", LocalDate.of(2021, 3, 15), 0);
        prof2.agregarTitulo(new Titulo(2019, "licenciatura en Administracion", "universitario"));

        Administrativo adm1 = new Administrativo("1003-26", 35444555, "Laura Ríos", LocalDate.of(2015, 8, 20), 1, 'A');
        Administrativo adm2 = new Administrativo("1004-26", 32666777, "Roberto Peña", LocalDate.of(2010, 11, 5), 3, 'C');

        Limpieza limp1 = new Limpieza("1005-26", 40888999, "Marta Sánchez", LocalDate.of(2022, 1, 10), 2);
        Limpieza limp2 = new Limpieza("1006-26", 41000111, "Jorge Torres", LocalDate.of(2023, 6, 1), 0);

        // Punto 2. CORRECCIÓN xdxd: Agregamos el try-catch porque ahora agregarEmpleado lanza EmpleadoException
        try {
            manager.agregarEmpleado(prof1);
            manager.agregarEmpleado(prof2);
            manager.agregarEmpleado(adm1);
            manager.agregarEmpleado(adm2);
            manager.agregarEmpleado(limp1);
            manager.agregarEmpleado(limp2);
            System.out.println("Se cargaron exitosamente los 6 empleados.\n");
        } catch (EmpleadoException e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }

        System.out.println("=== LISTA DE EMPLEADOS REGISTRADOS ===");
        // 3. CORRECCIÓN: Usamos obtenerSueldosTodos() dentro de un println
        System.out.println(manager.obtenerSueldosTodos());

        System.out.println("\n");
        
        // 4. CORRECCIÓN: Buscamos usando el legajo en formato texto
        System.out.println("--- Buscar por legajo (1001-26) ---");
        Empleado empB = manager.buscarPorLegajo("1001-26");
        if (empB != null) {
            System.out.println("Legajo: " + empB.getLegajo() + " | Nombre: " + empB.getNombre() + 
                               " | Documento: " + empB.getDocumento() + " | Sueldo Neto: $" + empB.calcularSueldoNeto());
        } else {
            System.out.println("Empleado no encontrado.");
        }
        System.out.println();

        
        System.out.println("--- Modificar categoría de Administrativo (Legajo 1003-26) ---");
        Empleado empC = manager.buscarPorLegajo("1003-26");
        if (empC instanceof Administrativo) {
            Administrativo admC = (Administrativo) empC;
            System.out.println("Categoría anterior: " + admC.getCategoria() + " | Sueldo Neto Anterior: $" + admC.calcularSueldoNeto());
            
            admC.setCategoria('B'); // Cambiamos de A a B
            
            System.out.println("Nueva Categoría: " + admC.getCategoria() + " | Nuevo Sueldo Neto: $" + admC.calcularSueldoNeto());
        }
        System.out.println();

        
        System.out.println("--- Agregar título a Profesional (Legajo 1002-26) ---");
        Empleado empD = manager.buscarPorLegajo("1002-26");
        if (empD instanceof Profesional) {
            Profesional profD = (Profesional) empD;
            System.out.println("Sueldo Neto Anterior: $" + profD.calcularSueldoNeto());
            
            profD.agregarTitulo(new Titulo(2024, "Maestría en Gestión", "Universitario"));
            
            System.out.println("Nuevo Sueldo Neto (con título extra): $" + profD.calcularSueldoNeto());
        }
        System.out.println();

        // Empleados de una categoría X y total acumulado de rubros
        System.out.println("--- Empleados Administrativos de Categoría 'C' ---");
        char categoriaBuscada = 'C';
        
        double totalRemunerativos = 0.0;
        double totalSalarioFamiliar = 0.0;
        double totalDescuentos = 0.0;
        double totalNeto = 0.0;

        for (Empleado e : manager.getEmpleados()) {
            if (e instanceof Administrativo) {
                Administrativo adm = (Administrativo) e;
                if (adm.getCategoria() == categoriaBuscada) {
                    System.out.println("- " + adm.getNombre() + " (Legajo: " + adm.getLegajo() + ")");

                    // Cálculos individuales para acumular
                    double adicionalCat = 55000.0; // Categoría C
                    double rem = Empleado.sueldoBasico + adicionalCat + adm.calcularAntiguedad();
                    double fam = adm.calcularSalarioFamiliar();
                    double desc = rem * 0.18;
                    double neto = adm.calcularSueldoNeto();

                    totalRemunerativos += rem;
                    totalSalarioFamiliar += fam;
                    totalDescuentos += desc;
                    totalNeto += neto;
                }
            }
        }

        System.out.println("TOTALES ACUMULADOS (Categoría " + categoriaBuscada + "):");
        System.out.println("  * Total Remunerativos Bonificables: $" + totalRemunerativos);
        System.out.println("  * Total Salario Familiar: $" + totalSalarioFamiliar);
        System.out.println("  * Total Descuentos: $" + totalDescuentos);
        System.out.println("  * Total Importe Neto: $" + totalNeto);
        System.out.println();

        
        // Calcular importe neto acumulado por tipo solicitado al usuario
        System.out.println("--- Acumulado por tipo de empleado ---");
        System.out.println("Ingrese el tipo de empleado a consultar (1: Profesional, 2: Administrativo, 3: Limpieza): ");
        int opcion = scanner.nextInt();

        double acumuladoNetoTipo = 0.0;

        for (Empleado e : manager.getEmpleados()) {
            if (opcion == 1 && e instanceof Profesional) {
                acumuladoNetoTipo += e.calcularSueldoNeto();
            } else if (opcion == 2 && e instanceof Administrativo) {
                acumuladoNetoTipo += e.calcularSueldoNeto();
            } else if (opcion == 3 && e instanceof Limpieza) {
                acumuladoNetoTipo += e.calcularSueldoNeto();
            }
        }

        System.out.println("El importe neto acumulado para el tipo seleccionado es: $" + acumuladoNetoTipo);

        scanner.close();
        
    }
}
