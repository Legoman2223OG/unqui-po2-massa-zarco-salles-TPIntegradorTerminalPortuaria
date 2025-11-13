package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;


public interface IBillOfLanding {
	
	/**
	 * Describe el duenio / los duenios del BL.
	 * @return List<Cliente>, el duenio del BL o los duenios.
	 */
	public abstract List<Cliente> getDuenios();
	
	/**
	 * Describe los productos listados del BL.
	 * @return List<Producto>, los productos listados.
	 */
	public abstract List<Producto> getProductos();
	/**
	 * Describe el BL de un BL simple / los BLs de un BL compuesto.
	 * @return List<BillOfLanding>, el BL o los BLs de un BL compuesto.
	 */
	public abstract List<BillOfLanding> getBillOfLandings();
	/**
	 * Describe el peso estimulado del total de todos los productos listados.
	 * @return double, el peso total estimado de todos los productos.
	 */
	public abstract double getPeso();
	
	/**
	 * Agrega un BL a un BL compuesto.
	 * @param bl, BillOfLanding, un BL a agregar, no puede ser nulo.
	 * @throws Exception, Si es que no es un BL compuesto.
	 */
	public abstract void agregarBillOfLanding(BillOfLanding bl) throws Exception;
}
