package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Una clase que representa un GPS de un buque, el cual permite hacer calculos de coordenadas y actualizar la información
 * del buque.
 */
public class GPS {
	private Buque buque;
	private Coordenada coordenada;

	/**
	 * Crea un GPS vinculado a un buque junto a la coordenada del buque segun la posición en el plano.
	 * @param coordenada, Coordenada, la Coordenada de ubicación del buque, no puede ser nula.
	 * @param buque, Buque, El buque al cual esta vinculado el GPS, no puede ser nulo.
	 */
	public GPS(Coordenada coordenada, Buque buque) {
		this.coordenada = coordenada;
		this.buque = buque;
	}

	/**
	 * Describe la distancia entre la coordenada del GPS y otra coordenada.
	 * @param coordenada2, Coordenada, Otra coordenada diferente a la del gps, no puede ser nula.
	 * @return double, la distancia entre el gps y la otra coordenada.
	 */
	private double calcularDistancia(Coordenada coordenada2) {
		double deltaX = coordenada2.getX() - this.coordenada.getX();
        double deltaY = coordenada2.getY() - this.coordenada.getY();
        return Math.hypot(deltaX, deltaY);
	}

	/**
	 * Avisa al barco para actualizar su estado según la coordenada en la que esta posicionado.
	 * Si es que tiene un viaje vinculado.
	 */
	private void actualizarEstadoDelBuque(){
		if(buque.getViaje() != null) {
			buque.actualizarEstado(calcularDistancia(buque.getDestino().getCoordenadas()));
		}
	}

	public Coordenada getCoordenadas() {
		return this.coordenada;
	}

	/**
	 * Cambia la coordenada y avisa al buque sobre si tiene que actualizar su estado.
	 * @param coordenada2
	 */
	public void setCoordenadas(Coordenada coordenada2) {
		this.coordenada = coordenada2;
		actualizarEstadoDelBuque();
	}
}
