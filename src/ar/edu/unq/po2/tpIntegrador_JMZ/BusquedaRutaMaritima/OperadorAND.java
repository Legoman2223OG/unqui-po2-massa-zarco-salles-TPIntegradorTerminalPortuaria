package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.ArrayList;

import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.Viaje;

public class OperadorAND implements ComponenteBusqueda {
	ArrayList<ComponenteBusqueda> ramas;
	String nombre;
	
	public OperadorAND(ArrayList<ComponenteBusqueda> ramas, String nombre) {
		this.ramas = ramas;
		this.nombre = nombre;
	}

	@Override
	public ArrayList<Viaje> filtrar(ArrayList<Viaje> listaViajes) {
		// TODO Auto-generated method stub
		return null;
	}

}
