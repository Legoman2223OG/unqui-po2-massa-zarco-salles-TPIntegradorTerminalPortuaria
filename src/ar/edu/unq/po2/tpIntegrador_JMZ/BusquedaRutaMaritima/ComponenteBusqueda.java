package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.util.List;
import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public interface ComponenteBusqueda {
	public List<Viaje> filtrar (List<Viaje> listaViajes);
}
