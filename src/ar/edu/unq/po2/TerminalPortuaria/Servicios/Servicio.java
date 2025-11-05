package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.Cliente;

public abstract class Servicio {
	protected Container container;
	protected double precioFijo;
	
	/**
	 * Crea un servicio con su respectivo container y un precio fijo.
	 * @param container, Container, el contenedor que revisara el servicio.
	 * @param precioFijo, double, Un precio fijo definido por una terminal. Debe ser >= 0.
	 */
	public Servicio(Container container, double precioFijo) {
		this.container = container;
		this.precioFijo = precioFijo;
	}
	
	/**
	 * Crea un servicio con su respectivo container.
	 * @param container, Container, el contenedor que revisara el servicio. No puede ser nulo.
	 */
	public Servicio(Container container) {
		this.container = container;
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
