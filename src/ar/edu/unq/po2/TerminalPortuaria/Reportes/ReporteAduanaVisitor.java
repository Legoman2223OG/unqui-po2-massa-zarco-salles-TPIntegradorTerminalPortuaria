package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteAduanaVisitor implements ReporteVisitor {
	private StringBuilder html = new StringBuilder();

    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {
        html.append("<html><body>");
        html.append("<h2>Reporte de Aduana</h2>");
        html.append("<p>Buque: ").append(buque.getNombre()).append("</p>");
        html.append("<p>Fecha de arribo: ").append(buque.getViaje().fechaDeLlegada()).append("</p>");
        html.append("<p>Fecha de salida: ").append(buque.getViaje().getFechaSalida()).append("</p>");
        html.append("<ul>");
    }

    @Override
    public void visitarOrden(Orden orden, Buque buque) {
    	if (orden.esOrdenImportacion()) {
    		html.append("<li>Container importado - ");
    	} else {
    		html.append("<li>Container exportado - ");
    	}
    }

    @Override
	public String generarReporte() {
        html.append("</ul></body></html>");
        return html.toString();
    }

	@Override
	public void visitarContainer(Container container, Buque buque) {
		html.append("Tipo: ").append(container.tipoDeContainer())
		    .append(". ID: ").append(container.getIdentificador())
		    .append(".</li>");
	}
}
