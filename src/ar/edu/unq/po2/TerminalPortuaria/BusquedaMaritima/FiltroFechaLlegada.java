package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;


public class FiltroFechaLlegada implements Busqueda{

	private LocalDateTime fechaLlegada;


	public FiltroFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	//FILTRA VIAJES CON FECHA DADO POR fechaLlegada

	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.fechaDeLlegada() == this.fechaLlegada).toList();
	}



//	public LocalDateTime getFechaLlegada() {
//		return fechaLlegada;
//	}


}
