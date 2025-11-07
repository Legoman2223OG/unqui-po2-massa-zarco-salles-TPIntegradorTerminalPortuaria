package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

class NavierasYCircuitosTestCase {

	Circuito circuitoTest;
	
	TerminalPortuaria terminal1;
	TerminalPortuaria terminal2;
	TerminalPortuaria terminal3;
	
	Tramo tramo1;
	Tramo tramo2;
	Tramo tramo3;
	ArrayList<Tramo> tramosDeCircuito = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		terminal3 = mock(TerminalPortuaria.class);
		
		when(terminal1.getNombre()).thenReturn("Terminal 1");
		when(terminal2.getNombre()).thenReturn("Terminal 2");
		when(terminal3.getNombre()).thenReturn("Terminal 3");
		
		tramo1 = new Tramo(terminal1, terminal3, Duration.ofHours(1), 68.0);
		tramo2 = new Tramo(terminal2, terminal1, Duration.ofMinutes(56), 90.0);
		tramo3 = new Tramo(terminal3, terminal2, Duration.ofMinutes(35), 24.0);
		
		tramosDeCircuito.add(tramo1); tramosDeCircuito.add(tramo2); tramosDeCircuito.add(tramo3);
		circuitoTest = new Circuito(tramosDeCircuito);
	}
	
	@Test
	void testTramosOrdenadosCorrectamente() {
		// Testea que los tramos dentro de circuitoTest fueron ordenados correctamente.
		Assertions.assertEquals("Terminal 1", circuitoTest.getTramos().get(0).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 3", circuitoTest.getTramos().get(0).getPuertoDestino().getNombre());
		
		Assertions.assertEquals("Terminal 3", circuitoTest.getTramos().get(1).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 2", circuitoTest.getTramos().get(1).getPuertoDestino().getNombre());
		
		Assertions.assertEquals("Terminal 2", circuitoTest.getTramos().get(2).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 1", circuitoTest.getTramos().get(2).getPuertoDestino().getNombre());
	}

}
