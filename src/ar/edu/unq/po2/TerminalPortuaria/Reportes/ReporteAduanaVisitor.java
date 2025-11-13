package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteAduanaVisitor implements ReporteVisitor {
	private StringBuilder html = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {
        html.append("<html><body>");
        html.append("<h2>Reporte de Aduana</h2>");
        html.append("<p>Buque: ").append(buque.getNombreBuque()).append("</p>");
        html.append("<ul>");
    }

    @Override
    public void visitarOrden(OrdenImportacion orden, Buque buque) {
        html.append("<li>Container tipo: IMPORTACIÓN - ID: ")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</li>");
    }

    @Override
    public void visitarOrden(OrdenExportacion orden, Buque buque) {
        html.append("<li>Container tipo: EXPORTACIÓN - ID: ")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</li>");
    }

    public String generarReporte() {
        html.append("</ul></body></html>");
        return html.toString();
    }
}
