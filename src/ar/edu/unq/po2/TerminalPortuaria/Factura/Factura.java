package ar.edu.unq.po2.TerminalPortuaria.Factura;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Servicio;

public class Factura {

	private Orden ordenFacturada;
	private Set<Concepto> conceptos;
	
	public Factura( Orden ordenFacturada ) throws Exception
	{
		conceptos = new HashSet<>();
		this.conceptos.add( new Concepto( "Monto total a pagar:", LocalDateTime.now(), ordenFacturada.calcularCostoTotal() ) );
		this.agregarCostoCircuito();
		this.agregarConceptosServicios();
	}
	
	public void agregarCostoCircuito()
	{
		if( this.ordenFacturada.esOrdenImportacion() )
		{
			this.conceptos.add( new Concepto( "Monto total del circuito", ordenFacturada.getTurno(), ordenFacturada.getViaje().precioTotal() ) );
		}
	}
	
	private void agregarConceptosServicios() throws Exception
	{
		for( Servicio servicio: this.ordenFacturada.getServicios() )
		{
			this.conceptos.add( new Concepto(servicio.toString(), ordenFacturada.getTurno(), servicio.calcularPrecio()) );
		}
	}
	
	public String toString()
	{
		String mensajeFactura = "Aquí está la factura de su orden N°: " + this.ordenFacturada.codigoUnico.toString();
			for( Concepto c: this.conceptos )
			{
				mensajeFactura += c.toString() + "/n";
			}
		return mensajeFactura;
	}
	
}
