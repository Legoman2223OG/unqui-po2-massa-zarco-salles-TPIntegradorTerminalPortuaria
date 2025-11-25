package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.*;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.*;
import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Orden.*;
import ar.edu.unq.po2.TerminalPortuaria.Reportes.ReporteVisitor;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrdenTestCase {

    private OrdenExportacion ordenExp;
    private OrdenImportacion ordenImp;
    private Viaje viajeMock;
    private Container container;
    private Tramo tramoMock;
    private Cliente clienteMock;
    private Servicio servicioMock;
    private Servicio servicioTestAgregar;
    private TransporteAsignado transporteMock;
    private Chofer chofer;
    private Camion camion;

    LocalDateTime fechaTurno;
    LocalDateTime fechaC;

    @BeforeEach
    public void setUp() throws Exception {
        fechaTurno = LocalDateTime.of(2025, 11, 12, 10, 0);
        fechaC = LocalDateTime.of(1999, 7, 5, 10, 0);

        // Mocks
        viajeMock = mock(Viaje.class);
        tramoMock = mock(Tramo.class);
        camion = mock(Camion.class);
        chofer = mock(Chofer.class);
        clienteMock = mock(Cliente.class);
        servicioMock = mock(Servicio.class);
        transporteMock = mock(TransporteAsignado.class);
        container = mock(Container.class);
        servicioTestAgregar = mock(Servicio.class);
        when(transporteMock.getChoferAsignado()).thenReturn(chofer);
        when(transporteMock.getCamionAsignado()).thenReturn(camion);
        when(servicioMock.getContainer()).thenReturn(container);
        when(servicioMock.calcularPrecio()).thenReturn(500.0);
        when(viajeMock.precioTotal()).thenReturn(1500.0);


        // Para que generarFactura no falle
        when(clienteMock.recibirFactura(any(Factura.class))).thenReturn(null);

     // Instancia de OrdenExportacion
        ordenExp = new OrdenExportacion( clienteMock, viajeMock, container, transporteMock, fechaTurno, 101);
        ordenExp.getServicios().add(servicioMock);

        // Instancia de OrdenImportacion
        ordenImp = new OrdenImportacion(  clienteMock, viajeMock, container, transporteMock, fechaTurno, 101);
        ordenImp.getServicios().add(servicioMock);
    }

    @Test
    public void testOrdenExportacionCostoTotal() {
        // Por defecto OrdenExportacion solo suma costoServicios()
        double total = ordenExp.calcularCostoTotal();
        assertEquals(500.0, total, 0.001);
    }

    @Test
    public void testOrdenImportacionCostoTotal() {
        // OrdenImportacion suma costoServicios + precioTramos
        double total = ordenImp.calcularCostoTotal();
        // 500 (servicio) + 1500 (tramo)
        assertEquals(2000.0, total, 0.001);
    }
    
    @Test
    public void testCostoServiciosConExcepcion() throws Exception {
    	ordenExp.getServicios().remove(servicioMock);
        Servicio servicioException = mock(Servicio.class);
        when(servicioException.calcularPrecio()).thenThrow(new Exception("Error al calcular precio"));
        ordenExp.getServicios().add(servicioException);
        double total = ordenExp.costoServicios();
        assertEquals(0.0, total, 0.001);
    }
    
    @Test
    public void testGettersOrden() {

        // Verificamos getters de OrdenExportacion
        assertEquals(clienteMock, ordenExp.getCliente());
        assertEquals(viajeMock, ordenExp.getViaje());
        assertEquals(chofer, ordenExp.getChoferAsignado());
        assertEquals(camion, ordenExp.getCamionAsignado());
        assertEquals(container, ordenExp.getContainerDeOrden());
        assertEquals(101, ordenExp.getNumFactura());
        assertTrue(ordenExp.esOrdenExportacion());
        assertFalse(ordenExp.esOrdenImportacion());

        // Verificamos getters de OrdenImportacion (igual, solo que otro objeto)
        assertEquals(clienteMock, ordenImp.getCliente());
        assertEquals(viajeMock, ordenImp.getViaje());
        assertEquals(chofer, ordenImp.getChoferAsignado());
        assertEquals(camion, ordenImp.getCamionAsignado());
        assertEquals(container, ordenImp.getContainerDeOrden());
        assertEquals(fechaTurno, ordenImp.getTurno());
        assertEquals(101, ordenImp.getNumFactura());
        assertTrue(ordenImp.esOrdenImportacion());
        assertFalse(ordenImp.esOrdenExportacion());
    }
    
    @Test
    public void testAgregarServicio() {
    	
    	assertFalse(ordenImp.getServicios().contains(servicioTestAgregar));
    	assertFalse(ordenExp.getServicios().contains(servicioTestAgregar));
    	ordenImp.agregarServicio(servicioTestAgregar);
    	ordenExp.agregarServicio(servicioTestAgregar);
    	assertTrue(ordenImp.getServicios().contains(servicioTestAgregar));
    	assertTrue(ordenExp.getServicios().contains(servicioTestAgregar));
    }
    
    @Test
    public void testAceptarLlamaAlVisitorYAlContainerImp() {
        ReporteVisitor visitor = mock(ReporteVisitor.class);
        Buque buque = mock(Buque.class);

        ordenImp.aceptar(visitor, buque);
        verify(visitor).visitarOrden(ordenImp, buque);
        verify(container).aceptar(visitor, buque);
    }
    
    @Test
    public void testAceptarLlamaAlVisitorYAlContainerExp() {
        ReporteVisitor visitor = mock(ReporteVisitor.class);
        Buque buque = mock(Buque.class);

        ordenExp.aceptar(visitor, buque);
        verify(visitor).visitarOrden(ordenExp, buque);
        verify(container).aceptar(visitor, buque);
    }

    @Test
    public void testGenerarFactura() throws Exception {
        Factura factura = ordenExp.generarFactura(ordenExp);
        assertNotNull(factura);
        assertTrue(factura.getConceptos().stream()
                .anyMatch(c -> c.getMonto() == ordenExp.calcularCostoTotal()));
    }

    @Test
    public void testEnviarFacturaPorMail() throws Exception {
        // Solo verificamos que se llama recibirFactura
        ordenExp.enviarFacturaPorMail();
        verify(clienteMock, times(1)).recibirFactura(any(Factura.class));
    }
}