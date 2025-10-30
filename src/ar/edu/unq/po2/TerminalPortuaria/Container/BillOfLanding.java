package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente;

/**
 * Una clase abstracta que representa un Bill Of Landing. Un Bill Of Landing posee el patrón composite.
 */
public abstract class BillOfLanding {
	/**
	 * Describe el peso total de todos los productos del BL.
	 * @return double, el peso total de todos los productos del BL.
	 */
	public abstract double peso();
	/**
	 * Describe los propietarios de los items, si solo posee 1, da error.
	 * @return List<Cliente>, la lista de los propietarios.
	 * @throws Exception, Si es que se trata de un Bill Of Landing normal.
	 */
	public abstract List<Cliente> duenios() throws Exception;
	/**
	 * Describe al propietario del BL, si son varios, da error.
	 * @return, Cliente, el duenio del BL.
	 * @throws Exception, si es que es un Bill of Landing especial.
	 */
	public abstract Cliente duenio() throws Exception;
	/**
	 * Describe el contenido listado del BL.
	 * @return List<Producto>, Los productos listados en el BL.
	 */
	public abstract List<Producto> productos();
	/**
	 * Agrega un nuevo bill of landing al contenedor. Mientras este sea un bill of landing especial, sino da error.
	 * @throws Exception, Si es que es un bill of landing normal.
	 */
	public abstract void agregarBL(BillOfLanding bl) throws Exception;
	
}
