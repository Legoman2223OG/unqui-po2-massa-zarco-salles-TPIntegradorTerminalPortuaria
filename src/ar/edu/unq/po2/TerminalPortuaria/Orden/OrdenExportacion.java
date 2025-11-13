package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;

public class OrdenExportacion extends Orden implements ElementoVisitable {



	
	public OrdenExportacion(Cliente cliente, Viaje viaje, IBillOfLanding bill, TransporteAsignado TAsignado, LocalDateTime turno, int num) {
		super(cliente, viaje, bill, TAsignado, turno, num);
		// TODO Auto-generated constructor stub
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

	@Override
	public void aceptar(ReporteVisitor visitor, Buque buque) {
		visitor.visitar(this, buque);
	}
}
