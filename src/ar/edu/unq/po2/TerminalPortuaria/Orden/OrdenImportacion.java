package ar.edu.unq.po2.TerminalPortuaria.Orden;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;

public class OrdenImportacion extends Orden implements ElementoVisitable {

	protected LocalDateTime entregaContainer;

	public OrdenImportacion(
            Cliente cliente,
            Viaje viaje,
            IBillOfLanding bill,
            TransporteAsignado transporte,
            boolean servicioLavado,
            LocalDateTime turno,
            int numFactura,
            LocalDateTime salidaContainer
    ) {
		super(cliente, viaje, bill, transporte, servicioLavado, turno, numFactura);
		this.entregaContainer = salidaContainer;
	}
	
	public LocalDateTime getEntregaContainer() {
		return this.entregaContainer;
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
	
	@Override
	public double calcularCostoTotal() {
	    double costoServicios = super.calcularCostoTotal();
	    double costoViaje = this.viaje.precioTotal();

	    return costoServicios + costoViaje;
	}

}
