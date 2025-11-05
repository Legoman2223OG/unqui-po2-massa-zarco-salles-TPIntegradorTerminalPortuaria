package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;

public class AlmacenamientoExcediente extends Servicio {

	/**
	 * Ingresa un servicio de almacenamiento excediente, en donde tiene un container vinculado y un costo fijo por la tardanza.
	 * @param container, Container, Container al que esta vinculado con este servicio, no puede ser nulo.
	 * @param precioFijo, double, un precio fijo de almacenamiento excediente asignado por la terminal. Debe ser >= 0.
	 */
	public AlmacenamientoExcediente(Container container, double precioFijo) {
		super(container, precioFijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecio() throws Exception {
		// TODO Auto-generated method stub
		return this.precioFijo;
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
