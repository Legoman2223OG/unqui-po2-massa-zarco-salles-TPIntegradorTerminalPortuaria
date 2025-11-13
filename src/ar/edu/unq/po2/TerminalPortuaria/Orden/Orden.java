package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Servicio;


public abstract class Orden implements ElementoVisitable {

	protected Cliente cliente;
	protected Viaje viaje;
	protected IBillOfLanding bill;
	protected Set<Servicio> servicios = new HashSet<>();
	protected TransporteAsignado transporteAsignado;
	protected boolean servicioLavado;
    public UUID codigoUnico;
    public LocalDateTime fechaTurno;

    public Orden() {}

	public Orden(Cliente cliente, Viaje viaje, IBillOfLanding bill,TransporteAsignado TAsignado, boolean servicioLavado, LocalDateTime turno)
	{
		this.cliente = cliente;
		this.viaje = viaje;
		this.bill = bill;
		this.transporteAsignado = TAsignado;
		this.fechaTurno = turno;
		this.codigoUnico = UUID.randomUUID();
	}

	public abstract boolean esOrdenImportacion();
	public abstract boolean esOrdenExportacion();

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

	public LocalDateTime getFechaSaludaDeCarga()
	{
		
		return viaje.getFechaSalida();
	}
	

	public LocalDateTime getFechaDeLlegadaCarga()
	{
		return viaje.fechaDeLlegada();
	}


	public UUID getCodigoUnico()
	{
		return codigoUnico;
	}
	
	public void enviarFacturaPorMail(){
		
	}
}
