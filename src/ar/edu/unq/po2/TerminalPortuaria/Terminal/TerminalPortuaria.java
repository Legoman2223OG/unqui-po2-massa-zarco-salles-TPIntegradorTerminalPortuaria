package ar.edu.unq.po2.TerminalPortuaria.Terminal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima.Busqueda;

public class TerminalPortuaria {


	private String nombre;
	private List<Naviera> misNavieras = new ArrayList<>();
	private Set<Orden> ordenes = new HashSet<>();
	private E_MejorRuta estrategia;
	private Busqueda busquedaMaritima;


	public TerminalPortuaria() {}

	public TerminalPortuaria(String nombre)
	{
		this.nombre = nombre;
		this.misNavieras = new ArrayList<>();
		this.ordenes = new HashSet<>();
	}



	public List<Naviera> getMisNavieras()
	{
		return this.misNavieras;
	}


	public List<Viaje> getMisViajes()
	{
	    return this.misNavieras.stream()
	            .flatMap(n -> n.getViajes().stream()) // Convierte los sets de viajes de todas las navieras en un solo stream
	            .filter(viaje -> viaje.validarSiTerminalExisteEnViaje(this)) // Filtra los viajes que contienen la terminal
	            .collect( Collectors.toList() ); // Recolecta los viajes en una lista.
	}
	
	public List<Viaje> busquedaViaje() {
		this.busquedaMaritima.filtrar(this.getMisViajes());
	}
	
	 public void setMejorCircuito( E_MejorRuta estrategia ) {
	 	this.estrategia = estrategia;
	 }


	 public Circuito getMejorCircuito() {
	 }



	public void darAvisoShippers( Viaje viaje )
	{
		List<Orden> ordenesExportacion = ordenes.stream().filter( o -> o.esOrdenExportacion() ).toList();
		List<Cliente> listaConsignees = ordenesExportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).toList();

		listaConsignees.stream().forEach( c -> c.recibirMail("Su carga está llegando") );
	}

	public void darAvisoConsignees( Viaje viaje )
	{
		List<Orden> ordenesImportacion = ordenes.stream().filter( o -> o.esOrdenImportacion() ).toList();
		List<Cliente> listaConsignees = ordenesImportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).toList();
		listaConsignees.stream().forEach( c -> c.recibirMail("Su carga ha salido de la terminal") );
	}

	public void enviarFacturaOrden( Viaje viaje )
	{
		List<Orden> ordenesVinculadasAlViaje = this.ordenes.stream().filter( o -> o.getViaje() == viaje ).toList();
		ordenesVinculadasAlViaje.forEach( o -> o.enviarFacturaPorMail() );
	}


	public void registrasNuevaOrden(Orden orden)
	{
		this.ordenes.add(orden);
	}


	public void registrarNuevaNaviera(Naviera nav)
	{
		if ( this.estoyEnUnCircuitoDeLaNaviera(nav) )
		{
			this.misNavieras.add(nav);
		}
	}


	public boolean estoyEnUnCircuitoDeLaNaviera(Naviera nav)
	{
		List<Circuito> circuitosNaviera = nav.getMisCircuitos();
		return circuitosNaviera.stream().anyMatch(cir->cir.validarSiTerminalExisteEnCircuito(this));
	}

	public void trabajoCargaYDescarga(Buque buque)
	{
		buque.working();
	}

	public void depart(Buque buque)
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
		if ( chofer.getNombre() != orden.getChoferAsignado().getNombre() )
		{
			throw new Exception ("El chofer no coincide");
		}
	}

	public void validarCamion(Camion camion, Orden orden) throws Exception
	{
		if ( camion.getPatente() != orden.getCamionAsignado().getPatente() )
		{
			throw new Exception ("El camión no coincide");
		}
	}

	public String getNombre()
	{
		return this.nombre;
	}



	 public long tiempoHasta(Viaje viaje, TerminalPortuaria puertoDestino) {
         return viaje.tiempoDeViajeDesdeHasta(this, puertoDestino);
     }

	
}
