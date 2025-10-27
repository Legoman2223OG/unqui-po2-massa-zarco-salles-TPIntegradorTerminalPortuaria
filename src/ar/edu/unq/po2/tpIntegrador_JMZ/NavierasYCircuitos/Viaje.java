package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.time.LocalTime;

public class Viaje {
	private Circuito circuitoDelViaje;
	private TerminalPortuaria puertoInicio;
	private TerminalPortuaria puertoDestino;
	private LocalTime fechaSalida;
	
	// Constructor
	public Viaje(Circuito circuitoDelViaje, TerminalPortuaria puertoInicio, TerminalPortuaria puertoDestino, LocalTime fechaSalida) {
		this.circuitoDelViaje = circuitoDelViaje;
		this.puertoInicio = puertoInicio;
		this.puertoDestino = puertoDestino;
		this.fechaSalida = fechaSalida;
	}

	// Getters
	public Circuito getCircuitoDelViaje() {
		return circuitoDelViaje;
	}

	public TerminalPortuaria getPuertoInicio() {
		return puertoInicio;
	}

	public TerminalPortuaria getPuertoDestino() {
		return puertoDestino;
	}

	public LocalTime getFechaSalida() {
		return fechaSalida;
	}

	// Metodos propios
	public LocalTime fechaDeLlegada() {
		// Falta implementar bien.
		return LocalTime.NOON;
	}
}
