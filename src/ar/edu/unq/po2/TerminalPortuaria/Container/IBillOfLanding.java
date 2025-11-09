package ar.edu.unq.po2.TerminalPortuaria.Container;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;


public interface IBillOfLanding {
	public abstract List<Cliente> getDuenios();
	public abstract List<Producto> getProductos();
	public abstract List<BillOfLanding> getBillOfLandings();
	public abstract double getPeso();
	public abstract void agregarBillOfLanding(BillOfLanding bl) throws Exception;
}
