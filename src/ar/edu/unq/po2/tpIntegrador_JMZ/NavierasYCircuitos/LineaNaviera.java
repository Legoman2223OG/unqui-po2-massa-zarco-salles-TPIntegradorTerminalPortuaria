package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

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
}
