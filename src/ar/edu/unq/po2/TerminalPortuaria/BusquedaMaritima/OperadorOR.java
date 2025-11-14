package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;

public class OperadorOR implements Busqueda {

	protected List<Busqueda> viajes = new ArrayList<>();


	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		List<Viaje> viajesFiltrados = new ArrayList<>();
		for (Busqueda v : this.viajes) {
			viajesFiltrados.addAll(v.filtrar(listaDeViajes));
		}
		return viajesFiltrados.stream().distinct().toList();
	}

	@Override
	public void agregar(Busqueda componente) {
		this.viajes.add(componente);
		
	}

	@Override
	public void remover(Busqueda componente) {
		this.viajes.remove(componente);
		
	}
	
	public List<Busqueda> getViajes() {
		return this.viajes;
	}


}
