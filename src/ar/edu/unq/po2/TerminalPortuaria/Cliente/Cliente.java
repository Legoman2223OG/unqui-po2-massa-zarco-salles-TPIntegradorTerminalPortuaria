package ar.edu.unq.po2.TerminalPortuaria.Cliente;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;

public abstract class Cliente {

	protected String nombre;
	protected int codigoCliente;
	protected LocalDateTime turno;


	public Cliente(String nombre, int codigoCliente)
	{
		super();
		this.nombre = nombre;
		this.codigoCliente = codigoCliente;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getCodigoCliente()
	{
		return codigoCliente;
	}

	public LocalDateTime getTurno()
	{
		return turno;
	}

	public void setTurno(LocalDateTime turno)
	{
		this.turno = turno;
	}

	public abstract String recibirFactura(Factura factura);
	public abstract String recibirAviso(String m);
	public abstract boolean isShipper();
	public abstract boolean isConsignee();

}
