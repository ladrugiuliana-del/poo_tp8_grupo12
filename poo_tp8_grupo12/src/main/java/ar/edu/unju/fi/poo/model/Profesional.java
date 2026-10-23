package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Profesional extends Empleado {
	List<Titulo> titulos;

	public Profesional(String legajo, int documento, String nombre, LocalDate fechaIngreso, int cantidadHijos) {
		super(legajo, documento, nombre, fechaIngreso, cantidadHijos);
		this.titulos = new ArrayList<>();
	}
	
	@Override
	public double calcularAdicional() {
        double adicionalTitulos = this.titulos.size() * 30000.0;

        return adicionalTitulos;
	}

	public void agregarTitulo(Titulo titulonuevo) {
		titulos.add(titulonuevo);
	}
	
	public List<Titulo> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}
}