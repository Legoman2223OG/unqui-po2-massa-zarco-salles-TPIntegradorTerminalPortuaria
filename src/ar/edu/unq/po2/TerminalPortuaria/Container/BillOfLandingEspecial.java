package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;


/**
 * Representa un Bill Of Landing especial, el cual puede contener
 * multiples bill of landings de diferentes importadores.
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
		this.duenios = (List<Cliente>) blsAsList.stream().map(BillOfLanding::getDuenios);
		this.bls = new ArrayList<BillOfLanding>(blsAsList);
	}
	
	public List<Cliente> getDuenios() {
		return this.duenios;
	}

	public List<Producto> getProductos() {
		return bls.stream().flatMap(bl -> bl.getProductos().stream()).collect(Collectors.toList());
	}
	
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
	}
}
