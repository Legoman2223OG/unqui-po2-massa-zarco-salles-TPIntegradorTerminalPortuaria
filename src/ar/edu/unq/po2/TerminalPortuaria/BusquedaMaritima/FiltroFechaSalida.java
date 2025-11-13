package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;


public class FiltroFechaSalida implements Busqueda{

	private LocalDateTime fechaDeSalida;


	public FiltroFechaSalida(LocalDateTime fecha) {
		this.fechaDeSalida = fecha;
	}

	//FILTRA VIAJES CON DESTINO DADO POR fechaDestino
	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.getFechaSalida().equals(this.fechaDeSalida)).toList();
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
