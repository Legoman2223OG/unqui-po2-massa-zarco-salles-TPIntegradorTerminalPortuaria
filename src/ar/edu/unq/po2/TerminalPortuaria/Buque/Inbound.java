package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Estado donde el buque se encuentra menor a 50km de distancia de la terminal.
 * Si llega a suceder algo que impida acercarse y sube o se queda a los 50km vuelve a Outbound.
 */
public class Inbound extends BuqueStatus {

	/**
	 * Si la distancia es 0 (Esta dentro de la terminal), entonces pasa a Arrived.
	 * En caso contrario de alejarse mas o igual a 50 kms, vuelve a Outbound.
	 */
	@Override
	public void actualizarEstado(double distancia, Buque buque) {
		if(distancia == 0) {
			buque.setStatus(new Arrived());
		} else if(distancia >= 50) {
			buque.setStatus(new Outbound());
		}
	}

	@Override
	public void working(Buque buque) throws Exception {
		throw new Exception("No se puede pasar a este estado actualmente");
	}

	@Override
	public void depart(Buque buque) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("No se puede partir en este estado");
	}

	@Override
	public void moverA(Coordenada coordenada, Buque buque) throws Exception {
		buque.getGestorDeCoordenadas().setCoordenadas(coordenada, buque.getDestino().getCoordenadas());
	}
}
