package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;

public class OrdenImportacion extends Orden implements ElementoVisitable {
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

	@Override
	public void aceptar(ReporteVisitor visitor, Buque buque) {
		visitor.visitar(this, buque);
	}
}
