package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public interface ReporteVisitor {
	// Entidades principales
	void visitar(TerminalPortuaria terminal, Buque buque);
    void visitar(OrdenImportacion orden, Buque buque);
    void visitar(OrdenExportacion orden, Buque buque);
    String generarReporte();
}
