package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {
	private int legajo;
	private int documento;
	private String nombre;
	private LocalDate fechaIngreso;
	private int cantidadHijos;
	public static double sueldoBasico = 400000.0;
	
	public Empleado(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
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
	
	public void mostrarSueldo() {
		System.out.println("El sueldo del empleado " + nombre + "es de: $" + calcularSueldoNeto());
	}
	
	public void mostrarEmpleado() {
		System.out.println("Legajo: " + legajo);
		System.out.println("Documento: " + documento);
		System.out.println("Nombre: " + nombre);
		System.out.println("Fecha de Ingreso: " + fechaIngreso);
		System.out.println("Cantidad de Hijos: " + cantidadHijos);
		System.out.println("Sueldo Neto " + calcularSueldoNeto());
	}

	public int getLegajo() {
		return legajo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getDocumento() {
		return documento;
	}
}


