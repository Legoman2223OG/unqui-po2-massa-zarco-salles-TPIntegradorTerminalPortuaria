package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.util.Comparator;
import java.util.HashMap;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public class E_MenorPrecio extends E_MejorRuta {

	public E_MenorPrecio() {}

	@Override
	public Circuito mejorCircuitoHacia(TerminalPortuaria terminalOrigen, TerminalPortuaria terminalDestino) {
		final HashMap<Viaje,Circuito> mapa = viajesPorCadaCircuito(terminalOrigen, terminalDestino);

	    var viajeOptimo = mapa.keySet().stream()
	                          .min(Comparator.comparing(Viaje::precioTotal))
	                          .orElse(null);

	    return mapa.get(viajeOptimo);
	}

}
