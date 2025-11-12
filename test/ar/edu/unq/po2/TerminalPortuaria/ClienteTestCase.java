package ar.edu.unq.po2.TerminalPortuaria;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Consignee;
import ar.edu.unq.po2.TerminalPortuaria.Cliente.Shipper;
import ar.edu.unq.po2.TerminalPortuaria.Factura.Factura;

public class ClienteTestCase {

	
	private Factura fact;
	private Shipper shipper;
	private Consignee consignee;
	
	
	@BeforeEach
	public void setUp() {
		
		
		shipper = new Shipper("Pato", 30);
		consignee = new Consignee ("Luis", 29);
		fact = mock(Factura.class);
	}
	
	@Test
	public void testShipper() {
		assertTrue(shipper.isShipper());
		assertFalse(shipper.isConsignee());
	}
	
	@Test
	public void testConsignee() {
		assertFalse(consignee.isShipper());
		assertTrue(consignee.isConsignee());
	}
	
    @Test
    public void testRecibirAvisoDevuelveNullPorAhora() {
    	String avisoT = "avisoTest";
    	assertEquals(shipper.recibirAviso(avisoT),avisoT);
    	assertEquals(consignee.recibirAviso(avisoT),avisoT);
    }
    
    @Test
    public void testRecibirFacturaDevuelveToStringDeFactura() {
        when(fact.toString()).thenReturn("MensajeTest");
        String mTest = "MensajeTest";
        assertEquals(shipper.recibirFactura(fact), mTest);
        assertEquals(consignee.recibirFactura(fact), mTest);
    }

    @Test
    public void testGettersYSetters() {

        assertEquals("Pato", shipper.getNombre());
        assertEquals(30, shipper.getCodigoCliente());
        assertEquals("Luis", consignee.getNombre());
        assertEquals(29, consignee.getCodigoCliente());
    }
}
