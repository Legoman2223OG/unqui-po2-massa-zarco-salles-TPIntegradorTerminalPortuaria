package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public class FiltroPuertoDestino implements ComponenteBusqueda {
	String puertoDestino;
	
	public FiltroPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> listaViajes) {
		return listaViajes.stream().filter(v -> v.puertoDeLlegada() == puertoDestino).collect(Collectors.toList());
	}

}
