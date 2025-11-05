package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Estado en donde el buque se encuentra listo para descargar y cargar contenedores. Una vez terminado
 * debera pasar a Depart.
 */
public class Working extends BuqueStatus {

	/**
	 * No hace nada, ya que no podria moverse por default en este estado.
	 */
	@Override
	public void actualizarEstado(double distancia, Buque buque) {
		
	}

	/**
	 * Ya se esta trabajando sobre este estado...
	 */
	@Override
	public void working(Buque buque) throws Exception {
	}

	/**
	 * Una vez terminada la carga o descarga, pasa a Depart.
	 */
	@Override
	public void depart(Buque buque) throws Exception {
		buque.setStatus(new Depart());
	}

	@Override
	public void moverA(Coordenada coordenada, Buque buque) throws Exception {
		throw new Exception("No se puede mover en este estado");
	}

}
