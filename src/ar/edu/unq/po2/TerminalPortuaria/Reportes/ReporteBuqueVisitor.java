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
    public void visitar(TerminalPortuaria terminal, Buque buque) {
        // No hace nada en particular con la terminal
    }

    @Override
    public void visitar(OrdenImportacion orden, Buque buque) {
        xml.append("<item>")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode()) // placeholder de ID
            .append("</item>\n");
    }

    @Override
    public void visitar(OrdenExportacion orden, Buque buque) {
        xml.append("</import>\n<export>\n");
        xml.append("<item>")
            .append(orden.getBill().getBillOfLandings().get(0).hashCode()) // placeholder de ID
            .append("</item>\n");
    }

    @Override
	public String generarReporte() {
        xml.append("</export>\n</report>");
        return xml.toString();
    }
}
