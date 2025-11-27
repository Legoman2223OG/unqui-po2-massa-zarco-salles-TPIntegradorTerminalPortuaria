package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;

public class WrapperRecorrido {
	private final Buque buque;
	private List<Viaje> listaViajes;

	// Constructor
	public WrapperRecorrido(Buque buque, List<Viaje> listaViajes) {
		this.buque = buque;
		this.listaViajes = listaViajes;
	}

	// Getter
	public List<Viaje> getListaViajes() {
		return listaViajes;
	}

	// Metodos propios
	public Buque getBuqueDeViaje(Viaje viaje) {
	    return listaViajes.contains(viaje) ? buque : null;
	}
}
