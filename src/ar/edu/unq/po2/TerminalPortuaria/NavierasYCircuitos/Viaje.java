package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class Viaje {
	private TerminalPortuaria terminalInicio;
	private ArrayList<Tramo> tramosARecorrer;
	private LocalDateTime fechaSalida;
	
	
	// Constructor
	public Viaje(TerminalPortuaria terminalInicio, ArrayList<Tramo> tramosARecorrer, LocalDateTime fechaSalida) {
		this.terminalInicio = terminalInicio;
		this.tramosARecorrer = tramosARecorrer;
		this.fechaSalida = fechaSalida;
	}

	
	// Getters
	public TerminalPortuaria getPuertoInicio() {
		return terminalInicio;
	}

	public ArrayList<Tramo> getTramosARecorrer() {
		return tramosARecorrer;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	
	// Metodos propios
	public LocalDateTime fechaDeLlegada() {
		return this.fechaSalida.plus(this.duracionDelViaje());
	}
	
	public int numeroDeTerminalesEntreOrigenYDestino() {
		// El numero de terminales que hay entre dos terminales cualesquiera siempre va a ser igual al numero de tramos, menos uno.
		return tramosARecorrer.size() - 1;
	}
	
	public Duration duracionDelViaje() {
		// Indica la cantidad de tiempo que va a durar el viaje.
		Duration duracionTotal = Duration.ZERO;
		
		for (Tramo t : tramosARecorrer) {
			duracionTotal = duracionTotal.plus(t.getTiempoDeTranscurso());
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
	
	public TerminalPortuaria puertoDeLlegada() {
		int indexUltimoElemento = this.tramosARecorrer.size() - 1;
		
		return this.tramosARecorrer.get(indexUltimoElemento).getPuertoDestino();
	}
}
