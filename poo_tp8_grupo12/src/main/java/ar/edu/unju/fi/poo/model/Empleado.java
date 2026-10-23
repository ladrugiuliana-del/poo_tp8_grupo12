package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {
	private String legajo;
	private int documento;
	private String nombre;
	private LocalDate fechaIngreso;
	private int cantidadHijos;
	public static double sueldoBasico = 400000.0;
	
	public Empleado(String legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
		super();
		this.legajo = legajo;
		this.documento = documento;
		this.nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.cantidadHijos = cantidadHijos;
	}

	public abstract double calcularSueldoNeto();
	
	public double calcularAntiguedad() {
		if (fechaIngreso == null) return 0.0;
        int anios = Period.between(fechaIngreso, LocalDate.now()).getYears();
        return anios * 6500.0;
	}
	
	public double calcularSalarioFamiliar() {
		return cantidadHijos * 15000.0;
	}
	
	@Override
	public String toString() {
		return "Legajo: " + legajo + " | Documento: " + documento + 
		       " | Nombre: " + nombre + " | Fecha Ingreso: " + fechaIngreso + 
		       " | Hijos: " + cantidadHijos + " | Sueldo Neto: $" + calcularSueldoNeto();
	}

	public String getLegajo() {
		return legajo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getDocumento() {
		return documento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public int getCantidadHijos() {
		return cantidadHijos;
	}
}