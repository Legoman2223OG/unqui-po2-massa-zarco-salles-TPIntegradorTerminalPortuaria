package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
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
import ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima.Busqueda;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenExportacion;
import ar.edu.unq.po2.TerminalPortuaria.Orden.OrdenImportacion;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ElementoVisitable;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Pesado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Servicio;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_MejorRuta;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class TerminalTestCase {


    private TerminalPortuaria terminal;
    private Buque buqueMock;
    private Orden ordenMock;
    private Orden ordenTest;
    private OrdenImportacion ordenImportMock;
    private OrdenExportacion ordenExportMock;
    private Camion camionMock;
    private Camion camionMock2;
    private Chofer choferMock;
    private Chofer juan;
    private Buque buqueSpy;
    private E_MejorRuta strategySpy;
    private BuqueStatus statusMock;
    private Cliente clienteMock;
    private Cliente clienteMockS;
    private Cliente clienteMockC;
    private E_MejorRuta estrategy;
    private LineaNaviera naviera;
    private LineaNaviera naviera1;
    private Circuito circuito;
    private Circuito circuito1;
    Coordenada coordenadaDummy = new Coordenada(0, 0);
    Viaje viajeDummy = mock(Viaje.class);
    private Viaje viaje1;
    private Viaje viaje2;

    @BeforeEach
    public void setUp() {
        terminal = new TerminalPortuaria("Puerto Uno", coordenadaDummy);
        buqueMock = mock(Buque.class);
        ordenMock = mock(Orden.class);
        ordenTest = mock(Orden.class);
        ordenImportMock = mock(OrdenImportacion.class);
        ordenExportMock = mock(OrdenExportacion.class);
        camionMock = mock(Camion.class);
        camionMock2 = mock(Camion.class);
        choferMock = mock(Chofer.class);
        statusMock = mock(BuqueStatus.class);
        clienteMock = mock(Cliente.class);
        estrategy = mock(E_MejorRuta.class);
        naviera = mock(LineaNaviera.class);
        naviera1 = mock(LineaNaviera.class);
        clienteMockS = mock(Cliente.class);
        clienteMockC = mock(Cliente.class);
//        strategySpy = spy(strategyReal);
        terminal.setEstrategia(estrategy);
        Buque buqueReal = new Buque("Test",coordenadaDummy, viajeDummy);
        buqueReal.setStatus(statusMock);
        buqueSpy = spy(buqueReal);
        viaje1 = mock(Viaje.class);
        viaje2 = mock(Viaje.class);
        circuito = mock(Circuito.class);
        circuito1= mock(Circuito.class);
    	when(ordenImportMock.esOrdenImportacion()).thenReturn(true);
        when(ordenImportMock.getViaje()).thenReturn(viaje1);
        when(ordenImportMock.getCliente()).thenReturn(clienteMockC);
        when(ordenExportMock.esOrdenExportacion()).thenReturn(true);
        when(ordenExportMock.getViaje()).thenReturn(viaje2);
        when(ordenExportMock.getCliente()).thenReturn(clienteMockS);

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
        LocalDateTime turno = LocalDateTime.now().minusHours(1);
        when(ordenMock.getTurno()).thenReturn(turno);
        terminal.exportar(ordenMock, camionMock, choferMock);
        assertTrue(
                terminal.getOrdenes().contains(ordenMock),
                "Error: la orden no quedó registrada en el Set de órdenes de la terminal"
            );
    }

    @Test
    public void testEntregaTerrestreImp() throws Exception {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
        terminal.importar(ordenMock, camionMock, choferMock);
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
    public void testBuscarConMock() {
        TerminalPortuaria terminal = spy(new TerminalPortuaria(null, coordenadaDummy));
        List<Viaje> viajesPrueba = new ArrayList<>();
        viajesPrueba.add(viaje1);
        viajesPrueba.add(viaje2);
        doReturn(viajesPrueba).when(terminal).getMisViajes();
        Busqueda filtro = mock(Busqueda.class);
        List<Viaje> resultadoEsperado = mock(List.class);
        when(filtro.filtrar(viajesPrueba)).thenReturn(resultadoEsperado);
        List<Viaje> resultado = terminal.buscar(filtro);
        assertEquals(resultadoEsperado, resultado);
        verify(filtro).filtrar(viajesPrueba); // ✔️ acá estaba el error
    }
    
    @Test
    public void testGenerarReporteDeBuqueEnTerminal() {

        Buque buque = mock(Buque.class);
        ReporteVisitor visitor = mock(ReporteVisitor.class);
        when(visitor.generarReporte()).thenReturn("Reporte generado");
        TerminalPortuaria terminal = spy(new TerminalPortuaria("BsAs", mock(Coordenada.class)));
        String resultado = terminal.generarReporte(visitor, buque);
        assertEquals("Reporte generado", resultado);
        verify(terminal).aceptar(visitor, buque);
        verify(visitor).visitarTerminal(terminal, buque);
        verify(visitor).generarReporte();
    }

    @Test
    public void testValidarHorarioDeEntrega_EntregaATiempo() throws Exception {
        LocalDateTime turnoReciente = LocalDateTime.now().minusHours(2);
		when(ordenMock.getTurno()).thenReturn(turnoReciente);
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

        LocalDateTime turnoTest = LocalDateTime.now().minusHours(5);

        when(ordenMock.getTurno()).thenReturn(turnoTest);

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
		assertEquals(terminal.getCoordenadas(), coordenadaDummy);
	 }

    @Test
	public void testRegistrarNuevaOrden()
	{
		terminal.registrarNuevaOrden(ordenTest);
		assertTrue(terminal.getOrdenes().contains(ordenTest));
	}

    @Test
    public void testEnviarFacturaOrden() throws Exception {

        Viaje viajeTarget = mock(Viaje.class);
        Viaje otroViaje = mock(Viaje.class);

        Orden orden1 = mock(Orden.class);
        Orden orden2 = mock(Orden.class);
        Orden ordenQueNoCorresponde = mock(Orden.class);

        when(orden1.getViaje()).thenReturn(viajeTarget);
        when(orden2.getViaje()).thenReturn(viajeTarget);
        when(ordenQueNoCorresponde.getViaje()).thenReturn(otroViaje);

        terminal.getOrdenes().add(orden1);
        terminal.getOrdenes().add(orden2);
        terminal.getOrdenes().add(ordenQueNoCorresponde);

        terminal.enviarFacturaOrden(viajeTarget);

        verify(orden1, times(1)).enviarFacturaPorMail();
        verify(orden2, times(1)).enviarFacturaPorMail();
        verify(ordenQueNoCorresponde, never()).enviarFacturaPorMail();
    }

//    void testVisitorEsLlamadoPorCadaOrden() {
//        ReporteVisitor visitorMock = mock(ReporteVisitor.class);
//        terminal.aceptar(visitorMock, buqueMock);
//
//        verify(visitorMock, times(1)).visitar(terminal, buqueMock);
//        verify(visitorMock, times(1)).visitar(ordenImportMock, buqueMock);
//        verify(visitorMock, times(1)).visitar(ordenExportMock, buqueMock);
//    }
    
    @Test
    public void testGetViajes() {
    	TerminalPortuaria terminalT = spy(new TerminalPortuaria(null, coordenadaDummy));
        List<Viaje> viajesPrueba = new ArrayList<>();
        viajesPrueba.add(viaje1);
        viajesPrueba.add(viaje2);

        when(naviera.getViajes()).thenReturn(viajesPrueba);
        doReturn(true).when(terminalT).estoyEnUnCircuitoDeLaNaviera(naviera);

        terminalT.registrarNuevaNaviera(naviera);

        when(viaje1.validarSiTerminalExisteEnViaje(terminalT)).thenReturn(true);
        when(viaje2.validarSiTerminalExisteEnViaje(terminalT)).thenReturn(false);

        List<Viaje> viajesTest = terminalT.getMisViajes();

        assertEquals(1, viajesTest.size());
        assertTrue(viajesTest.contains(viaje1));
        assertFalse(viajesTest.contains(viaje2));
    }
    
    @Test
    public void testMejorCircuito() {
    	
    	when(estrategy.mejorCircuitoHacia(terminal, terminal)).thenReturn(circuito);
    	Circuito circuitoT = terminal.getMejorCircuito(terminal);
    	assertEquals(circuito, circuitoT);
    }
    
    @Test
    public void testDarAvisoShippers() {
    	terminal.registrarNuevaOrden(ordenExportMock);
    	terminal.darAvisoShippers(viaje2);
    	verify(clienteMockS, times(1)).recibirAviso("Su carga ha salido de la terminal");
    	
    }
    
    @Test
    public void testDarAvisoConsignee() {
    	terminal.registrarNuevaOrden(ordenImportMock);
    	terminal.darAvisoConsignees(viaje1);
    	verify(clienteMockC, times(1)).recibirAviso("Su carga está llegando");
    	
    }
    
    @Test
    public void testRegistrarNuevaNavieraConYSinCircuito() {

        when(naviera.getCircuitos()).thenReturn(List.of(circuito));
        when(circuito.terminalExisteEnElCircuito(terminal)).thenReturn(true);
        
        terminal.registrarNuevaNaviera(naviera);
        
        assertTrue(terminal.getMisNavieras().contains(naviera));
        
        when(naviera1.getCircuitos()).thenReturn(List.of(circuito1));
        when(circuito1.terminalExisteEnElCircuito(terminal)).thenReturn(false);
        
        terminal.registrarNuevaNaviera(naviera1);
        assertFalse(terminal.getMisNavieras().contains(naviera1));
    }
    
    @Test
    public void testProximaSalidaHaciaFechaMasProxima() {
        TerminalPortuaria destino = mock(TerminalPortuaria.class);

        when(viaje1.getPuertoInicio()).thenReturn(terminal);
        when(viaje1.puertoDeLlegada()).thenReturn(destino);
        when(viaje1.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 10, 10, 0));

        when(viaje2.getPuertoInicio()).thenReturn(terminal);
        when(viaje2.puertoDeLlegada()).thenReturn(destino);
        when(viaje2.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 8, 10, 0));

        when(naviera.getViajes()).thenReturn(List.of(viaje1, viaje2));
        terminal.getMisNavieras().add(naviera);

        LocalDateTime fecha = terminal.proximaSalidaHacia(destino);

        assertEquals(LocalDateTime.of(2025, 11, 8, 10, 0), fecha);
        when(naviera1.getViajes()).thenReturn(List.of());
        terminal.getMisNavieras().remove(naviera);
        terminal.getMisNavieras().add(naviera1);
        assertNull(terminal.proximaSalidaHacia(destino));
    }
    
    @Test
    public void testEstoyEnUnCircuitoDeLaNaviera() {
        when(naviera.getCircuitos()).thenReturn(List.of(circuito));
        when(circuito.terminalExisteEnElCircuito(terminal)).thenReturn(true);

        assertTrue(terminal.estoyEnUnCircuitoDeLaNaviera(naviera));
    }
    
    @Test
    public void testPartiendoAViaje() {
        TerminalPortuaria terminalSpy = spy(terminal);

        terminalSpy.partiendoAViaje(viaje1);

        verify(terminalSpy, times(1)).darAvisoShippers(viaje1);
        
    }
    
    @Test
    public void testProximoAArribaConsignees() {
        TerminalPortuaria terminalSpy = spy(terminal);

        terminalSpy.proximoAArribar(viaje1);

        verify(terminalSpy, times(1)).darAvisoConsignees(viaje1);
    }
    
    @Test
    public void testFechaSalidaBuque() {
        LocalDateTime fechaEsperada = LocalDateTime.of(2025, 11, 15, 12, 0);

        when(buqueMock.getViaje()).thenReturn(viaje1);
        when(viaje1.getFechaSalida()).thenReturn(fechaEsperada);

        LocalDateTime resultado = terminal.fechaSalidaBuque(buqueMock);

        assertEquals(fechaEsperada, resultado);
    }
    
    @Test
    public void testAceptarLlamaVisitarYOrdenesAceptar() throws Exception {
    	DryContainer container1 = new DryContainer(1, 2, 3, null);
        TankContainer container2 = new TankContainer(3, 4, 5, "juan0987654", null);
        Orden ordenImportTest = new OrdenImportacion(clienteMock, viaje1, container1, null, null, 0);
        Orden ordenExportTest = new OrdenExportacion(clienteMock, viaje2, container2, null, null, 0);
        Servicio servicio1 = new Pesado(container1, 0);
        Servicio servicio2 = new Pesado(container2, 0);
        
        ordenImportTest.agregarServicio(servicio1);
        ordenExportTest.agregarServicio(servicio2);
    	
        ReporteVisitor visitorMock = mock(ReporteVisitor.class);

        terminal.registrarNuevaOrden(ordenExportMock);;
        terminal.registrarNuevaOrden(ordenImportMock);;

        terminal.aceptar(visitorMock, buqueMock);
        
        verify(visitorMock, times(1)).visitarTerminal(terminal, buqueMock);
        verify(ordenImportMock, times(1)).aceptar(visitorMock, buqueMock);
        verify(ordenExportMock, times(1)).aceptar(visitorMock, buqueMock);
    }
    
    
}
