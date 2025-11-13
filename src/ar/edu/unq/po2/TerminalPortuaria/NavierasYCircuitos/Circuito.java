package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class Circuito {
	private ArrayList<Tramo> tramos;
	
	// Constructor
	public Circuito(ArrayList<Tramo> tramos) throws Exception {
		this.tramos = ordenarTramos(tramos);
	}

	// Getter
	public ArrayList<Tramo> getTramos() {
		return this.tramos;
	}
	
	// Metodos propios
	private ArrayList<Tramo> ordenarTramos(ArrayList<Tramo> listaTramos) throws Exception {
		// Dada una lista de tramos, reordena sus elementos para que el circuito quede en orden y devuelve la lista ordenada.
		if (listaTramos.isEmpty()) return new ArrayList<>();
		
		HashMap<TerminalPortuaria, Tramo> mapaOrigen = new HashMap<>();
        for (Tramo t : listaTramos) {
            mapaOrigen.put(t.getPuertoOrigen(), t);
        }
		
		Tramo tramoActual = listaTramos.get(0);
		final TerminalPortuaria origenInicial = tramoActual.getPuertoOrigen();
		
		ArrayList<Tramo> listaOrdenada = new ArrayList<>();
		listaOrdenada.add(tramoActual);
		
		while (listaOrdenada.size() < listaTramos.size()) {
			TerminalPortuaria destinoActual = tramoActual.getPuertoDestino();
			Tramo siguienteTramo = mapaOrigen.get(destinoActual);
			listaOrdenada.add(siguienteTramo);
			tramoActual = siguienteTramo;
		}
		
		this.validarCircuitoCerrado(tramoActual.getPuertoDestino(), origenInicial);
		
		return listaOrdenada;
	}
	
	public Viaje crearNuevoViaje(TerminalPortuaria puertoOrigen, TerminalPortuaria puertoDestino, LocalDateTime fechaSalida) {
		return new Viaje(puertoOrigen,this.recorridoEntre_Y_(puertoOrigen, puertoDestino),fechaSalida);
	}
	
	public ArrayList<Tramo> recorridoEntre_Y_(TerminalPortuaria puertoOrigen, TerminalPortuaria puertoDestino) {
		ArrayList<Tramo> recorrido = new ArrayList<>();
		TerminalPortuaria puertoActual = puertoOrigen;
		
		while(puertoActual != puertoDestino) {
			Tramo siguiente = tramoQueIniciaEn(puertoActual);
			
			recorrido.add(siguiente);
			puertoActual = siguiente.getPuertoDestino();
		}
		
		return recorrido;
	}
	
	private Tramo tramoQueIniciaEn(TerminalPortuaria localidadPuerto) {
		// (Ideado para "tramosEntre_Y_")
		// PREC.: debe existir algún puerto cuya localidad de origen sea "localidadPuerto".
		return this.getTramos().stream().filter(t -> t.iniciaEnPuertoDado(localidadPuerto)).findFirst().get();
	}
	
	private void validarCircuitoCerrado(TerminalPortuaria destino, TerminalPortuaria origen) throws Exception {
		// (Pensado para el metodo "ordenarTramos".)
		// Valida que el ultimo puerto conecta con el inicial.
		if(!(destino == origen)) {
			throw new Exception("Los tramos no forman un circuito cerrado.");
		}
	}
	
	public ArrayList<TerminalPortuaria> localidadesQueAbarca() {
		// Devuelve un conjunto con todas las terminales portuarias que forman parte del circuito, en orden y sin duplicados.
		LinkedHashSet<TerminalPortuaria> conjuntoPuertos = new LinkedHashSet<TerminalPortuaria>();
		
		for(Tramo tramoActual : tramos) {
			conjuntoPuertos.add(tramoActual.getPuertoOrigen());
			conjuntoPuertos.add(tramoActual.getPuertoDestino());
		}
		
		return new ArrayList<>(conjuntoPuertos);
	}
	
	public boolean terminalExisteEnElCircuito(TerminalPortuaria terminal) {
		return this.localidadesQueAbarca().contains(terminal);
	}
}
