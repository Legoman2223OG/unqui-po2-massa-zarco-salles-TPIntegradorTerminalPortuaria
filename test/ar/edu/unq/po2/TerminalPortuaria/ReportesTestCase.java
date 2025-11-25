package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.*;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.*;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesTestCase {
	// SUT
    private TerminalPortuaria terminalTest;
    private ReporteMuelleVisitor visitorMuelleTest;
    private ReporteAduanaVisitor visitorAduanaTest;
    private ReporteBuqueVisitor visitorBuqueTest;
    
    // DOC
    private Buque buqueMock;
    private Viaje viajeMock;
    private OrdenImportacion ordenImportTest;
    private OrdenExportacion ordenExportTest;
    private Servicio docServicio1;
    private Servicio docServicio2;
    private Container docContainer1;
    private Container docContainer2;

    @BeforeEach
    void setUp() {
        // DOC
    	Cliente clienteMock = mock(Cliente.class);
    	TransporteAsignado transporteMock = mock(TransporteAsignado.class);
    	LocalDateTime fechaTurno = mock(LocalDateTime.class);
        buqueMock = mock(Buque.class);
        viajeMock = mock(Viaje.class);
        ordenImportTest = new OrdenImportacion(clienteMock, viajeMock, null, transporteMock, fechaTurno, 101);
        ordenExportTest = new OrdenExportacion(clienteMock, viajeMock, null, transporteMock, fechaTurno, 101);
        docServicio1 = mock(Pesado.class);
        docServicio2 = mock(Pesado.class);
        docContainer1 = mock(DryContainer.class);
        docContainer2 = mock(DryContainer.class);
        
        ordenImportTest.agregarServicio(docServicio1);
        ordenExportTest.agregarServicio(docServicio2);

        // SUT
        terminalTest = new TerminalPortuaria("Terminal Test", new Coordenada(0,0));
        terminalTest.registrarNuevaOrden(ordenImportTest);
        terminalTest.registrarNuevaOrden(ordenExportTest);
        
        visitorMuelleTest = new ReporteMuelleVisitor();

        // Stubs
        when(buqueMock.getNombre()).thenReturn("Buque Test");
        when(buqueMock.getViaje()).thenReturn(viajeMock);
        when(viajeMock.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 4, 10, 0));
        when(viajeMock.fechaDeLlegada()).thenReturn(LocalDateTime.of(2025, 11, 4, 18, 0));
        when(docServicio1.getContainer()).thenReturn(docContainer1);
        when(docServicio2.getContainer()).thenReturn(docContainer2);
    }

    @Test
    void testGenerarReporteMuelle() {
        // Exercise
        String reporte = terminalTest.generarReporteMuelle(visitorMuelleTest, buqueMock);

        // Verify
        assertTrue(reporte.contains("Buque: Buque Test"));
        assertTrue(reporte.contains("Contenedores operados: 2"));
        assertTrue(reporte.contains("Fecha arribo: 2025-11-04T10:00"));
        assertTrue(reporte.contains("Fecha partida: 2025-11-04T18:00"));
        verify(buqueMock, times(1)).getNombre();
    }
}