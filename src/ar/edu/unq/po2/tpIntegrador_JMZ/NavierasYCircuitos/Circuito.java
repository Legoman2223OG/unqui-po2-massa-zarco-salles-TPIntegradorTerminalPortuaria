package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.util.ArrayList;
import java.util.HashSet;

public class Circuito {
	private ArrayList<Tramo> tramos;
	
	// Constructor
	public Circuito(ArrayList<Tramo> tramos) {
		this.tramos = tramos;
	}
	
	// Metodos propios
	public HashSet<TerminalPortuaria> puertosQueFormanParte() {
		// Devuelve un conjunto con todas las terminales portuarias que forman parte del circuito.
		HashSet<TerminalPortuaria> conjuntoPuertos = new HashSet<TerminalPortuaria>();
		
		for(Tramo tramoActual : tramos) {
			conjuntoPuertos.add(tramoActual.getPuertoOrigen());
			conjuntoPuertos.add(tramoActual.getPuertoDestino());
		}
		
		return conjuntoPuertos;
	}
}
