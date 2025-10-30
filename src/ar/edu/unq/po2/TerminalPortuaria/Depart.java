package ar.edu.unq.po2.TerminalPortuaria;

/**
 * Un Estado que representa el estado de partida del buque. Una vez que se aleja de 1km de la terminal, envia un mensaje
 * a esta y vuelve a estar en Outbound.
 */
public class Depart extends BuqueStatus {

	@Override
	public void actualizarEstado(double distancia, Buque buque) {
		if(distancia > 1) {
			buque.setStatus(new Outbound());
			buque.getDestino().partiendoAViaje(buque.getViaje());
		}
	}

	@Override
	public void working(Buque buque) throws Exception {
		throw new Exception("No se puede cambiar a este estado");
	}

	/**
	 * Ya esta en este estado...
	 */
	@Override
	public void depart(Buque buque) throws Exception {
	}

	@Override
	public void moverA(Coordenada coordenada, Buque buque) throws Exception {
		buque.setCoordenadas(coordenada);
	}

}
