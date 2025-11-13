package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.util.Comparator;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public class E_MenorTiempo extends E_MejorRuta {

	public E_MenorTiempo() {}

	@Override
	protected Viaje criterioDeSeleccion(Set<Viaje> mapa) {
		return mapa.stream()
				   .min(Comparator.comparing(Viaje::duracionDelViaje))
				   .orElse(null);
	}

}
