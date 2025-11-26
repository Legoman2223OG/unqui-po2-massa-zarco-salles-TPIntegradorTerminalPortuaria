package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ViajesTestCase {
	// SUT
	private Viaje viajeTest;

	// DOC
	private TerminalPortuaria terminalDeInicio;
	private TerminalPortuaria terminal2;
	private TerminalPortuaria terminal3;
	private TerminalPortuaria terminalDestino;
	private Tramo tramo1;
	private Tramo tramo2;
	private Tramo tramo3;
	private Tramo tramo4;
	private ArrayList<Tramo> tramosDelViaje = new ArrayList<>();
	private Circuito circuitoTest;

	@BeforeEach
	void setUp() throws Exception {
		terminalDeInicio = mock(TerminalPortuaria.class);
		terminal2        = mock(TerminalPortuaria.class);
		terminal3        = mock(TerminalPortuaria.class);
		terminalDestino  = mock(TerminalPortuaria.class);

		tramo1 = new Tramo(terminalDeInicio, terminal2,        Duration.ofMinutes(28), 62.8);
		tramo2 = new Tramo(terminal2,        terminal3,        Duration.ofHours(1),    128.0);
		tramo3 = new Tramo(terminal3,        terminalDestino,  Duration.ofMinutes(32), 78.2);
		tramo4 = new Tramo(terminalDestino,  terminalDeInicio, Duration.ofMinutes(2),  3.0);
		tramosDelViaje.add(tramo1); tramosDelViaje.add(tramo2); tramosDelViaje.add(tramo3); tramosDelViaje.add(tramo4);
		circuitoTest = new Circuito(tramosDelViaje);

		viajeTest = new Viaje(terminalDeInicio, terminalDestino, circuitoTest, LocalDateTime.of(2025, 11, 10, 13, 50));
	}

	@Test
	void testPropiedadesDelViaje() {
		// Verify
		Assertions.assertEquals(terminalDeInicio, viajeTest.getPuertoInicio());
		Assertions.assertEquals(circuitoTest, viajeTest.getCircuitoDelViaje());
		Assertions.assertEquals(LocalDateTime.of(2025, 11, 10, 13, 50), viajeTest.getFechaSalida());
		Assertions.assertEquals(terminalDeInicio, viajeTest.getUltimaTerminalDePartida());
		Assertions.assertEquals(terminal2, viajeTest.getProximaTerminal());
		Assertions.assertEquals(LocalDateTime.of(2025, 11, 10, 15, 50), viajeTest.fechaDeLlegada());
		Assertions.assertEquals(2, viajeTest.numeroDeTerminalesEntreOrigenYDestino());
		Assertions.assertEquals(Duration.ofHours(2), viajeTest.duracionDelViaje());
		Assertions.assertEquals(269.0, viajeTest.precioTotal());
		Assertions.assertEquals(terminalDestino, viajeTest.puertoDeLlegada());
	}

	@Test
	void testTerminalesDelViaje() {
		Set<TerminalPortuaria> listaTerminales = new HashSet<>();
		listaTerminales.add(terminalDeInicio); listaTerminales.add(terminal2); listaTerminales.add(terminal3); listaTerminales.add(terminalDestino);

		// Verify
		Assertions.assertEquals(listaTerminales, viajeTest.todasLasTerminalesDentroDelViaje());
	}

	@Test
	void testTerminalQueNoEstaEnElViaje() {
		// DOC
		TerminalPortuaria terminal5 = mock(TerminalPortuaria.class);

		// Verify
		Assertions.assertEquals(false, viajeTest.validarSiTerminalExisteEnViaje(terminal5));
	}
	
	@Test
	void testCambiarAProximoTramo() {
		// Exercise
		viajeTest.cambiarASiguienteTramo();
		
		// Verify
		Assertions.assertEquals(terminal2, viajeTest.getUltimaTerminalDePartida());
		Assertions.assertEquals(terminal3, viajeTest.getProximaTerminal());
		
		// Exercise
		viajeTest.cambiarASiguienteTramo();
				
		// Verify
		Assertions.assertEquals(terminal2, viajeTest.getUltimaTerminalDePartida());
		Assertions.assertEquals(null, viajeTest.getProximaTerminal());
	}
}
