package ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista;


public class TransporteAsignado {

	protected Chofer choferAsignado;
	protected Camion camionAsignado;


	public TransporteAsignado(Chofer choferAsignado, Camion camionAsignado) {
		this.choferAsignado = choferAsignado;
		this.camionAsignado = camionAsignado;
	}


	public Chofer getChoferAsignado() {
		return this.choferAsignado;

	}

	public Camion getCamionAsignado() {
		return this.camionAsignado;

	}

}
