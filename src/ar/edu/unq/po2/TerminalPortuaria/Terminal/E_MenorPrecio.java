package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.util.Comparator;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public class E_MenorPrecio extends E_MejorRuta {

	public E_MenorPrecio() {}

	@Override
	protected Viaje criterioDeSeleccion(Set<Viaje> mapa) {
		return mapa.stream()
				   .min(Comparator.comparing(Viaje::precioTotal))
				   .orElse(null);
	}

}
