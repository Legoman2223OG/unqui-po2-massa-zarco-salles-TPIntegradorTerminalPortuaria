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
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;


public class TerminalPortuaria implements ElementoVisitable {
	private String nombre;
	private Coordenada coordenada = new Coordenada (0,0);
	private List<LineaNaviera> misNavieras = new ArrayList<>();
	private Set<Orden> ordenes = new HashSet<>();
	private E_MejorRuta estrategia = new E_MenorPrecio();


	public TerminalPortuaria(String nombre, Coordenada coordenada)
	{
		this.coordenada = coordenada;
		this.nombre = nombre;
	}
//	
	//Metodo de busqueda de ruta maritima
	public List<Viaje> buscar(Busqueda filtro) {
        return filtro.filtrar(this.getMisViajes());
    }

    //Getters y setters y adds
	public List<LineaNaviera> getMisNavieras()
	{
		return this.misNavieras;
	}


	public Set<Orden> getOrdenes() {
		return this.ordenes;
	}
	
	 public Coordenada getCoordenadas() {
		// TODO Auto-generated method stub
		return this.coordenada;
	 }
	 
	public String getNombre() {
		return this.nombre;
	}
	


	public List<Viaje> getMisViajes() {
	    return this.misNavieras.stream()
	            .flatMap(n -> n.getViajes().stream()) // Convierte los sets de viajes de todas las navieras en un solo stream
	            .filter(viaje -> viaje.validarSiTerminalExisteEnViaje(this)) // Filtra los viajes que contienen la terminal
	            .collect( Collectors.toList() ); // Recolecta los viajes en una lista.
	}

	public void setEstrategia( E_MejorRuta estrategia ) {
		 this.estrategia = estrategia;
	}

	public E_MejorRuta getEstrategia() {
		 return this.estrategia;
	}


	public Circuito getMejorCircuito(TerminalPortuaria terminalDestino) {
		 return estrategia.mejorCircuitoHacia(this, terminalDestino);
	}
	 
	public void registrarNuevaOrden(Orden orden) {
		 this.ordenes.add(orden);
	}


	public void registrarNuevaNaviera(LineaNaviera nav) {
		if ( this.estoyEnUnCircuitoDeLaNaviera(nav) )
		{
			this.misNavieras.add(nav);
		}
	}


	 
    //Avisos para shippers y consigness
	public void darAvisoShippers( Viaje viaje ) {
		List<Orden> ordenesExportacion = ordenes.stream().filter( o -> o.esOrdenExportacion() ).toList();
		List<Cliente> listaShippers = ordenesExportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).toList();

		listaShippers.stream().forEach( c -> c.recibirAviso("Su carga ha salido de la terminal") );
	}

	public void darAvisoConsignees( Viaje viaje ) {
		List<Orden> ordenesImportacion = ordenes.stream().filter( o -> o.esOrdenImportacion() ).toList();
		List<Cliente> listaConsignees = ordenesImportacion.stream().filter( o -> o.getViaje() == viaje ).map( v -> v.getCliente() ).collect(Collectors.toList());
		listaConsignees.stream().forEach( c -> c.recibirAviso("Su carga está llegando") );
	}

	public void enviarFacturaOrden( Viaje viaje ) {
		List<Orden> ordenesVinculadasAlViaje = this.ordenes.stream().filter( o -> o.getViaje() == viaje ).toList();
		ordenesVinculadasAlViaje.forEach( o -> {
			try {
				o.enviarFacturaPorMail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} );
	}
	
	public LocalDateTime proximaSalidaHacia(TerminalPortuaria destino) {
	    return this.getMisNavieras().stream()
	        .flatMap(n -> n.getViajes().stream())
	        .filter(v -> v.getPuertoInicio() == this && v.puertoDeLlegada() == destino)
	        .map(Viaje::getFechaSalida)
	        .min(LocalDateTime::compareTo)
	        .orElse(null);
	}



	//Validaciones
	public boolean estoyEnUnCircuitoDeLaNaviera(LineaNaviera nav)
	{
		List<Circuito> circuitosNaviera = nav.getCircuitos();
		return circuitosNaviera.stream().anyMatch(cir->cir.terminalExisteEnElCircuito(this));
	}
	
	//Realiza validaciones de camion y chofer y horario, si cumplen se registra la orden
	public void exportar(Orden orden, Camion camion, Chofer chofer) throws Exception
	{
		this.validarCamion(camion, orden);
		this.validarChofer(chofer, orden);
		this.validarHorarioDeEntrega(orden);
		this.registrarNuevaOrden(orden);
	}

	//Realiza validaciones de camion y chofer, si cumplen se registra la orden
	public void importar(Orden orden, Camion camion, Chofer chofer) throws Exception
	{
		this.validarCamion(camion, orden);
		this.validarChofer(chofer, orden);
		this.registrarNuevaOrden(orden);
	}


	public void validarHorarioDeEntrega(Orden orden) throws Exception
	{
		if (ChronoUnit.HOURS.between(orden.getTurno(), LocalDateTime.now()) > 3)
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
	
	

	public void working(Buque buque) throws Exception
	{
		buque.working();
	}

	public void depart(Buque buque) throws Exception
	{
		buque.depart();
	}


	public void partiendoAViaje(Viaje viaje) {
		 this.darAvisoShippers(viaje);
	}

	public void proximoAArribar(Viaje viaje) {
		 this.darAvisoConsignees(viaje);
	}
	 
	public LocalDateTime fechaSalidaBuque(Buque buque) {
		 return buque.getViaje().getFechaSalida();
	}


	 @Override
	 public void aceptar(ReporteVisitor visitor, Buque buque) {
		visitor.visitarTerminal(this, buque);
		for (Orden orden : ordenes) {
           orden.aceptar(visitor, buque);
        }
	}

	public String generarReporteDeBuque(ReporteVisitor visitor, Buque buque) {
		this.aceptar(visitor, buque);
		return visitor.generarReporte();
	}
}
