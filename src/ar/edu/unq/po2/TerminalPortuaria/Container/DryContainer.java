package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

public class DryContainer extends Container {

	private IBillOfLanding bls;
	
	/**
	 * Crea un Dry Container con su ancho, largo, altura, identificador 
	 * @param ancho, double, el ancho del contenedor, debe ser > 0.
	 * @param largo, double, el largo del contenedor, debe ser > 0.
	 * @param altura, double, la altura del contenedor, debe ser > 0.
	 * @param contenido, BillOfLanding, el contenido del contenedor.
	 * @throws Exception.
	 */
	public DryContainer(double ancho, double largo, double altura, BillOfLanding contenido) throws Exception {
		super(ancho, largo, altura, crearIdentificadorAleatorio(), new BillOfLandingEspecial());
		
	}

	private static String crearIdentificadorAleatorio() {
		String identificador = "DRYC";
		for(int i = 0; i < 7; i++) {
			identificador +=  Integer.toString((int) (Math.random()));
		}
		return identificador;
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
	
	public void agregarBillOfLanding(BillOfLanding bl) throws Exception {
		this.bl.agregarBillOfLanding(bl);
	}

	@Override
	public double consumoElectricidad() throws Exception {
		throw new Exception("Este contenedor no requiere de conexion a una fuente de electricidad");
	}

}
