package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public class FiltroFechaLlegada implements ComponenteBusqueda {
	LocalDateTime fechaLlegada;
	
	public FiltroFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> listaViajes) {
		return listaViajes.stream().filter(v -> v.fechaDeLlegada() == this.fechaLlegada).collect(Collectors.toList());
	}

}
