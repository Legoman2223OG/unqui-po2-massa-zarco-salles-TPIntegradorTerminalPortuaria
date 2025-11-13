package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;


public class FiltroFechaLlegada implements Busqueda{

	private LocalDateTime fechaLlegada;


	public FiltroFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	//FILTRA VIAJES CON FECHA DADO POR fechaLlegada

	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.fechaDeLlegada().equals(this.fechaLlegada)).toList();
	}


	@Override
	public void agregar(Busqueda componente) {
		throw new UnsupportedOperationException("No es posible agregar");
		
	}

	@Override
	public void remover(Busqueda componente) {
		throw new UnsupportedOperationException("No es posible remover");
		
	}




}
