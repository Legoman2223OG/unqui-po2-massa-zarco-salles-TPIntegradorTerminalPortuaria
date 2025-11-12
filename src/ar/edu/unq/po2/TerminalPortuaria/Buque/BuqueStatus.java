package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Una clase abstracta que representa un estado del barco según su distancia u operación con una terminal de destino u origen.
 */
public abstract class BuqueStatus {

	/**
	 * Cambia el estado según una distancia
	 * @param distancia, double, La distancia presente entre el buque y la terminal, debe ser >= 0.
	 */
	public abstract void actualizarEstado(double distancia, Buque buque);

	/**
	 * Empieza la fase de carga y descarga de contenedores en el buque.
	 * @param buque, Buque, el buque al cual pertenece este estado, no puede ser nulo.
	 * @throws Exception
	 */
	public abstract void working(Buque buque) throws Exception;

	/**
	 * Empieza la fase de partida de una terminal de origen.
	 * @param buque, Buque, el buque al cual pertenece este estado, no puede ser nulo.
	 * @throws Exception
	 */
	public abstract void depart(Buque buque) throws Exception;

	/**
	 * Segun el estado, mueve el bote a una nueva coordenada.
	 * @param coordenada, Coordenada, la nueva coordenada a la que tiene que moverse el bote, no puede ser nula.
	 * @param buque, Buque, el buque que pertenece a este estado, no puede ser nulo.
	 * @throws Exception
	 */
	public abstract void moverA(Coordenada coordenada, Buque buque) throws Exception;
}
