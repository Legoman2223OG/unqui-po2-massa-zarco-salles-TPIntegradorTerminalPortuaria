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
		this.ordenFacturada = ordenFacturada;
		conceptos = new HashSet<>();
		this.conceptos.add( new Concepto( "Monto total a pagar:", LocalDateTime.now(), ordenFacturada.calcularCostoTotal() ) );
		this.agregarCostoCircuito();
		this.agregarConceptosServicios();
	}
	
	public Set<Concepto> getConceptos() {
		return this.conceptos;
	}
	
	private void agregarCostoCircuito()
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
	
	public String toString() {
	    StringBuilder mensaje = new StringBuilder(
	        "Aquí está la factura de su orden N°: " + this.ordenFacturada.getNumFactura() + "\n"
	    );
	    for (Concepto c : this.conceptos) {
	        mensaje.append(c.toString()).append("\n");
	    }
	    return mensaje.toString();
	}
	
}
