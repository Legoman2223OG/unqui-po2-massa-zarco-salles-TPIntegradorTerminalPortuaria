package ar.edu.unq.po2.TerminalPortuaria.Buque;

/**
 * Un Value Object que representa una Coordenada en el plano del sistema.
 */
public class Coordenada {
	private int x, y;

	/**
	 * Crea una coordenada segun una posicion x e y.
	 * @param x, int, la coordenada x
	 * @param y, int, la coordenada y
	 */
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
