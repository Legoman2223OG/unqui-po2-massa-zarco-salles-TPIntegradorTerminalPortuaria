package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

import java.util.ArrayList;
import java.util.List;

public class LineaNaviera {
	private List<Buque> buques = new ArrayList<>();
	private List<Circuito> circuitos = new ArrayList<>();
	
	// Constructor
	public LineaNaviera() {}

	// Getters
	public List<Buque> getBuques() {
		return buques;
	}
	
	public void addBuque(Buque buque) {
		buques.add(buque);
	}
	
	public void addCircuito(Circuito circuito) {
		circuitos.add(circuito);
	}

	public List<Circuito> getCircuitos() {
		return circuitos;
	}
	
	public List<Circuito> circuitosQuePasanPor(TerminalPortuaria terminal) {
		return this.circuitos.stream()
							 			 .filter(circ -> circ.terminalExisteEnElCircuito(terminal))
							 			 .toList();
	}
}
