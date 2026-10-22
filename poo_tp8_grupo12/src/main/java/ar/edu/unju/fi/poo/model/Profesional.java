package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Profesional extends Empleado {
	List<Titulo> titulos;

	public Profesional(int legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
		super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
		this.titulos = new ArrayList<>();
	}
	
	@Override
	public double calcularSueldoNeto() {
        double adicionalTitulos = this.titulos.size() * 30000.0;

        double remunerativos = Empleado.sueldoBasico + adicionalTitulos + calcularAntiguedad();

        double descuentos = remunerativos * 0.18;

        double salarioFamiliar = calcularSalarioFamiliar();

        //Sueldo Neto
        return remunerativos + salarioFamiliar - descuentos;
	}
	
	public void agregarTitulo(Titulo titulonuevo) {
		titulos.add(titulonuevo);
		System.out.println("Titulo agregado exitosamente");
	}
}
