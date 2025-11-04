package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.ArrayList;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public class FiltroPuertoDestino implements ComponenteBusqueda {
	TerminalPortuaria puertoDestino;
	
	public FiltroPuertoDestino(TerminalPortuaria puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	@Override
	public ArrayList<Viaje> filtrar(ArrayList<Viaje> listaViajes) {
		// TODO Auto-generated method stub
		return null;
	}

}
