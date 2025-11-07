package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;


public class FiltroPuertoDestino implements Busqueda{

	private TerminalPortuaria puertoDestino;

	public FiltroPuertoDestino(TerminalPortuaria puertoDestino) {
		this.puertoDestino = puertoDestino;
	}


	public TerminalPortuaria getPuertoDestino()
	{
		return puertoDestino;
	}


	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.getPuertoInicio() == this.puertoDestino).toList();
	}

	
	
}
