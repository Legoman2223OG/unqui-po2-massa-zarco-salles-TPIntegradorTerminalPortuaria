package ar.edu.unq.po2.TerminalPortuaria;

public class Buque {

	private GPS gps;
	private BuqueStatus status;
	private TerminalPortuaria destino;
	private Viaje viaje;
	
	/**
	 * Constructor con intenciones de testing para el testeo de las fases.
	 * Empieza estando en el estado de outbound, con el gps marcando la respectiva coordenada
	 * @param coordenada, Coordenada, una coordenada que indica donde se posiciona el barco actualmente, no puede ser nula.
	 * @param destino, TerminalPortuaria, la terminal portuaria de destino del barco, no puede ser nula.
	 * @param viaje, Viaje, el viaje que tiene que hacer el barco, no puede ser nulo.
	 */
	public Buque(Coordenada coordenada, TerminalPortuaria destino, Viaje viaje) {
		this.destino = destino;
		this.viaje = viaje;
		this.gps = new GPS(coordenada,this);
		this.status = new Outbound();
	}

	public TerminalPortuaria getDestino() {
		// TODO Auto-generated method stub
		return this.destino;
	}
	
	/**
	 * Actualiza el estado según la distancia y el estado actual
	 * @param distancia
	 */
	public void actualizarEstado(double distancia) {
		status.actualizarEstado(distancia, this);
	}
	
	/**
	 * Si el buque se encuentra en la terminal de destino y necesita cargar o descargar, entonces pasa a la fase working.
	 * @throws Exception 
	 */
	public void working() throws Exception {
		this.status.working(this);
	}
	
	/**
	 * Si el buque se prepara para salir de la terminal origen, pasa a departing.
	 * @throws Exception 
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
		return this.gps.getCoordenadas();
	}
	
	/**
	 * Cambia las coordenadas del barco según su GPS.
	 * @param coordenda, Coordenada, la nueva coordenada del buque, no puede ser nula.
	 */
	public void setCoordenadas(Coordenada coordenda) {
		this.gps.setCoordenadas(coordenda);
	}
	
	/**
	 * Mueve el buque a una coordenada nueva.
	 * @param coordenada, Coordenada, una nueva coordenada, no puede ser nula.
	 * @throws Exception 
	 */
	public void moverA(Coordenada coordenada) throws Exception {
		this.status.moverA(coordenada, this);
	}

	public Viaje getViaje() {
		return this.viaje;
	}
}

