package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.ArrayList;
import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public interface ComponenteBusqueda {
	public ArrayList<Viaje> filtrar(ArrayList<Viaje> listaViajes);
}
