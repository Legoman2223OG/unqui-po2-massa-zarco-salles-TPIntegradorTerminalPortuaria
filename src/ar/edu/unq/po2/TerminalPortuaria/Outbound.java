package ar.edu.unq.po2.TerminalPortuaria;

/**
 * Clase que representa el Estado Outbound del buque.
 * Este estado es el inicial y solo se presenta cuando el buque esta a mayor o igual a 50KM de la terminal de destino
 */
public class Outbound extends BuqueStatus {

	/**
	 * Si la distancia es menor a 50km, pasa a inbound y avisa a la terminal de destino.
	 */
	@Override
	public void actualizarEstado(double distancia, Buque buque) {
		if(distancia > 50) {
			buque.setStatus(new Inbound());
			buque.getDestino().proximoAArribar(buque.getViaje());
		}
	}

	@Override
	public void working(Buque buque) throws Exception {
		throw new Exception("Aun no se encuentra operable en este estado");
	}

	@Override
	public void depart(Buque buque) throws Exception {
		throw new Exception("Aun no se encuentra operable en este estado");
	}

	@Override
	public void moverA(Coordenada coordenada, Buque buque) throws Exception {
		buque.setCoordenadas(coordenada);
	}
}
