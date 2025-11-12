package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public abstract class E_MejorRuta {
	public E_MejorRuta() {}
	
	public Circuito mejorCircuitoHacia(TerminalPortuaria terminalOrigen, TerminalPortuaria terminalDestino) {
		final HashMap<Viaje,Circuito> mapa = viajesPorCadaCircuito(terminalOrigen, terminalDestino);

	    var viajeOptimo = this.criterioDeSeleccion(mapa.keySet());

	    return mapa.get(viajeOptimo);
	}
	
	protected abstract Viaje criterioDeSeleccion(Set<Viaje> mapa);
	
	protected List<Circuito> circuitosQueContienen(TerminalPortuaria terminalOrigen, TerminalPortuaria terminalDestino) {
		return terminalOrigen.getMisNavieras().stream()
											  .flatMap(nav -> nav.circuitosQuePasanPor(terminalDestino).stream())
											  .toList();
	}
	
	protected HashMap<Viaje, Circuito> viajesPorCadaCircuito(TerminalPortuaria terminalOrigen, TerminalPortuaria terminalDestino) {
		HashMap<Viaje, Circuito> mapCircuitoConViaje = new HashMap<Viaje, Circuito>();
		
		for (Circuito circ : this.circuitosQueContienen(terminalOrigen, terminalDestino)) {
			mapCircuitoConViaje.put(circ.crearNuevoViaje(terminalOrigen,terminalDestino,LocalDateTime.now()), circ);
		}
		
		return mapCircuitoConViaje;
	}
}
