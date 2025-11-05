package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.time.Duration;

public class Tramo {
	private final String puertoOrigen;
	private final String puertoDestino;
	private final Duration tiempoDeTranscurso;
	private Double precio;
	
	// Constructor
	public Tramo(String puertoOrigen, String puertoDestino, Duration tiempoDeTranscurso, Double precio) {
		this.puertoOrigen = puertoOrigen;
		this.puertoDestino = puertoDestino;
		this.tiempoDeTranscurso = tiempoDeTranscurso;
		this.precio = precio;
	}
	
	// Getters
	public String getPuertoOrigen() {
		return puertoOrigen;
	}
	public String getPuertoDestino() {
		return puertoDestino;
	}
	public Duration getTiempoDeTranscurso() {
		return tiempoDeTranscurso;
	}
	public Double getPrecio() {
		return precio;
	}

	// Metodos propios
	public Boolean iniciaEnPuertoDado(String puerto) {
		return this.puertoOrigen.equalsIgnoreCase(puerto);
	}
	
	public Boolean terminaEnPuertoDado(String puerto) {
		return this.puertoDestino.equalsIgnoreCase(puerto);
	}
}
