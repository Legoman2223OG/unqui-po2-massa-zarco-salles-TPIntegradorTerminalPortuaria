package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

public class OrdenExportacion extends Orden{

	protected LocalDateTime salidaContainer;
	
	
	public OrdenExportacion(LocalDateTime salidaContainer) {
		super();
		this.salidaContainer = salidaContainer;
	}

	@Override
	public boolean esOrdenImportacion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esOrdenExportacion() {
		// TODO Auto-generated method stub
		return true;
	}


}
