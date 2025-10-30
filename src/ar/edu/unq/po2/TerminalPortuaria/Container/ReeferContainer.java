package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

public class ReeferContainer extends Container {

	private double consumoElectricidad;
	
	public ReeferContainer(double ancho, double largo, double altura, String identificador, IBillOfLanding bl, double consumoElectricidad)
			throws Exception {
		super(ancho, largo, altura, identificador, bl);
		this.consumoElectricidad = consumoElectricidad;
	}

	@Override
	public List<Cliente> getDuenio() {
		// TODO Auto-generated method stub
		return this.bl.getDuenios();
	}

	@Override
	public List<BillOfLanding> getBillsOfLanding() {
		// TODO Auto-generated method stub
		return this.bl.getBillOfLandings();
	}

	@Override
	public double getPeso() {
		return this.bl.getPeso();
	}

	@Override
	public void agregarBillOfLanding(BillOfLanding bl) throws Exception {
		throw new Exception("No se puede agregar otro Bill of Landing a este contenedor");
	}

	@Override
	public double consumoElectricidad() {
		// TODO Auto-generated method stub
		return this.consumoElectricidad;
	}

}
