package ar.edu.unq.po2.TerminalPortuaria.Reportes;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Container.IBillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ReporteMuelleVisitor implements ReporteVisitor {
    private StringBuilder reporte = new StringBuilder();
    private int contenedoresOperados = 0;
    private LocalDateTime arribo;
    private LocalDateTime partida;

    @Override
    public void visitarTerminal(TerminalPortuaria terminal, Buque buque) {
        reporte.append("=== Reporte Muelle ===\n");
        reporte.append("Buque: ").append(buque.getNombre()).append("\n");
        arribo = buque.getViaje().getFechaSalida();  // o como corresponda
        partida = buque.getViaje().fechaDeLlegada();
    }

    @Override
    public void visitarOrden(Orden orden, Buque buque) {
        contenedoresOperados += 1;
    }
    
    @Override
	public String generarReporte() {
        reporte.append("Fecha arribo: ").append(arribo).append("\n");
        reporte.append("Fecha partida: ").append(partida).append("\n");
        reporte.append("Contenedores operados: ").append(contenedoresOperados).append("\n");
        return reporte.toString();
    }

	@Override
	public void visitarBL(IBillOfLanding bl, Buque buque) {} // Este metodo no hace nada particular para este reporte.
}
