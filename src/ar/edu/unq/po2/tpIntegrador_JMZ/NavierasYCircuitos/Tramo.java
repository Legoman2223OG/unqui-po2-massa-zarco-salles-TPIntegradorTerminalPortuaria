package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.time.LocalTime;

public class Tramo {
	private final TerminalPortuaria puertoOrigen;
	private final TerminalPortuaria puertoDestino;
	private final LocalTime tiempoDeTranscurso;
	private Double precio;
	
	// Constructor
	public Tramo(TerminalPortuaria puertoOrigen, TerminalPortuaria puertoDestino, LocalTime tiempoDeTranscurso, Double precio) {
		this.puertoOrigen = puertoOrigen;
		this.puertoDestino = puertoDestino;
		this.tiempoDeTranscurso = tiempoDeTranscurso;
		this.precio = precio;
	}
	
	// Getters
	public TerminalPortuaria getPuertoOrigen() {
		return puertoOrigen;
	}
	public TerminalPortuaria getPuertoDestino() {
		return puertoDestino;
	}
	public LocalTime getTiempoDeTranscurso() {
		return tiempoDeTranscurso;
	}
	public Double getPrecio() {
		return precio;
	}

	// Metodos propios
	public Boolean iniciaEnPuertoDado(TerminalPortuaria puerto) {
		return this.puertoOrigen==puerto;
	}
	
	public Boolean terminaEnPuertoDado(TerminalPortuaria puerto) {
		return this.puertoDestino==puerto;
	}
}
