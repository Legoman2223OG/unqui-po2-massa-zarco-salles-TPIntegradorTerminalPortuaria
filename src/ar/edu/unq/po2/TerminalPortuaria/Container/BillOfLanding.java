package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;


/**
 * Representa un Bill Of Landing común, el cual contiene
 * información de su dueño, los productos del contenedor y
 * el peso total de todos los productos.
 */
public class BillOfLanding implements IBillOfLanding, ElementoVisitable{
	private Cliente duenio;
	private List<Producto> productos;
	private Container container;

	/**
	 * Describe un Bill of Landing con su duenio y los productos incluidos.
	 * @param duenio, Cliente, el duenio importador del Bill of Landing. No puede ser nulo.
	 * @param productos, Producto..., una lista de productos incluidos en el bill of landing. No pueden ser nulos
	 */
	public BillOfLanding(Cliente duenio, Container container, Producto...productos){
		this.duenio = duenio;
		this.productos = new ArrayList<>(Arrays.asList(productos));
		this.container = container;
	}

	@Override
	public List<Cliente> getDuenios() {
		return Arrays.asList(duenio);
	}

	@Override
	public List<Producto> getProductos() {
		return this.productos;
	}

	/**
	 * Describe el peso total registrado por el bill of landing.
	 * @return double, el peso total de todos los productos incluidos en el bill of landing.
	 */
	@Override
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
	
	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void aceptar(ReporteVisitor visitor, Buque buque) {
		visitor.visitarBL(this, buque);
	}
}
