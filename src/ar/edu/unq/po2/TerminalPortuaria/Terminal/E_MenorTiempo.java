package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.util.Comparator;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public class E_MenorTiempo extends E_MejorRuta {

	public E_MenorTiempo(TerminalPortuaria terminalOrigen) {
		super(terminalOrigen);
	}

	@Override
	public Circuito mejorCircuitoHacia(TerminalPortuaria terminalDestino) {
		return viajesPorCadaCircuito(terminalDestino).get(
				viajesDesdeOrigenHasta(terminalDestino).stream()
											   	       .min(Comparator.comparing(Viaje::duracionDelViaje))
												       .get()
		);
	}
}
