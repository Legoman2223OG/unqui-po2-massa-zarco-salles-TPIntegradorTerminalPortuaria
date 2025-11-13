package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Servicio;


public abstract class Orden implements ElementoVisitable {

	protected Cliente cliente;
	protected Viaje viaje;
	protected IBillOfLanding bill;
	protected Set<Servicio> servicios = new HashSet<>();
	protected TransporteAsignado transporteAsignado;
    private int numFactura;
    private LocalDateTime fechaTurno;


	public Orden(Cliente cliente, Viaje viaje, IBillOfLanding bill,TransporteAsignado TAsignado, LocalDateTime turno, int num)
	{
		this.cliente = cliente;
		this.viaje = viaje;
		this.bill = bill;
		this.transporteAsignado = TAsignado;
		this.fechaTurno = turno;
		this.numFactura = num;
	}

	public abstract boolean esOrdenImportacion();
	public abstract boolean esOrdenExportacion();
	
	public Set<Servicio> getServicios() {
		return this.servicios;
	}
	
	public LocalDateTime getTurno() {
		return this.fechaTurno;
	}

	public Chofer getChoferAsignado() {
		return this.transporteAsignado.getChoferAsignado();
	}

	public Camion getCamionAsignado() {
		return this.transporteAsignado.getCamionAsignado();
	}

	public Viaje getViaje()
	{
		return this.viaje;
	}

	public IBillOfLanding getBill()
	{
		return this.bill;
	}

	public Cliente getCliente()
	{
		return this.cliente;
	}


	public int getNumFactura()
	{
		return this.numFactura;
	}

	public Factura generarFactura(Orden orden) throws Exception
	{
		return new Factura(this);
	}
	
	
	public void enviarFacturaPorMail() throws Exception{
		Factura factura = this.generarFactura(this);
		this.cliente.recibirFactura(factura);
	}
	
	public double costoServicios() {
		double costoServicios = this.servicios.stream()
	            .mapToDouble(servicio -> {
	                try {
	                    return servicio.calcularPrecio();
	                } catch (Exception e) {
	                    return 0;
	                }
	            })
	            .sum();

	    return costoServicios;
	}
	
	public double calcularCostoTotal() {
	    return this.costoServicios();
	}
	


}
