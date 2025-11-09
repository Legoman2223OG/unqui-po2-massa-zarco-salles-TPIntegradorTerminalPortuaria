package ar.edu.unq.po2.TerminalPortuaria.Servicios;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;


public class Lavado extends Servicio {

	public Lavado(Container container) {
		super(container);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecio() {
		if(this.container.getCapacidad() > 70)
			return 2000.0;
		else
			return 1000.0;
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
