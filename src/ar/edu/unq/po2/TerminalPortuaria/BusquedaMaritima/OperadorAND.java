package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;

public class OperadorAND implements Busqueda{

	List<Busqueda> viajes = new ArrayList<>();

	
	@Override
	public void agregar(Busqueda componente) {
		this.viajes.add(componente);
		
	}

	@Override
	public void remover(Busqueda componente) {
		this.viajes.remove(componente);
	}
		

	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
	    List<Viaje> viajesFiltrados = new ArrayList<>(listaDeViajes);
	    for (Busqueda v : viajes) {
	        viajesFiltrados.retainAll(v.filtrar(listaDeViajes));
	    }
	    return viajesFiltrados;
	}

	public List<Busqueda> getViajes() {
		return this.viajes;
	}

}
