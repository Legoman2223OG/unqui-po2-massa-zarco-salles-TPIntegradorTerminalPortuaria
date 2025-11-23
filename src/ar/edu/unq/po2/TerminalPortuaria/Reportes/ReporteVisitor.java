package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public interface ReporteVisitor {
	void visitarTerminal(TerminalPortuaria terminal, Buque buque);
    void visitarOrden(Orden orden, Buque buque);
    void visitarBL(IBillOfLanding bl, Buque buque);
    String generarReporte();
}
