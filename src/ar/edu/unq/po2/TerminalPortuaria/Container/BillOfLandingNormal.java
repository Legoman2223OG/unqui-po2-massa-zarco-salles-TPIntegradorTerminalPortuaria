package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

/**
 * Describe un Bill of Landing perteneciente a un importador.
 */
public class BillOfLandingNormal extends BillOfLanding {
	
	private List<Producto> productos;
	private Cliente duenio;
	
	public BillOfLandingNormal(Cliente duenio, List<Producto> productos) {
		this.duenio = duenio;
		productos = productos;
	}
	
	@Override
	public double peso() {
		// TODO Auto-generated method stub
		return productos.stream().mapToDouble(p -> p.getPeso()).sum();
	}

	@Override
	public List<Cliente> duenios() throws Exception {
		throw new Exception("No es un Bill Of Landing Especial...");
	}

	@Override
	public Cliente duenio() throws Exception {
		return this.duenio;
	}

	@Override
	public List<Producto> productos() {
		return this.productos;
	}

	@Override
	public void agregarBL(BillOfLanding bl) throws Exception {
		throw new Exception("No es un Bill Of Landing Especial...");
	}

}
