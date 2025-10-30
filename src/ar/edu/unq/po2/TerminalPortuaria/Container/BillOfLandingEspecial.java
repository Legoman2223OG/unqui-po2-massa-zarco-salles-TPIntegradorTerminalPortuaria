package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

/**
 * Describe un Bill Of Landing Especial, el cual contiene Bills de varios importadores.
 */
public class BillOfLandingEspecial extends BillOfLanding {
	
	private List<BillOfLanding> bls = new ArrayList<BillOfLanding>();
	
	/**
	 * Describe un Bill Of Landing especial el cual incluye varios bill of landings de diferentes propietarios.
	 * @param billOfLandings
	 */
	public BillOfLandingEspecial(BillOfLanding ...billOfLandings) {
		this.bls.addAll(Arrays.asList(billOfLandings));
	}
	
	@Override
	public double peso() {
		// TODO Auto-generated method stub
		return this.bls.stream().mapToDouble(bl -> bl.peso()).sum();
	}

	@Override
	public List<Cliente> duenios() throws Exception {
		return bls.stream().map(bl -> {
			try {
				return bl.duenio();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	@Override
	public Cliente duenio() throws Exception {
		throw new Exception("No es un Bill Of Landing Normal...");
	}

	@Override
	public List<Producto> productos() {
		// TODO Auto-generated method stub
		return this.bls.stream().flatMap(bl -> bl.productos().stream()).collect(Collectors.toList());
	}

	@Override
	public void agregarBL(BillOfLanding bl) throws Exception {
		this.bls.add(bl);
	}

}
