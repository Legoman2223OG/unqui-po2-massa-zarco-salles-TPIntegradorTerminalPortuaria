package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.BuqueStatus;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class TerminalTestCase {


    private TerminalPortuaria terminal;
    private Buque buqueMock;
    private Orden ordenMock;
    private Camion camionMock;
    private Camion camionMock2;
    private Chofer choferMock;
    private Chofer juan;
    private Buque buqueSpy;
    private BuqueStatus statusMock;
    private Cliente clienteMock;
    Coordenada coordenadaDummy = new Coordenada(0, 0);
    Viaje viajeDummy = mock(Viaje.class);

    @BeforeEach
    public void setUp() {
        terminal = new TerminalPortuaria("Puerto Uno");
        buqueMock = mock(Buque.class);
        ordenMock = mock(Orden.class);
        camionMock = mock(Camion.class);
        camionMock2 = mock(Camion.class);
        choferMock = mock(Chofer.class);
        statusMock = mock(BuqueStatus.class);
        clienteMock = mock(Cliente.class);
        Buque buqueReal = new Buque(coordenadaDummy, terminal, viajeDummy);
        buqueReal.setStatus(statusMock);
        buqueSpy = spy(buqueReal);
  
//        clienteMock = mock(Cliente.class);
    }
    
    @Test
    public void testTrabajoCargaYDescarga() throws Exception {
        terminal.trabajoCargaYDescarga(buqueSpy);
        verify(buqueSpy, times(1)).working();
    }
    
//    @Test
//    public void testEntregaTerrestreExp() throws Exception {
//        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
//        when(ordenMock.getChoferAsignado()).thenReturn(choferMock);
//        terminal.entregaTerrestreExp(ordenMock, camionMock, choferMock);
//        verify(ordenMock, times(1)).registrarEntregaContainer();
//    }
    
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
    public void testCamionIncorrectoLanzaExcepcion() {
        when(ordenMock.getCamionAsignado()).thenReturn(camionMock);
        Exception exception = assertThrows(Exception.class, () -> {
            terminal.validarCamion(camionMock2, ordenMock);
        });
        assertEquals("El camión no coincide", exception.getMessage());
    }
    
    @Test
    public void testValidarHorarioDeEntrega_EntregaATiempo() throws Exception {
        LocalDateTime turnoReciente = LocalDateTime.now().minusHours(2);
		when(clienteMock.getTurno()).thenReturn(turnoReciente);
        when(ordenMock.getCliente()).thenReturn(clienteMock);
        assertDoesNotThrow(() -> terminal.validarHorarioDeEntrega(ordenMock));
    }
    
    
    
    
    
}
