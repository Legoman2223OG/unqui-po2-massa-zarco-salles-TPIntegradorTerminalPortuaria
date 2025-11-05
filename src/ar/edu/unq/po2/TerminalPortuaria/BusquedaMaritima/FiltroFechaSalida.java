package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;


public class FiltroFechaSalida implements Busqueda{

	private LocalDateTime fechaDeSalida;


	public FiltroFechaSalida(LocalDateTime fecha) {
		this.fechaDeSalida = fecha;
	}

	//FILTRA VIAJES CON DESTINO DADO POR fechaDestino
	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.getFechaDeSalida() == this.fechaDeSalida).toList();
	}



	public LocalDateTime getFechaDestino() {
		return fechaDeSalida;
	}
	
	
}
