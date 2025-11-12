package ar.edu.unq.po2.TerminalPortuaria.Cliente;

import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;

public class Consignee extends Cliente{

	public Consignee(String nombre, int codigoCliente) {
		super(nombre, codigoCliente);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isShipper() {
		return false;
	}
	
	public boolean isConsignee() {
		return true;
	}


	@Override
	public String recibirFactura(Factura factura) {
		// TODO Auto-generated method stub
		return factura.toString();
	}

	@Override
	public String recibirAviso(String m) {
		// TODO Auto-generated method stub
		return null;
	}

}
