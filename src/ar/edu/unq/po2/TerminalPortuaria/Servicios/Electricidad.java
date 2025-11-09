package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;


public class Electricidad extends Servicio {

	/**
	 * Ingresa un servicio de Electricidad, en donde contiene un Reefer y un precio fijo establecido por la terminal por cada Kilowatt/Hora consumido.
	 * @param container, ReeferContainer, Reefer al que esta vinculado con este servicio, no puede ser nulo.
	 * @param precioFijo, double, un precio fijo de consumo de electricidad fijado por la terminal. Debe ser >= 0.
	 */
	public Electricidad(ReeferContainer container, double precioFijo) {
		super(container, precioFijo);
	}

	@Override
	public double calcularPrecio() throws Exception {
		return container.consumoElectricidad() * precioFijo;
	}

	@Override
	public BillOfLanding billOfLandingDelCliente(Cliente cl) throws Exception {
		throw new Exception("El servicio no se encarga de esta operacion");
	}

	@Override
	public boolean contienePerdidas() throws Exception {
		throw new Exception("El servicio no se encarga de esta operacion");
	}

}
