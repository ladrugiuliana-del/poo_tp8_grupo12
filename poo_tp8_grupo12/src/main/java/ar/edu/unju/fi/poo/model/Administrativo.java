package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Administrativo extends Empleado{
	Character categoria;

	public Administrativo(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos,
			Character categoria) {
		super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
		this.categoria = categoria;
	}
	
	@Override
	public double calcularSueldoNeto() {
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

        double remunerativos = Empleado.sueldoBasico + adicionalCategoria + calcularAntiguedad();

        double descuentos = remunerativos * 0.18;

        double salarioFamiliar = calcularSalarioFamiliar();

        //Sueldo Neto
        return remunerativos + salarioFamiliar - descuentos;
	}
	
	public void setCategoria(Character caracter) {
		this.categoria = caracter;
	}
	
	public char getCategoria() {
		return categoria;
	}
}

