package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import java.util.ArrayList;

public class LineaNaviera {
	private ArrayList<Buque> buques;
	private ArrayList<Circuito> circuitos;
	
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
