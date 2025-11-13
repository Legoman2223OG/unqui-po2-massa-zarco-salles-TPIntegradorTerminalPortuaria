package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.BuqueStatus;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_MejorRuta;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class TerminalTestCase {


    private TerminalPortuaria terminal;
    private Buque buqueMock;
    private Orden ordenMock;
    private Orden ordenTest;
    private Camion camionMock;
    private Camion camionMock2;
    private Chofer choferMock;
    private Chofer juan;
    private Buque buqueSpy;
    private E_MejorRuta strategySpy;
    private BuqueStatus statusMock;
    private Cliente clienteMock;
    private E_MejorRuta estrategy;
    Coordenada coordenadaDummy = new Coordenada(0, 0);
    Viaje viajeDummy = mock(Viaje.class);

    @BeforeEach
    public void setUp() {
        terminal = new TerminalPortuaria("Puerto Uno", coordenadaDummy);
        buqueMock = mock(Buque.class);
        ordenMock = mock(Orden.class);
        ordenTest = mock(Orden.class);
        camionMock = mock(Camion.class);
        camionMock2 = mock(Camion.class);
        choferMock = mock(Chofer.class);
        statusMock = mock(BuqueStatus.class);
        clienteMock = mock(Cliente.class);
        estrategy = mock(E_MejorRuta.class);
//        E_MejorRuta strategyReal = new E_MejorRuta();
//        strategySpy = spy(strategyReal);
        terminal.setEstrategia(estrategy);
        Buque buqueReal = new Buque("Test",coordenadaDummy, viajeDummy);
        buqueReal.setStatus(statusMock);
        buqueSpy = spy(buqueReal);

    }

    @Test
    public void testTrabajoCargaYDescarga() throws Exception {
        terminal.working(buqueSpy);
        verify(buqueSpy, times(1)).working();
    }

    @Test
	public void depart() throws Exception
	{
		terminal.depart(buqueSpy);
		verify(buqueSpy, times(1)).depart();
	}



    @Test
    public void testEntregaTerrestreExp() throws Exception {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
        when(ordenMock.getCliente()).thenReturn(clienteMock);
        LocalDateTime turno = LocalDateTime.now().minusHours(1);
        when(clienteMock.getTurno()).thenReturn(turno);
        terminal.entregaTerrestreExp(ordenMock, camionMock, choferMock);
        assertTrue(
                terminal.getOrdenes().contains(ordenMock),
                "Error: la orden no quedó registrada en el Set de órdenes de la terminal"
            );
    }

    @Test
    public void testEntregaTerrestreImp() throws Exception {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
        terminal.validarEntregaTerrestreImp(ordenMock, camionMock, choferMock);
        assertTrue(
                terminal.getOrdenes().contains(ordenMock),
                "Error: la orden no quedó registrada en el Set de órdenes de la terminal"
            );
    }

    @Test
    public void testChoferIncorrectoLanzaExcepcion() {
        when(choferMock.getNombre()).thenReturn("Pedro");
        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
        Exception exception = assertThrows(Exception.class, () -> {
            terminal.validarChofer(juan, ordenMock);
        });
        assertEquals("El chofer no coincide", exception.getMessage());
    }

    @Test
    public void testChoferCorrecto() {
        when(choferMock.getNombre()).thenReturn("Pedro");
        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
        assertDoesNotThrow(() -> terminal.validarChofer(choferMock, ordenMock));
    }

    @Test
    public void testCamionIncorrectoLanzaExcepcion() {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        Exception exception = assertThrows(Exception.class, () -> {
            terminal.validarCamion(camionMock2, ordenMock);
        });
        assertEquals("El camión no coincide", exception.getMessage());
    }

    @Test
    public void testCamionCorrecto() {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        assertDoesNotThrow(() -> terminal.validarCamion(camionMock, ordenMock));
    }

    @Test
    public void testValidarHorarioDeEntrega_EntregaATiempo() throws Exception {
        LocalDateTime turnoReciente = LocalDateTime.now().minusHours(2);
		when(clienteMock.getTurno()).thenReturn(turnoReciente);
        when(ordenMock.getCliente()).thenReturn(clienteMock);
        assertDoesNotThrow(() -> terminal.validarHorarioDeEntrega(ordenMock));
    }


    @Test
	public void testTerminalPortuaria()
	{
    	TerminalPortuaria terminalTest = new TerminalPortuaria("Buenos Aires", coordenadaDummy);
		List<LineaNaviera> misNavierasTest = new ArrayList<>();
		Set<Orden> ordenesTest = new HashSet<>();
		assertEquals(terminalTest.getNombre(), "Buenos Aires");
		assertEquals(terminalTest.getMisNavieras(), misNavierasTest);
		assertEquals(terminalTest.getOrdenes(), ordenesTest);
	}

    @Test
    public void testValidarHorarioDeEntregaFueraDeTiempo() {
        // Turno de hace 5 horas → debería fallar
        LocalDateTime turnoTest = LocalDateTime.now().minusHours(5);

        when(clienteMock.getTurno()).thenReturn(turnoTest);
        when(ordenMock.getCliente()).thenReturn(clienteMock);

        Exception ex = assertThrows(Exception.class,
                () -> terminal.validarHorarioDeEntrega(ordenMock));

        assertEquals("Llegaste tarde", ex.getMessage());
    }

    @Test
	public void testSetEstrategia() {
		 terminal.setEstrategia(estrategy);
		 assertSame(terminal.getEstrategia(), estrategy);
	}


//   Test preparado para implementacion del strategy
//   @Test
//	 public void TestgetMejorCircuito() {
//		 verify(strategySpy, times(1)).mejorRuta();
//	 }

    @Test
	public void testGetCoordenadas() {
		// TODO Auto-generated method stub
		assertEquals(terminal.getCoordenadas(), coordenadaDummy);
	 }

    @Test
	public void testRegistrarNuevaOrden()
	{
		terminal.registrarNuevaOrden(ordenTest);
		assertTrue(terminal.getOrdenes().contains(ordenTest));
	}

    @Test
    public void testEnviarFacturaOrden() {
    	//Mock de viajes para test
        Viaje viajeTarget = mock(Viaje.class);
        Viaje otroViaje = mock(Viaje.class);
        //Mock de ordenes para test
        Orden orden1 = mock(Orden.class);
        Orden orden2 = mock(Orden.class);
        Orden ordenQueNoCorresponde = mock(Orden.class);

        //Preparo los return para ordenes
        when(orden1.getViaje()).thenReturn(viajeTarget);
        when(orden2.getViaje()).thenReturn(viajeTarget);
        when(ordenQueNoCorresponde.getViaje()).thenReturn(otroViaje);

        //agrego las ordenes para test
        terminal.getOrdenes().add(orden1);
        terminal.getOrdenes().add(orden2);
        terminal.getOrdenes().add(ordenQueNoCorresponde);

        //Ejecuto Metodo
        terminal.enviarFacturaOrden(viajeTarget);

        //Verifico que se envie correctamente
        verify(orden1, times(1)).enviarFacturaPorMail();
        verify(orden2, times(1)).enviarFacturaPorMail();
        verify(ordenQueNoCorresponde, never()).enviarFacturaPorMail();
    }

    void testVisitorEsLlamadoPorCadaOrden() {
    	OrdenImportacion ordenImportMock = mock(OrdenImportacion.class);
    	OrdenExportacion ordenExportMock = mock(OrdenExportacion.class);
    	
        ReporteVisitor visitorMock = mock(ReporteVisitor.class);
        terminal.aceptar(visitorMock, buqueMock);

        verify(visitorMock, times(1)).visitar(terminal, buqueMock);
        verify(visitorMock, times(1)).visitar(ordenImportMock, buqueMock);
        verify(visitorMock, times(1)).visitar(ordenExportMock, buqueMock);
    }
}
