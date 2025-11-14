package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;

public class ReeferContainer extends Container {

	private double consumoElectricidad;

	public ReeferContainer(double ancho, double largo, double altura, String identificador, BillOfLanding bl, double consumoElectricidad)
			throws Exception {
		super(ancho, largo, altura, identificador, bl);
		asertarNumeroPositivoElectricidadConsumida(consumoElectricidad);
		this.consumoElectricidad = consumoElectricidad;
	}

	/**
	 * Aserta que el numero ingresado para el consumo de electricidad sea mayor a 0.
	 * @param consumoElectricidad2, double, el numero de consumo de electricidad.
	 * @throws Exception, si es que el numero es 0 o negativo.
	 */
	private void asertarNumeroPositivoElectricidadConsumida(double consumoElectricidad) throws Exception {
		if(consumoElectricidad <= 0)
			throw new Exception("No se puede ingresar un numero negativo para consumo electrico");
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
