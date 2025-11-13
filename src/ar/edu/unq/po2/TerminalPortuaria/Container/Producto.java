package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.Objects;

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
	 * @throws Exception, Si es que el peso es negativo o 0.
	 */
	public Producto(String nombre, double peso) throws Exception {
		asertarPesoPositivo(peso);
		this.nombre = nombre;
		this.peso = peso;
	}

	/**
	 * Aserta que el peso sea positivo.
	 * @param peso2, double, el peso ingresado.
	 * @throws Exception, Si es que el peso es negativo o 0.
	 */
	private void asertarPesoPositivo(double peso) throws Exception {
		if(peso <= 0)
			throw new Exception("El peso no puede ser negativo o 0");
	}

	public String getNombre() {
		return nombre;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Producto other = (Producto) obj;
		return Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

}
