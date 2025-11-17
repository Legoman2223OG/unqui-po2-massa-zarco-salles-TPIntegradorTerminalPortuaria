package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class Viaje {
	private TerminalPortuaria terminalInicio;
	private TerminalPortuaria terminalDestino;
	private Circuito circuitoDelViaje;
	private LocalDateTime fechaSalida;


	// Constructor
	public Viaje(TerminalPortuaria terminalInicio, TerminalPortuaria terminalDestino, Circuito circuitoDelViaje, LocalDateTime fechaSalida) {
		this.terminalInicio = terminalInicio;
		this.terminalDestino = terminalDestino;
		this.circuitoDelViaje = circuitoDelViaje;
		this.fechaSalida = fechaSalida;
	}


	// Getters
	public TerminalPortuaria getPuertoInicio() {
		return terminalInicio;
	}

	public Circuito getCircuitoDelViaje() {
		return circuitoDelViaje;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}


	// Metodos propios
	public LocalDateTime fechaDeLlegada() {
		return this.fechaSalida.plus(this.duracionDelViaje());
	}
	
	public ArrayList<Tramo> recorridoDelViaje() {
		return this.circuitoDelViaje.recorridoEntre_Y_(terminalInicio, terminalDestino);
	}

	public int numeroDeTerminalesEntreOrigenYDestino() {
		// El numero de terminales que hay entre dos terminales cualesquiera siempre va a ser igual al numero de tramos, menos uno.
		return recorridoDelViaje().size() - 1;
	}

	public Duration duracionDelViaje() {
		// Indica la cantidad de tiempo que va a durar el viaje.
		Duration duracionTotal = Duration.ZERO;

		for (Tramo t : recorridoDelViaje()) {
			duracionTotal = duracionTotal.plus(t.getTiempoDeTranscurso());
		}

		return duracionTotal;
	}

	public Double precioTotal() {
		// Indica el precio acumulativo que implica viajar por cada uno de los tramos del viaje.
		double total = 0.0;

		for (Tramo t : recorridoDelViaje()) {
			total += t.getPrecio();
		}

		return total;
	}

	public Set<TerminalPortuaria> todasLasTerminalesDentroDelViaje() {
		// Devuelve un conjunto con todas las terminales que forman parte del viaje.
		Set<TerminalPortuaria> conjuntoPuertos = new HashSet<>();

		for(Tramo tramoActual : recorridoDelViaje()) {
			conjuntoPuertos.add(tramoActual.getPuertoOrigen());
			conjuntoPuertos.add(tramoActual.getPuertoDestino());
		}

		return conjuntoPuertos;
	}

	public TerminalPortuaria puertoDeLlegada() {
		int indexUltimoElemento = this.recorridoDelViaje().size() - 1;

		return this.recorridoDelViaje().get(indexUltimoElemento).getPuertoDestino();
	}

	public boolean validarSiTerminalExisteEnViaje(TerminalPortuaria terminal) {
		return this.todasLasTerminalesDentroDelViaje().contains(terminal);
	}


	public void siguienteTramo() {
		// TODO Auto-generated method stub
		
	}
}
