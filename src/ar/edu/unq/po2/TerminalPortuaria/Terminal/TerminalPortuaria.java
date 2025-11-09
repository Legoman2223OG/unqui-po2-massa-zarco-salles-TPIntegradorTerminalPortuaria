package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima.Busqueda;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.*;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.*;


public class TerminalPortuaria {


	private String nombre;
	private List<LineaNaviera> misNavieras = new ArrayList<>();
	private Set<Orden> ordenes = new HashSet<>();
	private E_MejorRuta estrategia;
	private Busqueda busquedaMaritima;

	public TerminalPortuaria(String nombre)
	{
		this.nombre = nombre;
	}



	public List<LineaNaviera> getMisNavieras()
	{
		return this.misNavieras;
	}


//	public List<Viaje> getMisViajes()
//	{
//	    return this.misNavieras.stream()
//	            .flatMap(n -> n.getViajes().stream()) // Convierte los sets de viajes de todas las navieras en un solo stream
//	            .filter(viaje -> viaje.validarSiTerminalExisteEnViaje(this)) // Filtra los viajes que contienen la terminal
//	            .collect( Collectors.toList() ); // Recolecta los viajes en una lista.
//	}
	
//	public List<Viaje> busquedaViaje() {
//		this.busquedaMaritima.filtrar(this.getMisViajes());
//	}
	
	 public void setMejorCircuito( E_MejorRuta estrategia ) {
	 	this.estrategia = estrategia;
	 }


	 public Circuito getMejorCircuito(TerminalPortuaria terminalDestino) {
		 return estrategia.mejorCircuitoHacia(this, terminalDestino);
	 }


//
//	public void darAvisoShippers( Viaje viaje )
//	{
//		List<Orden> ordenesExportacion = ordenes.stream().filter( o -> o.esOrdenExportacion() ).toList();
//		List<Cliente> listaConsignees = ordenesExportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).toList();
//
//		listaConsignees.stream().forEach( c -> c.recibirMail("Su carga está llegando") );
//	}

//	public void darAvisoConsignees( Viaje viaje )
//	{
//		List<Orden> ordenesImportacion = ordenes.stream().filter( o -> o.esOrdenImportacion() ).toList();
//		List<Cliente> listaConsignees = ordenesImportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).toList();
//		listaConsignees.stream().forEach( c -> c.recibirMail("Su carga ha salido de la terminal") );
//	}

	public void enviarFacturaOrden( Viaje viaje )
	{
		List<Orden> ordenesVinculadasAlViaje = this.ordenes.stream().filter( o -> o.getViaje() == viaje ).toList();
		ordenesVinculadasAlViaje.forEach( o -> o.enviarFacturaPorMail() );
	}


	public void registrasNuevaOrden(Orden orden)
	{
		this.ordenes.add(orden);
	}


	public void registrarNuevaNaviera(LineaNaviera nav)
	{
		if ( this.estoyEnUnCircuitoDeLaNaviera(nav) )
		{
			this.misNavieras.add(nav);
		}
	}


	public boolean estoyEnUnCircuitoDeLaNaviera(LineaNaviera nav)
	{
		List<Circuito> circuitosNaviera = nav.getCircuitos();
		return circuitosNaviera.stream().anyMatch(cir->cir.terminalExisteEnElCircuito(this));
	}

	public void trabajoCargaYDescarga(Buque buque) throws Exception
	{
		buque.working();
	}

	public void depart(Buque buque) throws Exception
	{
		buque.depart();
	}


	public void entregaTerrestreExp(Orden orden, Camion camion, Chofer chofer) throws Exception
	{
		this.validarCamion(camion, orden);
		this.validarChofer(chofer, orden);
		this.validarHorarioDeEntrega(orden);
		orden.registrarEntregaContainer();
	}


	public void validarEntregaTerrestreImp(Orden orden, Camion camion, Chofer chofer) throws Exception
	{
		this.validarCamion(camion, orden);
		this.validarChofer(chofer, orden);
		orden.registrarSalidaContainer();
	}


	public void validarHorarioDeEntrega(Orden orden) throws Exception
	{
		if (ChronoUnit.HOURS.between(orden.getCliente().getTurno(), LocalDateTime.now()) > 3)
		//if ( Math.abs (orden.getCliente().getTurno().getHour() - LocalDateTime.now().getHour()) > 3 )
			// Está mal que el cliente tenga el horario de entrega. Debería tenerlo la propia orden porque un cliente puede tener multiples ordenes.
		{
			throw new Exception ("Llegaste tarde");
		}
	}


	public void validarChofer(Chofer chofer, Orden orden) throws Exception {
		if ( chofer != orden.getChoferAsignado())
		{
			throw new Exception ("El chofer no coincide");
		}
	}

	public void validarCamion(Camion camion, Orden orden) throws Exception
	{
		if ( camion != orden.getCamionAsignado())
		{
			throw new Exception ("El camión no coincide");
		}
	}

	public String getNombre()
	{
		return this.nombre;
	}


	 
//	 Necesito pasar esta terminal por parametro para que me diga la duracion del viaje
//	 public Duration duracionDelViaje() {
//		 
//	 }

	 public void partiendoAViaje(Viaje viaje) {
		// TODO Auto-generated method stub
		
	 }

	 public Coordenada getCoordenadas() {
		// TODO Auto-generated method stub
		return null;
	 }

	 public void proximoAArribar(Viaje viaje) {
		// TODO Auto-generated method stub
		
	 }

	 public void working(Buque buque1) {
		// TODO Auto-generated method stub
		
	 }

	
}
