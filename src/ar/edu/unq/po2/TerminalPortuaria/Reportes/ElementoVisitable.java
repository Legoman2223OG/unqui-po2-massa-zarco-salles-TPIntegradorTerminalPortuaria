package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;

public interface ElementoVisitable {
	void aceptar(ReporteVisitor visitor, Buque buque);
}
