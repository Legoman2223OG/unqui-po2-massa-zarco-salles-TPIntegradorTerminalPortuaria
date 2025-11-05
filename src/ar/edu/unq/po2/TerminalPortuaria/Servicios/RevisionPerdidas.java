package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.Cliente;

public class RevisionPerdidas extends Servicio {

	/**
	 * Ingresa un servicio encargado de verificar las perdidas de un Tank Container.
	 * @param container, TankContainer, el Tank container asignado para este servicio. No puede ser nulo.
	 */
	public RevisionPerdidas(TankContainer container) {
		super(container);
	}

	@Override
	public double calcularPrecio() throws Exception {
		throw new Exception("Este servicio no se encarga de calcular un precio");
	}

	@Override
	public BillOfLanding billOfLandingDelCliente(Cliente cl) throws Exception {
		throw new Exception("Este servicio no se encarga de esta operacion");
	}

	@Override
	public boolean contienePerdidas() {
		return true;
	}

}
