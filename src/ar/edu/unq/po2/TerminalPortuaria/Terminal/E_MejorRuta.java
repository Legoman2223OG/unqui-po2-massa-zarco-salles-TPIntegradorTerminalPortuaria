package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;

public abstract class E_MejorRuta {
	private TerminalPortuaria terminalOrigen;
	
	public E_MejorRuta(TerminalPortuaria terminalOrigen) {
		this.terminalOrigen = terminalOrigen;
	}
	
	public abstract Circuito mejorCircuitoHacia(TerminalPortuaria terminalDestino);
	
	protected ArrayList<Circuito> circuitosQueContienenDestino(TerminalPortuaria terminalDestino) {
		return (ArrayList<Circuito>) this.terminalOrigen.getMisNavieras().stream()
												   .flatMap(nav -> nav.circuitosQuePasanPor(terminalDestino).stream())
												   .toList();
	}
	
	protected HashMap<Viaje, Circuito> viajesPorCadaCircuito(TerminalPortuaria terminalDestino) {
		HashMap<Viaje, Circuito> mapCircuitoConViaje = new HashMap<Viaje, Circuito>();
		
		for (Circuito circ : this.circuitosQueContienenDestino(terminalDestino)) {
			mapCircuitoConViaje.put(circ.crearNuevoViaje(terminalOrigen,terminalDestino,LocalDateTime.now()), circ);
		}
		
		return mapCircuitoConViaje;
	}
	
	protected Set<Viaje> viajesDesdeOrigenHasta(TerminalPortuaria terminalDestino) {
		return viajesPorCadaCircuito(terminalDestino).keySet();
	}
}
