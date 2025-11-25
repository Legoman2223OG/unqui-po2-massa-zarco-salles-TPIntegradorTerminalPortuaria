package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;

public class OrdenImportacion extends Orden implements ElementoVisitable {

	
	
	public OrdenImportacion(Cliente cliente, Viaje viaje, Container container, TransporteAsignado TAsignado,
			LocalDateTime turno, int num) {
		super(cliente, viaje, container, TAsignado, turno, num);
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
		visitor.visitarOrden(this, buque);
		this.getContainerDeOrden().aceptar(visitor, buque);
	}
	
	@Override
	public double calcularCostoTotal() {
	    double costoServicios = super.calcularCostoTotal();
	    double costoViaje = this.viaje.precioTotal();

	    return costoServicios + costoViaje;
	}

}
