package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public class OperadorAND implements Busqueda{

	List<Busqueda> viajes = new ArrayList<>();

	public OperadorAND() {
    }


	public void Agregar(Busqueda componente) {
		// TODO Auto-generated method stub
		viajes.add(componente);
	}


	public void Remover(Busqueda componente) {
		// TODO Auto-generated method stub
		viajes.remove(componente);
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
