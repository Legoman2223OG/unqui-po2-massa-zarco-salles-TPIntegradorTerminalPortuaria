package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.HashSet;
import java.util.List;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.Viaje;

public class OperadorOR implements ComponenteBusqueda {
	List<ComponenteBusqueda> ramas;
	String nombre;
	
	public OperadorOR(List<ComponenteBusqueda> ramas, String nombre) {
		this.ramas = ramas;
		this.nombre = nombre;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Viaje> filtrar(List<Viaje> listaViajes) {
		HashSet<Viaje> resultado = new HashSet<>();
		
		for(ComponenteBusqueda comp : ramas) {
			HashSet<Viaje> conjuntoViajes = (HashSet<Viaje>) comp.filtrar(listaViajes);
			resultado.addAll(conjuntoViajes);
		}
		
		return (List<Viaje>) resultado;
	}

}
