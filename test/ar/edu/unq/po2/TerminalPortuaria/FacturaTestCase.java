package ar.edu.unq.po2.TerminalPortuaria;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Orden.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Set;

public class FacturaTestCase {

	Orden orden1;
	Factura factura;
	Viaje viaje1;
	Servicio servicio;
	
	@BeforeEach
    public void setUp() throws Exception {
		orden1 = mock(Orden.class);
		viaje1 = mock(Viaje.class);
		servicio = mock(Servicio.class);
		
	}
	
	@Test
	public void testFacturaCreaConceptoPrincipalConCostoTotal() throws Exception {
	    when(orden1.calcularCostoTotal()).thenReturn(5000.0);
	    when(orden1.getServicios()).thenReturn(Set.of());
	    when(orden1.esOrdenImportacion()).thenReturn(false);
	    when(orden1.getNumFactura()).thenReturn(123);

	    Factura factura = new Factura(orden1);

	    boolean contieneMontoTotal = factura.getConceptos().stream()
	        .anyMatch(c -> c.getNombre().equals("Monto total a pagar:") && c.getMonto() == 5000.0);
	    Assertions.assertTrue(contieneMontoTotal);
	}
	
	@Test
    public void testFacturaAgregaCostoCircuitoSiEsImportacion() throws Exception {
        // Arrange
        LocalDateTime turno = LocalDateTime.of(2025, 11, 12, 10, 0);
        when(orden1.calcularCostoTotal()).thenReturn(6000.0);
        when(orden1.esOrdenImportacion()).thenReturn(true);
        when(orden1.getTurno()).thenReturn(turno);
        when(orden1.getViaje()).thenReturn(viaje1);
        when(viaje1.precioTotal()).thenReturn(1500.0);
        when(orden1.getServicios()).thenReturn(Set.of());
        when(orden1.getNumFactura()).thenReturn(123);

        // Act
        Factura factura = new Factura(orden1);

        // Assert
        boolean contieneCircuito = factura.getConceptos().stream()
            .anyMatch(c -> c.getNombre().equals("Monto total del circuito") && c.getMonto() == 1500.0);
        assertTrue(contieneCircuito);
    }

    @Test
    public void testFacturaAgregaConceptosDeServicios() throws Exception {
        // Arrange
        LocalDateTime turno = LocalDateTime.of(2025, 11, 12, 10, 0);
        when(orden1.calcularCostoTotal()).thenReturn(8000.0);
        when(orden1.esOrdenImportacion()).thenReturn(false);
        when(orden1.getTurno()).thenReturn(turno);
        when(orden1.getServicios()).thenReturn(Set.of(servicio));
        when(servicio.calcularPrecio()).thenReturn(300.0);
        when(servicio.toString()).thenReturn("Servicio Extra");
        when(orden1.getNumFactura()).thenReturn(123);

        // Act
        Factura factura = new Factura(orden1);

        // Assert
        boolean contieneServicio = factura.getConceptos().stream()
            .anyMatch(c -> c.getNombre().equals("Servicio Extra") && c.getMonto() == 300.0);
        assertTrue(contieneServicio);
    }

    @Test
    public void testFacturaToStringIncluyeNumFacturaYConceptos() throws Exception {
        // Arrange
        when(orden1.calcularCostoTotal()).thenReturn(5000.0);
        when(orden1.getServicios()).thenReturn(Set.of());
        when(orden1.esOrdenImportacion()).thenReturn(false);
        when(orden1.getNumFactura()).thenReturn(123);

        // Act
        Factura factura = new Factura(orden1);
        String texto = factura.toString();

        // Assert
        assertTrue(texto.contains("123"));
        assertTrue(texto.contains("Monto total a pagar:"));
    }
	
	
}
