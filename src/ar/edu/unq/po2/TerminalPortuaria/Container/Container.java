package ar.edu.unq.po2.TerminalPortuaria.Container;

/**
 * Una clase que representa un container para transportar cosas. Posee un ancho, largo y altura incluido su peso.
 * Cada subclase debera tener un Bill Of Landing para tener en cuenta su contenido.
 */
public abstract class Container {
	private double ancho;
	private double largo;
	private double altura;
	private BillOfLanding bl;
	
	/**
	 * Describe un Container con su ancho, largo y altura.
	 * @param ancho, double, el ancho del container, debe ser > 0.
	 * @param largo, double, el largo del container, debe ser > 0.
	 * @param altura, double, la altura del container, debe ser > 0.
	 */
	public Container(double ancho, double largo, double altura) {
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
	}

	public double getAncho() {
		return ancho;
	}

	public double getLargo() {
		return largo;
	}

	public double getAltura() {
		return altura;
	}
	
}
