package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteBuqueVisitor implements ReporteVisitor {
	private StringBuilder xml = new StringBuilder();

    public ReporteBuqueVisitor() {
        xml.append("<report>\n<import>\n");
    }

    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {} // Nada en particular con la terminal

    @Override
    public void visitarOrden(OrdenImportacion orden, Buque buque) {
        xml.append("<item>")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</item>\n");
    }

    @Override
    public void visitarOrden(OrdenExportacion orden, Buque buque) {
        xml.append("</import>\n<export>\n");
        xml.append("<item>")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode())
            .append("</item>\n");
    }

    public String generarReporte() {
        xml.append("</export>\n</report>");
        return xml.toString();
    }
}
