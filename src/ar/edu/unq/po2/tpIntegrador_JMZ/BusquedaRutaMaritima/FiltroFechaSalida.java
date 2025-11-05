package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public class FiltroFechaSalida implements ComponenteBusqueda {
	LocalDateTime fechaSalida;
	
	public FiltroFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> listaViajes) {
		return listaViajes.stream().filter(v -> v.getFechaSalida() == this.fechaSalida).collect(Collectors.toList());
	}

}
