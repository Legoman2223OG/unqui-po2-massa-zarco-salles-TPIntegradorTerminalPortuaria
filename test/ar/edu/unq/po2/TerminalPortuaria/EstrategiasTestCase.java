package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.*;

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
	private ArrayList<Tramo> listaTramos1;
	private ArrayList<Tramo> listaTramos2;
	private ArrayList<Tramo> listaTramos3;
	private Tramo tramo1;
	private Tramo tramo2;
	private Tramo tramo3;
	private Tramo tramo4;
	private Tramo tramo5;
	private Tramo tramo6;
	private Tramo tramo7;
	private Tramo tramo8;
	private Circuito circuitoTest1;
	private Circuito circuitoTest2;
	private Circuito circuitoTest3;

	@BeforeEach
	void setUp() throws Exception {
		terminalOrigenTest = new TerminalPortuaria("Localidad de Terminal Test");
		terminalOrigenTest.a
		
		terminalDestinoTest = mock(TerminalPortuaria.class);
		terminalIntermedia1 = mock(TerminalPortuaria.class);
		terminalIntermedia2 = mock(TerminalPortuaria.class);
		terminalIntermedia3 = mock(TerminalPortuaria.class);
		terminalIntermedia4 = mock(TerminalPortuaria.class);
		terminalIntermedia5 = mock(TerminalPortuaria.class);
		
		tramo1 = new Tramo(terminalOrigenTest,  terminalIntermedia1, Duration.ofMinutes(26), 120.6);
		tramo2 = new Tramo(terminalIntermedia1, terminalIntermedia2, Duration.ofMinutes(12),  48.9);
		tramo3 = new Tramo(terminalIntermedia2, terminalDestinoTest, Duration.ofMinutes(19),  84.3);
		listaTramos1.add(tramo1); listaTramos1.add(tramo2); listaTramos1.add(tramo3);
		circuitoTest1 = new Circuito(listaTramos1);
		
		tramo4 = new Tramo(terminalOrigenTest,  terminalIntermedia3, Duration.ofHours(1),    12.2);
		tramo5 = new Tramo(terminalIntermedia3, terminalDestinoTest, Duration.ofMinutes(56), 45.6);
		listaTramos2.add(tramo4); listaTramos2.add(tramo5);
		circuitoTest2 = new Circuito(listaTramos2);
		
		tramo6 = new Tramo(terminalOrigenTest,  terminalIntermedia4, Duration.ofMinutes(49), 168.1);
		tramo7 = new Tramo(terminalIntermedia4, terminalIntermedia5, Duration.ofHours(2),    120.6);
		tramo8 = new Tramo(terminalIntermedia5, terminalDestinoTest, Duration.ofMinutes(26), 246.9);
		listaTramos3.add(tramo6); listaTramos3.add(tramo7); listaTramos3.add(tramo8);
		circuitoTest3 = new Circuito(listaTramos3);
		
		lineaTest1 = new LineaNaviera(); lineaTest1.addCircuito(circuitoTest1);
		lineaTest2 = new LineaNaviera(); lineaTest2.addCircuito(circuitoTest2); lineaTest2.addCircuito(circuitoTest3);
	}
	
	@Test
	void testEstrategiaMenorTiempo() {
		// Exercise
		estrategiaTest = new E_MenorTiempo(terminalOrigenTest);
		terminalOrigenTest.setMejorCircuito(estrategiaTest);
		
		
	}

}
