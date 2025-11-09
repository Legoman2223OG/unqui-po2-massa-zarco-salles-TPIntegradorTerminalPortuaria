package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.time.Duration;

import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class Tramo {
	private final TerminalPortuaria terminalOrigen;
	private final TerminalPortuaria terminalDestino;
	private final Duration tiempoDeTranscurso;
	private Double precio;
	
	// Constructor
	public Tramo(TerminalPortuaria terminalOrigen, TerminalPortuaria terminalDestino, Duration tiempoDeTranscurso, Double precio) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.tiempoDeTranscurso = tiempoDeTranscurso;
		this.precio = precio;
	}
	
	// Getters
	public TerminalPortuaria getPuertoOrigen() {
		return terminalOrigen;
	}
	public TerminalPortuaria getPuertoDestino() {
		return terminalDestino;
	}
	public Duration getTiempoDeTranscurso() {
		return tiempoDeTranscurso;
	}
	public Double getPrecio() {
		return precio;
	}

	// Metodos propios
	public Boolean iniciaEnPuertoDado(TerminalPortuaria puerto) {
		return this.terminalOrigen == puerto;
	}
	
	public Boolean terminaEnPuertoDado(TerminalPortuaria puerto) {
		return this.terminalDestino == puerto;
	}
}
