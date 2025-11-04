package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.time.LocalTime;
import java.time.Duration;

import java.util.ArrayList;

public class Viaje {
	private Circuito circuitoDelViaje; // Posiblemente no necesite el circuito completo, solamente la lista de tramos.
	private String puertoInicio;
	private ArrayList<Tramo> tramosARecorrer;
	private LocalTime fechaSalida;
	
	
	// Constructor
	public Viaje(Circuito circuitoDelViaje, String puertoInicio, ArrayList<Tramo> tramosARecorrer, LocalTime fechaSalida) {
		this.circuitoDelViaje = circuitoDelViaje;
		this.puertoInicio = puertoInicio;
		this.tramosARecorrer = tramosARecorrer;
		this.fechaSalida = fechaSalida;
	}

	
	// Getters
	public Circuito getCircuitoDelViaje() {
		return circuitoDelViaje;
	}

	public String getPuertoInicio() {
		return puertoInicio;
	}

	public ArrayList<Tramo> getTramosARecorrer() {
		return tramosARecorrer;
	}

	public LocalTime getFechaSalida() {
		return fechaSalida;
	}

	
	// Metodos propios
	public LocalTime fechaDeLlegada() {
		return this.fechaSalida.plus(this.duracionDelViaje());
	}
	
	private Duration duracionDelViaje() {
		// Indica la cantidad de tiempo que va a durar el viaje.
		Duration duracionTotal = Duration.ZERO;
		
		for (Tramo t : tramosARecorrer) {
			duracionTotal.plus(t.getTiempoDeTranscurso());
		}
		
		return duracionTotal;
	}
	
	public Double precioTotal() {
		// Indica el precio acumulativo que implica viajar por cada uno de los tramos del viaje.
		Double total = 0.0;
		
		for (Tramo t : tramosARecorrer) {
			total += t.getPrecio();
		}
		
		return total;
	}
}
