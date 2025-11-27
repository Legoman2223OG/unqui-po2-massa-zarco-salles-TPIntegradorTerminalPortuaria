package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class LineaNavieraTestCase {
	// SUT
	LineaNaviera navieraTest;
	TerminalPortuaria terminalGestionadaTest;
	
	// DOC
	Circuito circuitoTest1;
	ArrayList<Tramo> listaTramos1 = new ArrayList<>();
	Circuito circuitoTest2;
	ArrayList<Tramo> listaTramos2 = new ArrayList<>();
	TerminalPortuaria terminalIntermedia1;
	TerminalPortuaria terminalIntermedia2;
	TerminalPortuaria terminalIntermedia3;
	Tramo tramo1;
	Tramo tramo2;
	Tramo tramo3;
	Tramo tramo4;
	Tramo tramo5;
	Buque buqueMock1;
	Buque buqueMock2;
	
	@BeforeEach
	void setUp() throws Exception {
		navieraTest = new LineaNaviera();
		terminalGestionadaTest = new TerminalPortuaria("Terminal Testeo", new Coordenada(0,0));
		
		buqueMock1 = mock(Buque.class);
		buqueMock2 = mock(Buque.class);
		
		terminalIntermedia1 = mock(TerminalPortuaria.class);
		terminalIntermedia2 = mock(TerminalPortuaria.class);
		terminalIntermedia3 = mock(TerminalPortuaria.class);
		
		tramo1 = new Tramo(terminalGestionadaTest, terminalIntermedia1, Duration.ZERO, 0.0);
		tramo2 = new Tramo(terminalIntermedia1, terminalIntermedia2, Duration.ZERO, 0.0);
		tramo3 = new Tramo(terminalIntermedia2, terminalGestionadaTest, Duration.ZERO, 0.0);
		listaTramos1.add(tramo1); listaTramos1.add(tramo2); listaTramos1.add(tramo3);
		circuitoTest1 = new Circuito(listaTramos1);
		
		tramo4 = new Tramo(terminalIntermedia3, terminalIntermedia2, Duration.ZERO, 0.0);
		tramo5 = new Tramo(terminalIntermedia2, terminalIntermedia3, Duration.ZERO, 0.0);
		listaTramos2.add(tramo4); listaTramos2.add(tramo5);
		circuitoTest2 = new Circuito(listaTramos2);
		
		navieraTest.addCircuito(circuitoTest1); navieraTest.addCircuito(circuitoTest2); navieraTest.addBuque(buqueMock1);
	}
	
	@Test
	void testCircuitosQueContienenUnDestino() {
		Assertions.assertTrue(navieraTest.circuitosQuePasanPor(terminalGestionadaTest).contains(circuitoTest1));
		Assertions.assertFalse(navieraTest.circuitosQuePasanPor(terminalGestionadaTest).contains(circuitoTest2));
	}
	
	@Test
	void testNuevosRecorridos() throws Exception {		
		// Exercise
		ArrayList<Viaje> listaViajes = new ArrayList<>();
		listaViajes.add(circuitoTest1.crearNuevoViaje(terminalGestionadaTest, terminalIntermedia2, LocalDateTime.now()));
		listaViajes.add(circuitoTest2.crearNuevoViaje(terminalIntermedia2, terminalIntermedia3, LocalDateTime.now()));
		navieraTest.registrarNuevoRecorrido(buqueMock1, listaViajes);
		
		// Verify
		Assertions.assertThrows(Exception.class, () -> navieraTest.registrarNuevoRecorrido(buqueMock2, listaViajes));
		Assertions.assertFalse(navieraTest.buqueExisteEnEstaNaviera(buqueMock2));
		Assertions.assertEquals(1, navieraTest.getListaRecorridos().size());
	}
	
	@Test
	void testBuscarBuquePorViaje() throws Exception {
		// Exercise
		Viaje viaje1 = circuitoTest1.crearNuevoViaje(terminalGestionadaTest, terminalIntermedia2, LocalDateTime.now());
		Viaje viaje2 = circuitoTest2.crearNuevoViaje(terminalIntermedia2, terminalIntermedia3, LocalDateTime.now());
		Viaje viaje3 = circuitoTest1.crearNuevoViaje(terminalGestionadaTest, terminalIntermedia1, null);
		List<Viaje> listaViaje1 = Arrays.asList(viaje1);
		List<Viaje> listaViaje2 = Arrays.asList(viaje2);
		
		navieraTest.addBuque(buqueMock2);
		navieraTest.registrarNuevoRecorrido(buqueMock1, listaViaje1);
		navieraTest.registrarNuevoRecorrido(buqueMock2, listaViaje2);
		
		// Verify
		Assertions.assertTrue(navieraTest.getBuques().contains(buqueMock1));
		Assertions.assertTrue(navieraTest.getBuques().contains(buqueMock2));
		
		Assertions.assertTrue(navieraTest.getViajes().contains(viaje1));
		Assertions.assertTrue(navieraTest.getViajes().contains(viaje2));
		Assertions.assertFalse(navieraTest.getViajes().contains(viaje3));
		
		Assertions.assertEquals(buqueMock1, navieraTest.buscarBuquePorViaje(viaje1));
		Assertions.assertEquals(buqueMock2, navieraTest.buscarBuquePorViaje(viaje2));
		
		Assertions.assertEquals(null, navieraTest.buscarBuquePorViaje(viaje3));
	}
}
