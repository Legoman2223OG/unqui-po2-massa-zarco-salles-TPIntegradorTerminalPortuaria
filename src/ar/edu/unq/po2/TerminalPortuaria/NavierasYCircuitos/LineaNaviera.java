package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

import java.util.List;

public class LineaNaviera {
	private List<Buque> buques;
	private List<Circuito> circuitos;
	
	// Constructor
	public LineaNaviera(List<Buque> buques, List<Circuito> circuitos) {
		this.buques = buques;
		this.circuitos = circuitos;
	}

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
