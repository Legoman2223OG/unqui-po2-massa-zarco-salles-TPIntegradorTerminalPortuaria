package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.List;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.Viaje;

public class OperadorAND implements ComponenteBusqueda {
	List<ComponenteBusqueda> ramas;
	String nombre;
	
	public OperadorAND(List<ComponenteBusqueda> ramas, String nombre) {
		this.ramas = ramas;
		this.nombre = nombre;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> listaViajes) {
		List<Viaje> resultado = listaViajes;
		
		for(ComponenteBusqueda comp : ramas) {
			List<Viaje> conjuntoViajes = comp.filtrar(listaViajes);
			resultado.retainAll(conjuntoViajes);
		}
		
		return resultado;
	}

}
