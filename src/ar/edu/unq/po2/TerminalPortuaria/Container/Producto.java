package ar.edu.unq.po2.TerminalPortuaria.Container;

/**
 * Un Value Object que representa un producto con su nombre y peso.
 */
public class Producto {
	private String nombre;
	private double peso;
	
	/**
	 * Crea un producto con su nombre y peso.
	 * @param nombre, String, El nombre del producto, no puede ser un string vacio o nulo.
	 * @param peso, double, El peso del producto, debe ser > 0.
	 */
	public Producto(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPeso() {
		return peso;
	}
	
}
