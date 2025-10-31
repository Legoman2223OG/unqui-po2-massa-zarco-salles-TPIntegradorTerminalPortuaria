package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Estado donde indica que el barco ya se encuentra situado en la terminal.
 * Una vez que esta en este estado, puede iniciar la carga y descarga de containers en el estado Working cuando
 * este listo.
 */
public class Arrived extends BuqueStatus {

	/**
	 * No hace nada, ya que no podria moverse por default en este estado.
	 */
	@Override
	public void actualizarEstado(double distancia, Buque buque) {
	}

	@Override
	public void working(Buque buque) throws Exception {
		buque.setStatus(new Working());
	}

	@Override
	public void depart(Buque buque) throws Exception {
		throw new Exception("No se puede partir en este estado");
	}

	@Override
	public void moverA(Coordenada coordenada, Buque buque) throws Exception {
		throw new Exception("No se puede mover en este estado");
	}
}
