package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Circuito;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.LineaNaviera;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Tramo;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_MejorRuta;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_MenorPrecio;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_MenorTiempo;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.E_TerminalesIntermedias;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class EstrategiasTestCase {
	// SUT
	private E_MejorRuta estrategiaTest;

	// DOC
	private TerminalPortuaria terminalOrigenTest;
	private TerminalPortuaria terminalIntermedia1;
	private TerminalPortuaria terminalIntermedia2;
	private TerminalPortuaria terminalIntermedia3;
	private TerminalPortuaria terminalIntermedia4;
	private TerminalPortuaria terminalIntermedia5;
	private TerminalPortuaria terminalDestinoTest;
	private LineaNaviera lineaTest1;
	private LineaNaviera lineaTest2;
	private ArrayList<Tramo> listaTramos1 = new ArrayList<>();
	private ArrayList<Tramo> listaTramos2 = new ArrayList<>();
	private ArrayList<Tramo> listaTramos3 = new ArrayList<>();
	private Tramo tramo1;
	private Tramo tramo2;
	private Tramo tramo3;
	private Tramo tramo4;
	private Tramo tramo5;
	private Tramo tramo6;
	private Tramo tramo7;
	private Tramo tramo8;
	private Tramo tramoFinal;
	private Circuito circuitoTest1;
	private Circuito circuitoTest2;
	private Circuito circuitoTest3;

	@BeforeEach
	void setUp() throws Exception {
		terminalOrigenTest = new TerminalPortuaria("Localidad de Terminal Test", new Coordenada(60,60));
		terminalDestinoTest = new TerminalPortuaria("Localidad de Destino Test", new Coordenada(60,60));

		terminalIntermedia1 = mock(TerminalPortuaria.class);
		terminalIntermedia2 = mock(TerminalPortuaria.class);
		terminalIntermedia3 = mock(TerminalPortuaria.class);
		terminalIntermedia4 = mock(TerminalPortuaria.class);
		terminalIntermedia5 = mock(TerminalPortuaria.class);

		tramoFinal = new Tramo(terminalDestinoTest, terminalOrigenTest, Duration.ZERO,0.0);

		// Circuito de menor tiempo
		tramo1 = new Tramo(terminalOrigenTest,  terminalIntermedia1, Duration.ofMinutes(26), 120.6);
		tramo2 = new Tramo(terminalIntermedia1, terminalIntermedia2, Duration.ofMinutes(12),  48.9);
		tramo3 = new Tramo(terminalIntermedia2, terminalDestinoTest, Duration.ofMinutes(19),  84.3);
		listaTramos1.add(tramo1); listaTramos1.add(tramo2); listaTramos1.add(tramo3); listaTramos1.add(tramoFinal);
		circuitoTest1 = new Circuito(listaTramos1);

		// Circuito de menor precio y menor cantidad de terminales intermedias
		tramo4 = new Tramo(terminalOrigenTest,  terminalIntermedia3, Duration.ofHours(1),    12.2);
		tramo5 = new Tramo(terminalIntermedia3, terminalDestinoTest, Duration.ofMinutes(56), 45.6);
		listaTramos2.add(tramo4); listaTramos2.add(tramo5); listaTramos2.add(tramoFinal);
		circuitoTest2 = new Circuito(listaTramos2);

		tramo6 = new Tramo(terminalOrigenTest,  terminalIntermedia4, Duration.ofMinutes(49), 168.1);
		tramo7 = new Tramo(terminalIntermedia4, terminalIntermedia5, Duration.ofHours(2),    120.6);
		tramo8 = new Tramo(terminalIntermedia5, terminalDestinoTest, Duration.ofMinutes(26), 246.9);
		listaTramos3.add(tramo6); listaTramos3.add(tramo7); listaTramos3.add(tramo8); listaTramos3.add(tramoFinal);
		circuitoTest3 = new Circuito(listaTramos3);

		lineaTest1 = new LineaNaviera(); lineaTest1.addCircuito(circuitoTest1);
		lineaTest2 = new LineaNaviera(); lineaTest2.addCircuito(circuitoTest2); lineaTest2.addCircuito(circuitoTest3);

		terminalOrigenTest.registrarNuevaNaviera(lineaTest1);
		terminalOrigenTest.registrarNuevaNaviera(lineaTest2);
	}

	@Test
	void testEstrategiaMenorTiempo() {
		// Exercise
		estrategiaTest = new E_MenorTiempo();
		terminalOrigenTest.setEstrategia(estrategiaTest);
		
		// Verify
		Assertions.assertEquals(circuitoTest1,terminalOrigenTest.getMejorCircuito(terminalDestinoTest));
	}

	@Test
	void testEstrategiaMenorPrecio() {
		// Exercise
		estrategiaTest = new E_MenorPrecio();
		terminalOrigenTest.setEstrategia(estrategiaTest);
		
		// Verify
		Assertions.assertEquals(circuitoTest2,terminalOrigenTest.getMejorCircuito(terminalDestinoTest));
	}

	@Test
	void testEstrategiaMenorCantidadDeTerminalesIntermedias() {
		// Exercise
		estrategiaTest = new E_TerminalesIntermedias();
		terminalOrigenTest.setEstrategia(estrategiaTest);
		
		// Verify
		Assertions.assertEquals(circuitoTest2,terminalOrigenTest.getMejorCircuito(terminalDestinoTest));
	}
}
