package ar.edu.unq.po2.tpIntegrador_JMZ.BusquedaRutaMaritima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos.*;

public class FiltroFechaLlegada implements ComponenteBusqueda {
	LocalDateTime fechaLlegada;
	
	public FiltroFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	@Override
	public ArrayList<Viaje> filtrar(ArrayList<Viaje> listaViajes) {
		// TODO Auto-generated method stub
		return null;
	}

}
