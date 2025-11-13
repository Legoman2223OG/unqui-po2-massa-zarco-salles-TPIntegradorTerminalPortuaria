package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public interface ReporteVisitor {
	void visitarTerminal(TerminalPortuaria terminal, Buque buque);
    void visitarOrden(OrdenImportacion orden, Buque buque);
    void visitarOrden(OrdenExportacion orden, Buque buque);
    String generarReporte();
}
