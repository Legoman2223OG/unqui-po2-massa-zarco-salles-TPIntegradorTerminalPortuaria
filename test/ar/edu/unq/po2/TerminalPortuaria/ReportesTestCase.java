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
    private Container spyContainer;

    @BeforeEach
    void setUp() throws Exception {
        // DOC
    	Cliente clienteMock = mock(Cliente.class);
    	TransporteAsignado transporteMock = mock(TransporteAsignado.class);
    	LocalDateTime fechaTurno = mock(LocalDateTime.class);
        buqueMock = mock(Buque.class);
        viajeMock = mock(Viaje.class);
        ordenImportTest = new OrdenImportacion(clienteMock, viajeMock, null, transporteMock, fechaTurno, 101);
        ordenExportTest = new OrdenExportacion(clienteMock, viajeMock, null, transporteMock, fechaTurno, 101);
        DryContainer container1 = new DryContainer(1, 2, 3, null);
        TankContainer container2 = new TankContainer(3, 4, 5, "juan0987654", null);
        spyContainer = spy(container1);
        Servicio servicio1 = new Pesado(spyContainer, 0);
        Servicio servicio2 = new Pesado(container2, 0);
        
        ordenImportTest.agregarServicio(servicio1);
        ordenExportTest.agregarServicio(servicio2);

        // SUT
        terminalTest = new TerminalPortuaria("Terminal Test", new Coordenada(0,0));
        terminalTest.registrarNuevaOrden(ordenImportTest);
        terminalTest.registrarNuevaOrden(ordenExportTest);
        
        visitorMuelleTest = new ReporteMuelleVisitor();
        visitorAduanaTest = new ReporteAduanaVisitor();
        visitorBuqueTest = new ReporteBuqueVisitor();

        // Stubs
        when(buqueMock.getNombre()).thenReturn("Buque Test");
        when(buqueMock.getViaje()).thenReturn(viajeMock);
        when(viajeMock.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 4, 10, 0));
        when(viajeMock.fechaDeLlegada()).thenReturn(LocalDateTime.of(2025, 11, 4, 18, 0));
        when(spyContainer.getIdentificador()).thenReturn("juli1234567");
    }

    @Test
    void testGenerarReporteMuelle() {
        // Exercise
        String reporte = terminalTest.generarReporte(visitorMuelleTest, buqueMock);

        // Verify
        assertTrue(reporte.contains("Buque: Buque Test"));
        assertTrue(reporte.contains("Contenedores operados: 2"));
        assertTrue(reporte.contains("Fecha arribo: 2025-11-04T10:00"));
        assertTrue(reporte.contains("Fecha partida: 2025-11-04T18:00"));
        verify(buqueMock, times(1)).getNombre();
    }
    
    @Test
    void testGenerarReporteAduana() {
    	// Exercise
    	String reporte = terminalTest.generarReporte(visitorAduanaTest, buqueMock);
    	
    	// Verify
    	assertTrue(reporte.contains("<p>Buque: Buque Test</p>"));
    	assertTrue(reporte.contains("<p>Fecha de arribo: 2025-11-04T18:00</p>"));
        assertTrue(reporte.contains("<p>Fecha de salida: 2025-11-04T10:00</p>"));
    	assertTrue(reporte.contains("<li>Container importado - Tipo: Dry. ID: juli1234567.</li>"));
    	assertTrue(reporte.contains("<li>Container exportado - Tipo: Tanque. ID: juan0987654.</li>"));
    	verify(buqueMock, times(1)).getNombre();
    }
    
    @Test
    void testGenerarReporteBuque() {
    	// Exercise
    	String reporte = terminalTest.generarReporte(visitorBuqueTest, buqueMock);
    	
    	// Verify
    	assertTrue(reporte.contains("<item>juli1234567</item>"));
    	assertTrue(reporte.contains("<item>juan0987654</item>"));
    }
}