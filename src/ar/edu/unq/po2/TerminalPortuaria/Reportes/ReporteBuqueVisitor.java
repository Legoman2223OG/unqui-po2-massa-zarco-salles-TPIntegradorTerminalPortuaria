package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteBuqueVisitor implements ReporteVisitor {
	private StringBuilder xml = new StringBuilder();
	
    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {
    	xml.append("<report>\n<import>\n");
    }

    @Override
    public void visitarOrden(Orden orden, Buque buque) {
    	xml.append("<item>");
    }

    @Override
    public void finDeImportaciones() {
    	xml.append("</import>\n<export>\n");
    }
    
    @Override
	public String generarReporte() {
        xml.append("</export>\n</report>");
        return xml.toString();
    }

	@Override
	public void visitarContainer(Container container, Buque buque) {
		xml.append(container.getIdentificador())
		   .append("</item>\n");
	}
}
