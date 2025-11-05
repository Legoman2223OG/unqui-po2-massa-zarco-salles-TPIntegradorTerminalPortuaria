package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Viaje;



public interface Busqueda {

	abstract public List<Viaje> filtrar(List<Viaje> listaDeViajes);
}
