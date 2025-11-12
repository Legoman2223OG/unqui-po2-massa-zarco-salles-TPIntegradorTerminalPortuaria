package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.util.ArrayList;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;

public class WrapperRecorrido {
	private final Buque buque;
	private ArrayList<Viaje> listaViajes;

	// Constructor
	public WrapperRecorrido(Buque buque, ArrayList<Viaje> listaViajes) {
		this.buque = buque;
		this.listaViajes = listaViajes;
	}

	// Getter
	public Buque getBuque() {
		return buque;
	}

	public ArrayList<Viaje> getListaViajes() {
		return listaViajes;
	}

	// Metodos propios
	public void agregarViaje(Viaje viaje) {
		this.listaViajes.add(viaje);
	}

	public Buque getBuqueDeViaje(Viaje viaje) {
	    return listaViajes.contains(viaje) ? buque : null;
	}
}
