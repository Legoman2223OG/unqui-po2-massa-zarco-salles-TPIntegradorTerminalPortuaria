package ar.edu.unq.po2.TerminalPortuaria.Factura;

import java.time.LocalDateTime;

public class Concepto {

	private String nombre;
	private LocalDateTime fecha;
	private double monto;
	
	
	
	public Concepto(String nombre, LocalDateTime fecha, double monto) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.monto = monto;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public LocalDateTime getFecha() {
		return this.fecha;
	}
	
	public double getMonto() {
		return this.monto;
	}
	
	public String toString()
	{
		return "Nombre: " + nombre + "\tfecha: " + fecha + "\tmonto: " + monto;
	}
	
}
