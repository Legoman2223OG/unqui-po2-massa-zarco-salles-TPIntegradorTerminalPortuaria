package ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;

public interface Busqueda {

	abstract public List<Viaje> filtrar(List<Viaje> listaDeViajes);
	abstract public void agregar(Busqueda componente);
	abstract public void remover(Busqueda componente);
}
