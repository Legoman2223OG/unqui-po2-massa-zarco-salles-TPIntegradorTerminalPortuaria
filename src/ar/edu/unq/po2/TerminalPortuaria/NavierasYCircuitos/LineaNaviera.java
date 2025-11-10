package ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

import java.util.ArrayList;
import java.util.List;

public class LineaNaviera {
	private List<Buque> buques = new ArrayList<>();
	private List<Circuito> circuitos = new ArrayList<>();
	private List<WrapperViaje> listaRecorridos = new ArrayList<>();
	
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
	
	// Metodos propios
	public List<Circuito> circuitosQuePasanPor(TerminalPortuaria terminal) {
		return this.circuitos.stream()
							 .filter(circ -> circ.terminalExisteEnElCircuito(terminal))
							 .toList();
	}
	
	public List<Viaje> getViajes() {
		return this.listaRecorridos.stream()
								   .flatMap(r -> r.getListaViajes().stream())
								   .toList();
	}
	
	public void registrarNuevoRecorrido(Buque buque, ArrayList<Viaje> listaViajes) throws Exception {
		this.verificarExistenciaDeBuque(buque);
		this.listaRecorridos.add(new WrapperViaje(buque, listaViajes));
	}
	
	public void verificarExistenciaDeBuque(Buque buque) throws Exception {
		if(!this.buqueExisteEnEstaNaviera(buque)) {
			throw new Exception("Este buque no trabaja para esta linea naviera.");
		}
	}
	
	public boolean buqueExisteEnEstaNaviera(Buque buque) {
		return this.buques.contains(buque);
	}
}
