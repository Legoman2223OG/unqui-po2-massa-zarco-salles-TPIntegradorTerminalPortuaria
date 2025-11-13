package ar.edu.unq.po2.TerminalPortuaria;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import ar.edu.unq.po2.TerminalPortuaria.Factura.Concepto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class ConceptoTestCase {

    @Test
    public void testConstructorYGetters() {
        LocalDateTime fecha = LocalDateTime.of(2025, 11, 9, 10, 0);
        Concepto concepto = new Concepto("Boca", fecha, 2.0);

        assertEquals("Boca", concepto.getNombre());
        assertEquals(fecha, concepto.getFecha());
        assertEquals(2.0, concepto.getMonto(), 0.001);
    }

    @Test
    public void testToStringIncluyeTodosLosDatos() {
        LocalDateTime fecha = LocalDateTime.of(2025, 11, 9, 10, 0);
        Concepto concepto = new Concepto("Servicio de partido Boca", fecha, 2.0);

        String resultado = concepto.toString();

        assertTrue(resultado.contains("Servicio de partido Boca"));
        assertTrue(resultado.contains("fecha: " + fecha.toString()));
        assertTrue(resultado.contains("monto: 2.0"));
    }
}
