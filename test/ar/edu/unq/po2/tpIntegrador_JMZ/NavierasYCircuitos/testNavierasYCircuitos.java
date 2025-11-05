package ar.edu.unq.po2.tpIntegrador_JMZ.NavierasYCircuitos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Tramo;

class testNavierasYCircuitos {

	Circuito circuitoTest;
	Tramo tramo1;
	Tramo tramo2;
	Tramo tramo3;
	ArrayList<Tramo> tramosDeCircuito = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		tramo1 = new Tramo("localidad1", "localidad3", Duration.ofHours(1), 68.0);
		tramo2 = new Tramo("localidad2", "localidad1", Duration.ofMinutes(56), 90.0);
		tramo3 = new Tramo("localidad3", "localidad2", Duration.ofMinutes(35), 24.0);
		
		tramosDeCircuito.add(tramo1); tramosDeCircuito.add(tramo2); tramosDeCircuito.add(tramo3);
		circuitoTest = new Circuito(tramosDeCircuito);
	}
	
	@Test
	void testTramosOrdenadosCorrectamente() {
		// Testea que los tramos dentro de circuitoTest fueron ordenados correctamente.
		Assertions.assertEquals("localidad1", circuitoTest.getTramos().get(0).getPuertoOrigen());
		Assertions.assertEquals("localidad3", circuitoTest.getTramos().get(0).getPuertoDestino());
		
		Assertions.assertEquals("localidad3", circuitoTest.getTramos().get(1).getPuertoOrigen());
		Assertions.assertEquals("localidad2", circuitoTest.getTramos().get(1).getPuertoDestino());
		
		Assertions.assertEquals("localidad2", circuitoTest.getTramos().get(2).getPuertoOrigen());
		Assertions.assertEquals("localidad1", circuitoTest.getTramos().get(2).getPuertoDestino());
	}

}
