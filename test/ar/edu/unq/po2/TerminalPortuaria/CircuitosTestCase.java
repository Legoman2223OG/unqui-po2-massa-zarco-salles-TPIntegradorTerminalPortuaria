package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Tramo;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

class CircuitosTestCase {
	// SUT
	private Circuito circuitoTest;
	private LineaNaviera navieraTest;

	// DOC
	private TerminalPortuaria terminal1;
	private TerminalPortuaria terminal2;
	private TerminalPortuaria terminal3;
	private TerminalPortuaria terminal4;
	private Tramo tramo1;
	private Tramo tramo2;
	private Tramo tramo3;
	private Tramo tramo4;
	private ArrayList<Tramo> tramosDeCircuito = new ArrayList<>();

	@BeforeEach
	void setUp() {
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		terminal3 = mock(TerminalPortuaria.class);
		terminal4 = mock(TerminalPortuaria.class);

		tramo1 = new Tramo(terminal1, terminal2, Duration.ZERO, 0.0);
		tramo2 = new Tramo(terminal2, terminal3, Duration.ZERO, 0.0);
		tramo3 = new Tramo(terminal3, terminal4, Duration.ZERO, 0.0);
		tramo4 = new Tramo(terminal4, terminal1, Duration.ZERO, 0.0);
	}

	@Test
	void testTramosOrdenadosCorrectamente() throws Exception {
		// DOC
		when(terminal1.getNombre()).thenReturn("Terminal 1");
		when(terminal2.getNombre()).thenReturn("Terminal 2");
		when(terminal3.getNombre()).thenReturn("Terminal 3");
		when(terminal4.getNombre()).thenReturn("Terminal 4");

		// Exercise
		tramosDeCircuito.add(tramo1);
		tramosDeCircuito.add(tramo4);
		tramosDeCircuito.add(tramo2);
		tramosDeCircuito.add(tramo3);
		circuitoTest = new Circuito(tramosDeCircuito);

		// Verify
		Assertions.assertEquals("Terminal 1", circuitoTest.getTramos().get(0).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 2", circuitoTest.getTramos().get(0).getPuertoDestino().getNombre());
		Assertions.assertEquals("Terminal 2", circuitoTest.getTramos().get(1).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 3", circuitoTest.getTramos().get(1).getPuertoDestino().getNombre());
		Assertions.assertEquals("Terminal 3", circuitoTest.getTramos().get(2).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 4", circuitoTest.getTramos().get(2).getPuertoDestino().getNombre());
		Assertions.assertEquals("Terminal 4", circuitoTest.getTramos().get(3).getPuertoOrigen().getNombre());
		Assertions.assertEquals("Terminal 1", circuitoTest.getTramos().get(3).getPuertoDestino().getNombre());
	}

	@Test
	void testRechazaCircuitoSinCerrar() {
		// Exercise
		tramosDeCircuito.add(tramo1);
		tramosDeCircuito.add(tramo2);
		tramosDeCircuito.add(tramo3);

		// Verify
		Assertions.assertThrows(Exception.class, () -> new Circuito(tramosDeCircuito));
	}

	@Test
	void testRecorridoEntreTramos() throws Exception {
		// Exercise
		tramosDeCircuito.add(tramo1);
		tramosDeCircuito.add(tramo2);
		tramosDeCircuito.add(tramo3);
		tramosDeCircuito.add(tramo4);
		circuitoTest = new Circuito(tramosDeCircuito);

		// Verify
		Assertions.assertEquals(tramo4, circuitoTest.recorridoEntre_Y_(terminal4,terminal3).get(0));
		Assertions.assertEquals(tramo1, circuitoTest.recorridoEntre_Y_(terminal4,terminal3).get(1));
		Assertions.assertEquals(tramo2, circuitoTest.recorridoEntre_Y_(terminal4,terminal3).get(2));
	}

	@Test
	void testTerminalesQueExistenEnCircuito() throws Exception {
		// DOC
		TerminalPortuaria terminal5 = mock(TerminalPortuaria.class);

		// Exercise
		tramosDeCircuito.add(tramo1);
		tramosDeCircuito.add(tramo2);
		tramosDeCircuito.add(tramo3);
		tramosDeCircuito.add(tramo4);
		circuitoTest = new Circuito(tramosDeCircuito);

		// Verify
		Assertions.assertTrue(circuitoTest.terminalExisteEnElCircuito(terminal1));
		Assertions.assertTrue(circuitoTest.terminalExisteEnElCircuito(terminal2));
		Assertions.assertTrue(circuitoTest.terminalExisteEnElCircuito(terminal3));
		Assertions.assertTrue(circuitoTest.terminalExisteEnElCircuito(terminal4));
		Assertions.assertFalse(circuitoTest.terminalExisteEnElCircuito(terminal5));
	}

	@Test
	void testCircuitosQueContienenUnDestino() throws Exception {
		// DOC
		TerminalPortuaria terminal5 = mock(TerminalPortuaria.class);
		TerminalPortuaria terminal6 = mock(TerminalPortuaria.class);
		Tramo tramo5 = new Tramo(terminal1, terminal5, Duration.ZERO, 0.0);
		Tramo tramo6 = new Tramo(terminal5, terminal6, Duration.ZERO, 0.0);
		Tramo tramo7 = new Tramo(terminal6, terminal1, Duration.ZERO, 0.0);
		ArrayList<Tramo> tramosDeSegundoCircuito = new ArrayList<>();
		tramosDeSegundoCircuito.add(tramo5);
		tramosDeSegundoCircuito.add(tramo6);
		tramosDeSegundoCircuito.add(tramo7);
		tramosDeCircuito.add(tramo1);
		tramosDeCircuito.add(tramo2);
		tramosDeCircuito.add(tramo3);
		tramosDeCircuito.add(tramo4);
		circuitoTest = new Circuito(tramosDeCircuito);
		Circuito segundoCircuito = new Circuito(tramosDeSegundoCircuito);

		// Exercise
		LineaNaviera lineaTest = new LineaNaviera(); lineaTest.addCircuito(circuitoTest); lineaTest.addCircuito(segundoCircuito);

		// Verify
		Assertions.assertEquals(segundoCircuito, lineaTest.circuitosQuePasanPor(terminal5).get(0));
	}
}
