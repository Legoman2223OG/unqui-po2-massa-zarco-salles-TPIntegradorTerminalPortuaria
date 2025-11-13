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

	protected LocalDateTime salidaContainer;


	public OrdenExportacion(
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
        this.salidaContainer = salidaContainer;
    }

	public LocalDateTime getSalidaContainer() {
		return this.salidaContainer;
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
