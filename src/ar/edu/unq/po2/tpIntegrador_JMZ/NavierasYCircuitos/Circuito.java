package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Circuito {
	private ArrayList<Tramo> tramos;
	
	// Constructor
	public Circuito(ArrayList<Tramo> tramos) {
		this.tramos = ordenarTramos(tramos);
	}
	
	// Getter
	public ArrayList<Tramo> getTramos() {
		return this.tramos;
	}
	
	// Metodos propios
	private ArrayList<Tramo> ordenarTramos(ArrayList<Tramo> listaTramos) {
		// Dada una lista de tramos, reordena sus elementos para que el circuito quede en orden y devuelve la lista ordenada.
		if (listaTramos.isEmpty()) return new ArrayList<>();
		
		HashMap<String, Tramo> mapaOrigen = new HashMap<>();
        for (Tramo t : listaTramos) {
            mapaOrigen.put(t.getPuertoOrigen(), t);
        }
		
		Tramo tramoActual = listaTramos.get(0);
		final String origenInicial = tramoActual.getPuertoOrigen();
		
		ArrayList<Tramo> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(tramoActual);
		
		while (listaOrdenada.size() < listaTramos.size()) {
			String destinoActual = tramoActual.getPuertoDestino();
			Tramo siguienteTramo = mapaOrigen.get(destinoActual);
			listaOrdenada.add(siguienteTramo);
			tramoActual = siguienteTramo;
		}
		
		this.validarCircuitoCerrado(tramoActual.getPuertoDestino(), origenInicial);
		
		return listaOrdenada;
	}
	
	private void validarCircuitoCerrado(String destino, String origen) {
		// (Pensado para el metodo "ordenarTramos".)
		// Valida que el ultimo puerto conecta con el inicial.
		if(!(destino == origen)) {
			throw new IllegalArgumentException("Los tramos no forman un circuito cerrado.");
		}
	}
	
	public ArrayList<String> localidadesQueAbarca() {
		// Devuelve un conjunto con todas las terminales portuarias que forman parte del circuito, en orden y sin duplicados.
		LinkedHashSet<String> conjuntoPuertos = new LinkedHashSet<String>();
		
		for(Tramo tramoActual : tramos) {
			conjuntoPuertos.add(tramoActual.getPuertoOrigen());
			conjuntoPuertos.add(tramoActual.getPuertoDestino());
		}
		
		return new ArrayList<>(conjuntoPuertos);
	}
}
