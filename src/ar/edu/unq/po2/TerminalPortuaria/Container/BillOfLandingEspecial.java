package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;


/**
 * Representa un Bill Of Landing especial, el cual puede contener
 * multiples bill of landings de diferentes clientes.
 */
public class BillOfLandingEspecial implements IBillOfLanding{
	private List<Cliente> duenios;
	private List<BillOfLanding> bls;

	/**
	 * Crea un Bill Of Landing Especial a partir de una lista de bls
	 * @param bls, BillOfLanding[], una lista de Bill of Landings, no pueden ser nulos.
	 */
	public BillOfLandingEspecial(BillOfLanding...bls) {
		//Lo convertimos a List para hacer operaciones con stream.
		List<BillOfLanding> blsAsList = Arrays.asList(bls);
		this.duenios = blsAsList.stream().flatMap(bl -> bl.getDuenios().stream()).collect(Collectors.toList());
		this.bls = new ArrayList<>(blsAsList);
	}

	@Override
	public List<Cliente> getDuenios() {
		return this.duenios;
	}

	@Override
	public List<Producto> getProductos() {
		return bls.stream().flatMap(bl -> bl.getProductos().stream()).collect(Collectors.toList());
	}

	@Override
	public double getPeso() {
		return this.bls.stream().mapToDouble(bl -> bl.getPeso()).sum();
	}

	@Override
	public List<BillOfLanding> getBillOfLandings() {
		// TODO Auto-generated method stub
		return this.bls;
	}

	@Override
	public void agregarBillOfLanding(BillOfLanding bl) throws Exception {
		this.bls.add(bl);
		this.duenios.add(bl.getDuenios().getFirst());
	}
}
