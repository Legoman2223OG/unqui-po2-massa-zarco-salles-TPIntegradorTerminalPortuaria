package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Tramo;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

public class ViajesTestCase {
	// SUT
	private Viaje viajeTest;
	
	// DOC
	private TerminalPortuaria terminalDeInicio;
	private TerminalPortuaria terminal2;
	private TerminalPortuaria terminal3;
	private TerminalPortuaria terminal4;
	private Tramo tramo1;
	private Tramo tramo2;
	private Tramo tramo3;
	private ArrayList<Tramo> tramosDelViaje = new ArrayList<>();

	@BeforeEach
	void setUp() {
		terminalDeInicio = mock(TerminalPortuaria.class);
		terminal2        = mock(TerminalPortuaria.class);
		terminal3        = mock(TerminalPortuaria.class);
		terminal4        = mock(TerminalPortuaria.class);
		
		tramo1 = new Tramo(terminalDeInicio,terminal2,Duration.ofMinutes(28),62.8);
		tramo2 = new Tramo(terminal2,       terminal3,Duration.ofHours(1),   128.0);
		tramo3 = new Tramo(terminal3,       terminal4,Duration.ofMinutes(32),78.2);
		tramosDelViaje.add(tramo1); tramosDelViaje.add(tramo2); tramosDelViaje.add(tramo3);
		
		viajeTest = new Viaje(terminalDeInicio,tramosDelViaje,LocalDateTime.of(2025, 11, 10, 13, 50));
	}

	@Test
	void testPropiedadesDelViaje() {
		// Verify
		Assertions.assertEquals(LocalDateTime.of(2025, 11, 10, 15, 50), viajeTest.fechaDeLlegada());
		Assertions.assertEquals(2, viajeTest.numeroDeTerminalesEntreOrigenYDestino());
		Assertions.assertEquals(Duration.ofHours(2), viajeTest.duracionDelViaje());
		Assertions.assertEquals(269.0, viajeTest.precioTotal());
		Assertions.assertEquals(terminal4, viajeTest.puertoDeLlegada());
	}
	
	@Test
	void testTerminalesDelViaje() {
		Set<TerminalPortuaria> listaTerminales = new HashSet<>();
		listaTerminales.add(terminalDeInicio); listaTerminales.add(terminal2); listaTerminales.add(terminal3); listaTerminales.add(terminal4);
		
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
}
