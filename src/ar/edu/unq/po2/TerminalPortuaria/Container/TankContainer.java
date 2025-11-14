package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;


public class TankContainer extends Container {

	public TankContainer(double ancho, double largo, double altura, String identificador, BillOfLanding bl)
			throws Exception {
		super(ancho, largo, altura, identificador, bl);
	}

	@Override
	public List<Cliente> getDuenio() {
		return this.bl.getDuenios();
	}

	@Override
	public List<BillOfLanding> getBillsOfLanding() {
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
	public double consumoElectricidad() throws Exception {
		throw new Exception("Este contenedor no requiere de conexion a una fuente de electricidad");
	}

}
