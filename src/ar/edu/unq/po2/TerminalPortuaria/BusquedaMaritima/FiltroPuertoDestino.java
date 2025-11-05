package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;


public class FiltroPuertoDestino implements Busqueda{

	private String puertoDestino;

	public FiltroPuertoDestino(String puertoDestino) {
		this.puertoDestino = puertoDestino;
	}


	public String getPuertoDestino()
	{
		return puertoDestino;
	}


	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		return listaDeViajes.stream().filter(v -> v.getPuertoInicio() == this.puertoDestino).toList();
	}

	
	
}
