package ar.edu.unq.po2.TerminalPortuaria.Cliente;

import java.time.LocalDateTime;

public class Cliente {

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

	public void recibirMail( String mensaje ) {} // método para poder recibir avisos de la terminal gestionada.	
}
