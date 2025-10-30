package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

/**
 * Una clase que representa un container para transportar cosas. Posee un ancho, largo y altura incluido su peso.
 * Cada subclase debera tener un Bill Of Landing para tener en cuenta su contenido.
 */
public abstract class Container {
	private double ancho;
	private double largo;
	private double altura;
	private String identificador;
	protected IBillOfLanding bl;
	
	/**
	 * Describe un Container con su ancho, largo, altura y su identificador.
	 * @param ancho, double, el ancho del container, debe ser > 0.
	 * @param largo, double, el largo del container, debe ser > 0.
	 * @param altura, double, la altura del container, debe ser > 0.
	 * @param identificador, String, el identificador del contenedor, debe ser de 11 caracteres, siendo los primeros 4 parte del nombre del importador y los 7 restantes digitos numericos aleatorios.
	 * @param bl, BillOfLanding, bill of landing que define el contenido del contenedor.
	 * @throws Exception 
	 */
	public Container(double ancho, double largo, double altura, String identificador, IBillOfLanding bl) throws Exception {
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
		asertarIdentificadorCorrecto(identificador);
		this.identificador = identificador;
		this.bl = bl;
	}

	/**
	 * Asegura que el identificador sea 4 digitos alfabeticos y
	 * 7 digitos numericos aleatorios.
	 * @param identificador
	 * @throws Exception, Si, las primeras 4 letras no son letras y los 7 ultimos caracteres no son digitos.
	 */
	private void asertarIdentificadorCorrecto(String identificador) throws Exception {
		if(identificador.length() < 11)
			throw new Exception("El identificador no es de 11 caracteres");
		//Los primeros 4 caracteres deben ser letras.
		if(!identificador.substring(0, 3).chars().allMatch(Character::isLetter))
			throw new Exception("No esta incluido el nombre del importador, usar letras");
		if(!identificador.substring(3,11).chars().allMatch(Character::isDigit))
			throw new Exception("No se incluyo digitos despues de las letras que identifican al importador");
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
	
	/**
	 * Describe el dueño o los dueños del contenedor.
	 * @return, List<Cliente>, una lista con el duenio o los duenios del contenedor.
	 */
	public abstract List<Cliente> getDuenio();
	
	/**
	 * Describe el Bill Of Landing o los Bill of Landing del contenedor.
	 * @return, List<BillOfLanding>, el bill of landing o los bill of landings del contenedor.
	 */
	public abstract List<BillOfLanding> getBillsOfLanding();
	
	/**
	 * Describe el peso total del contenedor basado en su contenido.
	 * @return, double, el peso del contenedor.
	 */
	public abstract double getPeso();
	
	/**
	 * Agrega a un bill of landing al contenedor, solo posible para contenedores
	 * que puedan tener más de un dueño.
	 * @param bl, BillOfLanding, el bill of landing a agregar.
	 * @throws Exception 
	 */
	public abstract void agregarBillOfLanding(BillOfLanding bl) throws Exception;
	
	public abstract double consumoElectricidad() throws Exception;
}
