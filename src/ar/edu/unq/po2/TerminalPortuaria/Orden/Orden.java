package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Servicio;


public class Orden {

	protected Cliente cliente;
	protected Viaje viaje;
	protected Container container;
	protected Set<Servicio> servicios = new HashSet<>();
	protected TransporteAsignado transporteAsignado;
	protected boolean servicioLavado;
	protected LocalDateTime entregaContainer;
	protected LocalDateTime salidaContainer;
    public UUID codigoUnico;

    public Orden() {}

	public Orden(Cliente cliente, Viaje viaje, Container container, boolean servicioLavado)
	{
		this.cliente = cliente;
		this.viaje = viaje;
		this.container = container;
//		this.transporteAsignado = empresa.asignarTransporte(this);
		this.codigoUnico = UUID.randomUUID();
	}

//	public abstract boolean esOrdenImportacion();
//	public abstract boolean esOrdenExportacion();

	public Chofer getChoferAsignado() {
		return this.transporteAsignado.getChoferAsignado();
	}
	
	public Camion getCamionAsignado() {
		return this.transporteAsignado.getCamionAsignado();
	}	

	public Viaje getViaje()
	{
		return viaje;
	}

	public Container getContainer()
	{
		return container;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public LocalDateTime getFechaDeSalidaCarga()
	{
		return viaje.getFechaSalida();
	}

	public LocalDateTime getFechaDeLlegadaCarga()
	{
		return viaje.fechaDeLlegada();
	}

	public void registrarEntregaContainer()
	{
		this.entregaContainer = LocalDateTime.now();
	}

	public void registrarSalidaContainer()
	{
		this.salidaContainer = LocalDateTime.now();
	}

	public LocalDateTime getEntregaContainer()
	{
		return entregaContainer;
	}

	public LocalDateTime getSalidaContainer()
	{
		return salidaContainer;
	}


	public UUID getCodigoUnico()
	{
		return codigoUnico;
	}
	
	public void enviarFacturaPorMail(){
		
	}


}
