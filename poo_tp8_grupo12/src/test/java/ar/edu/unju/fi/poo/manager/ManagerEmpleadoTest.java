package ar.edu.unju.fi.poo.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ar.edu.unju.fi.poo.exceptions.EmpleadoException;
import ar.edu.unju.fi.poo.model.Administrativo;
import ar.edu.unju.fi.poo.model.Empleado;

public class ManagerEmpleadoTest {

    private ManagerEmpleado manager;

    // Punto 1. @BeforeEach: Se ejecuta antes de cada test.
    // se usa el metodo cargarDatosPrueba del integrante 2 Kikin.
    @BeforeEach
    void setUp() {
        manager = new ManagerEmpleado();
        try {
            manager.cargarDatosPrueba();
        } catch (EmpleadoException e) {
            fail("Falló la carga de datos inicial: " + e.getMessage());
        }
    }

    // Punto 2. Pruebas para agregarEmpleado()

    @Test
    void testAgregarEmpleadoValido() {
        // Obtenemos los 2 últimos dígitos del año actual para el legajo correcto
        String anioActual = String.valueOf(LocalDate.now().getYear()).substring(2);
        Administrativo admin = new Administrativo("9999-" + anioActual, 99888777, "Test", LocalDate.now(), 0, 'A');
        
        assertDoesNotThrow(() -> {
            manager.agregarEmpleado(admin);
        }, "no debería lanzar excepción con un empleado vlido");
    }

    // assertThrows: verificacion por si falle si el documento está repetido
    @Test
    void testAgregarEmpleadoDocumentoRepetidoLanzaExcepcion() {
        String anioActual = String.valueOf(LocalDate.now().getYear()).substring(2);
        // Creamos un empleado con documento 35444555, que ya existe en cargarDatosPrueba() ("Laura")
        Administrativo admin = new Administrativo("8888-" + anioActual, 35444555, "CopiaLaura", LocalDate.now(), 0, 'A');

        assertThrows(EmpleadoException.class, () -> {
            manager.agregarEmpleado(admin);
        }, "deberia lanzar EmpleadoException por documento repetido");
    }

    // @Disabled: Dejamos un test deshabilitado como pide el práctico exactamente el punto que me toco (ojala este correcto xd)
    @Disabled("Deshabilitado temporalmente: Prueba de legajo inválido")
    @Test
    void testAgregarEmpleadoLegajoInvalidoLanzaExcepcion() {
        Administrativo admin = new Administrativo("123-26", 11223344, "ErrorLegajo", LocalDate.now(), 0, 'A');
        assertThrows(EmpleadoException.class, () -> manager.agregarEmpleado(admin));
    }


    // Punto 3. Pruebas para obtenerSueldosTodos()

    // @RepeatedTest y assertTrue: Verificamos que el String no esté vacío y contenga los sueldos
    @RepeatedTest(3)
    void testObtenerSueldosTodos() {
        String detalleSueldos = manager.obtenerSueldosTodos();
        
        assertNotNull(detalleSueldos, "El string de sueldos no debe ser null");
        assertTrue(detalleSueldos.length() > 0, "el detalle de sueldos no debe estar vacío");
        assertTrue(detalleSueldos.contains("Laura"), "el detalle debe contener al empleado Laura");
    }


    // Punto 4. Pruebas para buscarPorLegajo()

    // @ParameterizedTest y @ValueSource: Probamos buscar varios legajos que sabemos que existen
    @ParameterizedTest
    @ValueSource(strings = {"1111-26", "2222-26", "4444-26"})
    void testBuscarVariosLegajosValidos(String legajo) {
        Empleado emp = manager.buscarPorLegajo(legajo);
        
        assertNotNull(emp, "Deberia encontrar al empleado con legajo " + legajo);
        assertEquals(legajo, emp.getLegajo(), "el legajo encontrado debe coincidir con el buscado");
    }

    // assertNull: Probamos buscar un legajo que (no) existe
    @Test
    void testBuscarPorLegajoNoExistenteDevuelveNull() {
        Empleado emp = manager.buscarPorLegajo("0000-00");
        assertNull(emp, "si el legajo no existe, debe devolver null");
    }
}