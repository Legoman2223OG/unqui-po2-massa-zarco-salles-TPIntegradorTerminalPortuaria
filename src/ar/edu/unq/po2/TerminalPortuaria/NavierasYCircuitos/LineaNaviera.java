package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LineaNaviera {
	private ArrayList<Buque> buques;
	private ArrayList<Circuito> circuitos;
	private Map<Buque, ArrayList<Viaje>> recorridos = new HashMap<>();
	
	// Constructor
	public LineaNaviera(ArrayList<Buque> buques, ArrayList<Circuito> circuitos) {
		this.buques = buques;
		this.circuitos = circuitos;
	}

	// Getters
	public ArrayList<Buque> getBuques() {
		return buques;
	}

	public ArrayList<Circuito> getCircuitos() {
		return circuitos;
	}
	
	public ArrayList<Circuito> circuitosQuePasanPor(TerminalPortuaria terminal) {
		return (ArrayList<Circuito>) this.circuitos.stream()
							 			 .filter(circ -> circ.terminalExisteEnElCircuito(terminal))
							 			 .toList();
	}
}
