package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Viaje;


public class OperadorOR implements Busqueda {

	protected List<Busqueda> viajes = new ArrayList<>();

	public OperadorOR(String nombre) {

	    }

	public void Agregar(Busqueda v) {
		// TODO Auto-generated method stub
		viajes.add(v);
	}

	public void Remover(Busqueda v) {
		// TODO Auto-generated method stub
		viajes.remove(v);
	}


	@Override
	public List<Viaje> filtrar(List<Viaje> listaDeViajes) {
		List<Viaje> viajesFiltrados = new ArrayList<>();
		for (Busqueda v : this.viajes) {
			viajesFiltrados.addAll(v.filtrar(listaDeViajes));
		}
		return viajesFiltrados;
	}

	public List<Busqueda> getViajes() {
		return this.viajes;
	}	
}
