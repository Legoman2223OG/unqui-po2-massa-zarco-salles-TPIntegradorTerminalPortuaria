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

	public Coordenada getCoordenadas() {
		return this.coordenada;
	}

	/**
	 * Cambia la coordenada y avisa al buque sobre si tiene que actualizar su estado.
	 * @param coordenada, Coordenada, la coordenada nueva a la que se debe de mover. No puede ser nulo.
	 * @param coordenadaTerminal, Coordenada de la terminal, ya sea la de origen o destino. No puede ser nulo.
	 */
	public void setCoordenadas(Coordenada coordenada, Coordenada coordenadaTerminal) {
		this.coordenada = coordenada;
		buque.actualizarEstado(calcularDistancia(coordenadaTerminal));
	}
	
	/**
	 * Cambia la coordenada pero sin avisarle al buque sobre actualizar su estado. (Esto suele pasar cuando el buque llego a su tramo final o no tiene un viaje).
	 * @param coordenada, Coordenada, la nueva coordenada a donde se tiene que mover el buque.
	 */
	public void setCoordenadas(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
}
