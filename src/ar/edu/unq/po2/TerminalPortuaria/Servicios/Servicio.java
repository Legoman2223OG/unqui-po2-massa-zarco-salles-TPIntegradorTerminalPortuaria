package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;

public abstract class Servicio {
	protected Container container;
	protected double precioFijo;

	/**
	 * Crea un servicio con su respectivo container y un precio fijo.
	 * @param container, Container, el contenedor que revisara el servicio.
	 * @param precioFijo, double, Un precio fijo definido por una terminal. Debe ser >= 0.
	 * @throws Exception 
	 */
	public Servicio(Container container, double precioFijo) throws Exception {
		asertarNumeroPositivo(precioFijo);
		this.container = container;
		this.precioFijo = precioFijo;
	}

	/**
	 * Aserta que el numero indicado sea positivo o 0.
	 * @param precioFijo2, double, un numero ingresado
	 * @throws Exception, si el numero es negativo.
	 */
	private void asertarNumeroPositivo(double precio) throws Exception {
		if(precio < 0)
			throw new Exception("El numero ingresado no es positivo");
	}

	/**
	 * Crea un servicio con su respectivo container.
	 * @param container, Container, el contenedor que revisara el servicio. No puede ser nulo.
	 */
	public Servicio(Container container) {
		this.container = container;
	}
	
	public Container getContainer() {
		return this.container;
	}

	/**
	 * Calcula el precio según como lo define el servicio.
	 * @return double, El precio calculado.
	 * @throws Exception
	 */
	public abstract double calcularPrecio() throws Exception;

	/**
	 * Describe el BillOfLanding perteneciente al cliente indicado (Solo para Desconsolidado).
	 * @param cl, Cliente, el Cliente especificado, no puede ser nulo.
	 * @return BillOfLanding, el BL del cliente especificado.
	 * @throws Exception
	 */
	public abstract BillOfLanding billOfLandingDelCliente(Cliente cl) throws Exception;

	/**
	 * Afirma que el container sufre de perdidas de gas o liquido (Solo para RevisionPerdidas).
	 * @return boolean, true, indicando que hay una perdida en el container.
	 * @throws Exception
	 */
	public abstract boolean contienePerdidas() throws Exception;
}
