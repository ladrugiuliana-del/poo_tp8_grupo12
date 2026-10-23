package ar.edu.unju.fi.poo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ar.edu.unju.fi.poo.manager.ManagerEmpleado;
import ar.edu.unju.fi.poo.model.Administrativo;
import ar.edu.unju.fi.poo.model.Limpieza;
import ar.edu.unju.fi.poo.model.Profesional;
import ar.edu.unju.fi.poo.model.Titulo;

public class ManagerEmpleadoTest {

    private ManagerEmpleado manager;

    @BeforeEach
    void setUp() throws Exception {
        manager = new ManagerEmpleado();

        // Formato obligatorio del legajo: NNNN-YY (sufijo del año actual)
        String anioActual = String.valueOf(LocalDate.now().getYear()).substring(2);

        // 1. Carga de un Profesional con 1 título registrado
        Profesional prof1 = new Profesional("1001-" + anioActual, 30111111, "Carlos Gómez", LocalDate.now(), 2);
        prof1.agregarTitulo(new Titulo(2018, "Ingeniería en Sistemas", "Universitario"));

        // 2. Carga de Administrativos (2 de Categoría 'A' y 1 de Categoría 'B')
        Administrativo adm1 = new Administrativo("1002-" + anioActual, 30222222, "Laura Ríos", LocalDate.now(), 1, 'A');
        Administrativo adm2 = new Administrativo("1003-" + anioActual, 30333333, "Pedro Gómez", LocalDate.now(), 0, 'A');
        Administrativo adm3 = new Administrativo("1004-" + anioActual, 30444444, "Ana Martínez", LocalDate.now(), 0, 'B');

        // 3. Carga de un empleado de Limpieza
        Limpieza limp1 = new Limpieza("1005-" + anioActual, 30555555, "Jorge Torres", LocalDate.now(), 0);

        // Agregamos los empleados al manager validando sus reglas
        manager.agregarEmpleado(prof1);
        manager.agregarEmpleado(adm1);
        manager.agregarEmpleado(adm2);
        manager.agregarEmpleado(adm3);
        manager.agregarEmpleado(limp1);
    }

    // Pruebas para: obtenerAdministrativosPorCategoria(char categoria)
    @Test
    @DisplayName("Filtrar administrativos por categoría existente ('A')")
    void testObtenerAdministrativosPorCategoriaExistente() {
        List<Administrativo> listaCatA = manager.obtenerAdministrativosPorCategoria('A');

        assertNotNull(listaCatA, "La lista retornada no debe ser nula");
        assertEquals(2, listaCatA.size(), "Debe encontrar exactamente 2 administrativos de categoría 'A'");
        assertTrue(listaCatA.stream().allMatch(a -> a.getCategoria() == 'A'), 
                "Todos los objetos devueltos deben ser de categoría 'A'");
    }

    @Test
    @DisplayName("Filtrar administrativos por categoría sin coincidencias ('Z')")
    void testObtenerAdministrativosPorCategoriaInexistente() {
        List<Administrativo> listaCatZ = manager.obtenerAdministrativosPorCategoria('Z');

        assertNotNull(listaCatZ, "La lista retornada no debe ser nula");
        assertTrue(listaCatZ.isEmpty(), "La lista debe estar vacía si no hay coincidencias");
    }

    // Pruebas para: calcularNetoAcumuladoPorTipo(int opcionTipo)
    @Test
    @DisplayName("Calcular neto acumulado para tipo Profesional (Opción 1)")
    void testCalcularNetoAcumuladoProfesional() {
        // Cálculo para prof1:
        // Remunerativo = $400.000 (básico) + $30.000 (1 título) = $430.000
        // Descuentos (18%) = $77.400 | Salario Familiar (2 hijos) = $30.000
        // Neto = 430.000 + 30.000 - 77.400 = $382.600
        double esperado = 382600.0;
        double resultado = manager.calcularNetoAcumuladoPorTipo(1);

        assertEquals(esperado, resultado, 0.01, "El acumulado neto para profesionales debe ser $382.600");
    }

    @Test
    @DisplayName("Calcular neto acumulado para tipo Administrativo (Opción 2)")
    void testCalcularNetoAcumuladoAdministrativo() {
        // adm1 ('A', 1 hijo)  => Neto: $367.600
        // adm2 ('A', 0 hijos) => Neto: $352.600
        // adm3 ('B', 0 hijos) => Neto: $364.900
        // Total acumulado = $1.085.100
        double esperado = 1085100.0;
        double resultado = manager.calcularNetoAcumuladoPorTipo(2);

        assertEquals(esperado, resultado, 0.01, "El acumulado neto para administrativos debe ser $1.085.100");
    }

    @Test
    @DisplayName("Calcular neto acumulado para tipo Limpieza (Opción 3)")
    void testCalcularNetoAcumuladoLimpieza() {
        // limp1 (0 hijos):
        // Remunerativo = $400.000 (básico) + $25.000 (insalubridad) = $425.000
        // Descuentos (18%) = $76.500 | Neto = $348.500
        double esperado = 348500.0;
        double resultado = manager.calcularNetoAcumuladoPorTipo(3);

        assertEquals(esperado, resultado, 0.01, "El acumulado neto para personal de limpieza debe ser $348.500");
    }

    @Test
    @DisplayName("Calcular neto acumulado ingresando un tipo de opción inválida")
    void testCalcularNetoAcumuladoOpcionInvalida() {
        double resultado = manager.calcularNetoAcumuladoPorTipo(99);

        assertEquals(0.0, resultado, 0.001, "Una opción diferente de 1, 2 o 3 debe retornar 0.0");
    }
}