package ar.edu.unq.po2.TerminalPortuaria.Buque;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class Buque {

	private GestorDeCoordenadas gestorDeCoordenadas;
	private String nombre;
	private BuqueStatus status;
	private Viaje viaje;

	/**
	 * Instancia un buque en una coordenada especifica en conjunto a un viaje que debe de realizar. (Inicia en Outbound).
	 * @param nombre, String, el nombre del buque, no puede ser nulo o vacio con espacios o sin espacios.
	 * @param coordenada, Coordenada, una coordenada que indica donde se posiciona el barco actualmente, no puede ser nula.
	 * @param viaje, Viaje, el viaje que tiene que hacer el barco, no puede ser nulo.
	 */
	public Buque(String nombre,Coordenada coordenada, Viaje viaje) {
		this.nombre = nombre;
		this.gestorDeCoordenadas = new GestorDeCoordenadas(coordenada,this);
		this.viaje = viaje;
		this.status = new Outbound();
	}

	/**
	 * Instancia un buque sin ningun viaje determinado y en una coordenada especifica.
	 * @param nombre, String, el nombre del buque, no puede ser nulo o vacio con espacios o sin espacios.
	 * @param coordenada, Coordenada, Una coordenada donde se situa el buque, no puede ser nula.
	 */
	public Buque(String nombre, Coordenada coordenada) {
		this.nombre = nombre;
		this.gestorDeCoordenadas = new GestorDeCoordenadas(coordenada,this);
		this.status = new Outbound();
	}

	/**
	 * Describe la terminal de destino a la que debe ir el buque según el viaje.
	 * @throws NullPointerException, si es que el buque no tiene un viaje.
	 * @return
	 */
	public TerminalPortuaria getDestino() {
		return this.viaje.puertoDeLlegada();
	}

	/**
	 * Describe la terminal de origen del viaje.
	 * @throws NullPointerException, si es que el buque no tiene un viaje.
	 * @return
	 */
	public TerminalPortuaria getOrigen() {
		return this.viaje.getPuertoInicio();
	}

	/**
	 * Actualiza el estado según la distancia y el estado actual.
	 * @param distancia
	 */
	public void actualizarEstado(double distancia) {
		//Si no hay viaje, no es necesario chequear coordenadas.
		if(this.viaje != null && this.viaje.puertoDeLlegada() != null) {
			this.status.actualizarEstado(distancia, this);
		}
	}

	/**
	 * Si el buque se encuentra en la terminal de destino y necesita cargar o descargar, entonces pasa a la fase working.
	 * @throws Exception, depende del estado, este mensaje podria no estar disponible para llamarlo.
	 */
	public void working() throws Exception {
		this.status.working(this);
	}

	/**
	 * Si el buque se prepara para salir de la terminal origen, pasa a departing.
	 * @throws Exception, depende del estado, este mensaje podria no estar disponible para llamarlo.
	 */
	public void depart() throws Exception {
		this.status.depart(this);
	}

	public void setStatus(BuqueStatus status) {
		this.status = status;
	}

	/**
	 * Obtiene las coordenadas del barco según su GPS.
	 * @return Coordenada, La coordenada del buque, no puede ser nula.
	 */
	public Coordenada getCoordenadas() {
		return this.gestorDeCoordenadas.getCoordenadas();
	}

	/**
	 * Mueve el buque a una coordenada nueva.
	 * @param coordenada, Coordenada, una nueva coordenada, no puede ser nula.
	 * @throws Exception, depende del estado, este mensaje podria no estar disponible para llamarlo.
	 */
	public void moverA(Coordenada coordenada) throws Exception {
		this.status.moverA(coordenada, this);
	}

	public Viaje getViaje() {
		return this.viaje;
	}

	/**
	 * Si es necesario cambiar de viaje, con este mensaje se podra cambiarlo. Si ya no necesita seguir un viaje,
	 * puede quedarse en null.
	 * @param viaje, Viaje, un nuevo viaje que debe hacer el buque.
	 */
	public void setViaje(Viaje viaje) {
		this.status = new Outbound();
		this.viaje = viaje;
	}

	public GestorDeCoordenadas getGestorDeCoordenadas() {
		return this.gestorDeCoordenadas;
	}

	public String getNombre() {
		return this.nombre;
	}

}

