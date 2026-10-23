package ar.edu.unju.fi.poo.test;

import ar.edu.unju.fi.poo.exceptions.EmpleadoException;
import ar.edu.unju.fi.poo.model.Profesional;
import ar.edu.unju.fi.poo.model.Titulo;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

	@Test
    void testProfesionalConTitulosValido() {
		
        Profesional prof1 = new Profesional("1001-26", 38111222, "Carlos Gómez", LocalDate.of(2018, 5, 10), 2);
        prof1.agregarTitulo(new Titulo(2015, "Ingeniería en Sistemas", "Universitario"));
        prof1.agregarTitulo(new Titulo(2020, "Diplomatura en Datos", "Universitario"));

        assertDoesNotThrow(() -> {
            validarProfesional(prof1); 
        });
    }

    @Test
    void testProfesionalSinTitulosLanzaExcepcion() {
        // Caso inválido: Profesional sin títulos
        Profesional profSinTitulos = new Profesional("1002-26", 39222333, "Ana Martínez", LocalDate.of(2021, 3, 15), 0);

        // Verificamos que se lance la excepción personalizada al no tener títulos
        assertThrows(EmpleadoException.class, () -> {
            validarProfesional(profSinTitulos);
        });
    }

    // EJEMPLO de cómo se evaluaría la regla de la actividad 2 inciso e -----> hay que borrarlo después!!!!
    private void validarProfesional(Profesional prof) throws EmpleadoException {
        if (prof.getTitulos() == null || prof.getTitulos().isEmpty()) {
            throw new EmpleadoException("El profesional debe tener al menos un título Universitario o Terciario.");
        }
    }
}