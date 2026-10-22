package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;

public class Limpieza extends Empleado{

	public Limpieza(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
		super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
	}

	@Override
	public double calcularSueldoNeto() {
		double adicionalInsalubridad = 25000.0;

        double remunerativos = Empleado.sueldoBasico + adicionalInsalubridad + calcularAntiguedad();

        double descuentos = remunerativos * 0.18;

        double salarioFamiliar = calcularSalarioFamiliar();

        //Sueldo Neto
        return remunerativos + salarioFamiliar - descuentos;
	}
	
}