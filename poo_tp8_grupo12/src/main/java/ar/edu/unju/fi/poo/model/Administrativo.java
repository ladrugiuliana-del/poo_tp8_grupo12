package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Administrativo extends Empleado {
	Character categoria;

	public Administrativo(String legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos, Character categoria) {
		super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
		this.categoria = categoria;
	}
	
	@Override
	public double calcularAdicional() {
        double adicionalCategoria = 0.0;
        switch (this.categoria) {
            case 'A':
                adicionalCategoria = 30000.0; // Auxiliar
                break;
            case 'B':
                adicionalCategoria = 45000.0; // Ventas
                break;
            case 'C':
                adicionalCategoria = 55000.0; // Gerencia
                break;
            default:
                System.out.println("Categoría no válida. Se asume 0$ de adicional.");
                break;
        }

        return adicionalCategoria;
	}
	
	public void setCategoria(Character caracter) {
		this.categoria = caracter;
	}
	
	public char getCategoria() {
		return categoria;
	}
}