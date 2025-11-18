package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;


public class FiltroPuertoDestino implements Busqueda{

	private TerminalPortuaria puertoDestino;

	public FiltroPuertoDestino(TerminalPortuaria puertoDestino) {
		this.puertoDestino = puertoDestino;
	}


	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.puertoDeLlegada() == this.puertoDestino).toList();
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
