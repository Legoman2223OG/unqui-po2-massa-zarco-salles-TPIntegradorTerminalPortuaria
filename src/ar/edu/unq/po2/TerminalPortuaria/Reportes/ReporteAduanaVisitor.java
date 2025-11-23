package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteAduanaVisitor implements ReporteVisitor {
	private StringBuilder html = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {
        html.append("<html><body>");
        html.append("<h2>Reporte de Aduana</h2>");
        html.append("<p>Buque: ").append(buque.getNombre()).append("</p>");
        html.append("<ul>");
    }

    @Override
    public void visitarOrden(Orden orden, Buque buque) {
    	if (orden.esOrdenImportacion()) {
    		html.append("<li>Container tipo: IMPORTACIÓN - ID: ")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</li>");
    	} else {
    		html.append("<li>Container tipo: EXPORTACIÓN - ID: ")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</li>");
    	}
    }

    @Override
	public String generarReporte() {
        html.append("</ul></body></html>");
        return html.toString();
    }

	@Override
	public void visitarBL(IBillOfLanding bl, Buque buque) {
		// TODO Auto-generated method stub
		
	}
}
