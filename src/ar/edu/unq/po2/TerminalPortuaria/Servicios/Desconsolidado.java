package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;

public class Desconsolidado extends Servicio {

	/**
	 * Ingresa un servicio de desconsolidado para poder obtener los items necesarios de un importador en particular
	 * cuando se trata de un dry container compuesto.
	 * @param container, DryContainer, un DryContainer compuesto que necesita este servicio, no puede ser nulo.
	 */
	public Desconsolidado(DryContainer container) {
		super(container);
	}

	@Override
	public double calcularPrecio() throws Exception {
		throw new Exception("Este servicio no se encarga de calcular un precio");
	}
	
	/**
	 * Obtiene el bill of landing del cliente especificado.
	 * @param cl, Cliente, el Cliente especificado, no puede ser nulo.
	 * @return BillOfLanding, el BL del cliente especificado.
	 */
	public BillOfLanding billOfLandingDelCliente(Cliente cl) {
		return (BillOfLanding) this.container.getBillsOfLanding().stream().filter(bl -> bl.getDuenios().stream().anyMatch(duenio -> duenio == cl)).findFirst().orElse(null);
	}

	@Override
	public boolean contienePerdidas() throws Exception {
		throw new Exception("El servicio no se encarga de esta operacion");
	}

}
