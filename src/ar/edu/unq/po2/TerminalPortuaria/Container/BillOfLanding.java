package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;


/**
 * Representa un Bill Of Landing común, el cual contiene
 * información de su dueño, los productos del contenedor y
 * el peso total de todos los productos.
 */
public class BillOfLanding implements IBillOfLanding{
	private Cliente duenio;
	private List<Producto> productos;

	/**
	 * Describe un Bill of Landing con su duenio y los productos incluidos.
	 * @param duenio, Cliente, el duenio importador del Bill of Landing. No puede ser nulo.
	 * @param productos, Producto..., una lista de productos incluidos en el bill of landing. No pueden ser nulos
	 */
	public BillOfLanding(Cliente duenio, Producto...productos ){
		this.duenio = duenio;
		this.productos = new ArrayList<Producto>(Arrays.asList(productos));
	}

	public List<Cliente> getDuenios() {
		return Arrays.asList(duenio);
	}

	public List<Producto> getProductos() {
		return this.productos;
	}
	
	/**
	 * Describe el peso total registrado por el bill of landing.
	 * @return double, el peso total de todos los productos incluidos en el bill of landing.
	 */
	public double getPeso() {
		return this.productos.stream().mapToDouble(p -> p.getPeso()).sum();
	}

	@Override
	public List<BillOfLanding> getBillOfLandings() {
		return Arrays.asList(this);
	}

	@Override
	public void agregarBillOfLanding(BillOfLanding bl) throws Exception {
		throw new Exception("No se puede agregar un bill of landing a un bill of landing normal");
	}
}
