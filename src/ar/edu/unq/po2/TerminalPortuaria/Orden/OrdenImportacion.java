package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

public class OrdenImportacion extends Orden{

	protected LocalDateTime entregaContainer;
	
	
	
	public OrdenImportacion(LocalDateTime entregaContainer) {
		super();
		this.entregaContainer = entregaContainer;
	}

	@Override
	public boolean esOrdenImportacion() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean esOrdenExportacion() {
		// TODO Auto-generated method stub
		return false;
	}

}
