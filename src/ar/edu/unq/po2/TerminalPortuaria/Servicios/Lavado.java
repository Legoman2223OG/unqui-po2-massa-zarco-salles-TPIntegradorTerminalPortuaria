package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;


public class Lavado extends Servicio {
	private double precioMenor;
	private double precioMayor;
	/**
	 * Aplica un servicio de lavado con un precio mayor y un precio menor en base a la capacidad del container.
	 * @param container, Container, el container aplicado al servicio, no puede ser null.
	 * @param precioMayor, double, el precio que se le cobrara si la capacidad supera los 70kg.
	 * @param precioMenor, double, el precio menor que se le cobrara si la capacidad es menor a los 70kg.
	 * @throws Exception 
	 */
	public Lavado(Container container, double precioMayor, double precioMenor) throws Exception {
		super(container);
		asertarNumeroPositivoPrecio(precioMayor);
		asertarNumeroPositivoPrecio(precioMenor);
		this.precioMayor = precioMayor;
		this.precioMenor = precioMenor;
	}

	/**
	 * Aserta que el precio ingresado sea positivo o 0.
	 * @param precioMenor2, double, el precio ingresado.
	 * @throws Exception, si el precio es negativo.
	 */
	private void asertarNumeroPositivoPrecio(double precio) throws Exception {
		if(precio < 0)
			throw new Exception("No se puede ingresar un precio negativo");
	}

	@Override
	public double calcularPrecio() {
		if(this.container.getCapacidad() > 70) {
			return this.precioMayor;
		} else {
			return this.precioMenor;
		}
	}

	@Override
	public BillOfLanding billOfLandingDelCliente(Cliente cl) throws Exception {
		throw new Exception("Este servicio no se encarga de esta operacion");
	}

	@Override
	public boolean contienePerdidas() throws Exception {
		throw new Exception("Este servicio no se encarga de esta operacion");
	}

}
