package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.Buque.GPS;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

class GPSTestCase {
	//DOC
	Buque docB = mock(Buque.class);
	//SUT
	private GPS gps;
	/**
	 * Crea un escenario donde el GPS se encuentra en las coordenadas 10,10 y conoce a un buque.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		gps = new GPS(new Coordenada(10,10), docB);
	}

	/**
	 * El GPS describe sus coordenadas que son (10,10)
	 */
	@Test
	void test01_CoordenadasDelGPS() {
		//Exercise
		Coordenada coords = gps.getCoordenadas();
		//Verify
		Assertions.assertEquals(new Coordenada(10,10), coords);
	}
	
	/**
	 * Un GPS recibe nuevas coordenadas y le avisa al buque que actualize su estado con la nueva distancia calculada.
	 * Esto aplica para cualquier caso, ya sea si es la coordenadas de origen o destino.
	 */
	@Test
	void test02_GPSActualizaSusCoordenadasYAvisaAlBuque() {
		//DOC
		TerminalPortuaria docT = mock(TerminalPortuaria.class);
		when(docT.getCoordenadas()).thenReturn(new Coordenada(0,0));
		//Exercise
		gps.setCoordenadas(new Coordenada(9,9),docT.getCoordenadas());
		//Verify
		verify(docT).getCoordenadas();
		verify(docB).actualizarEstado(12.727922061357855d);
	}

	/**
	 * Un GPS recibe nuevas coordenadas de un buque que no tiene un viaje / termino su viaje y no tiene un destino, por lo tanto
	 * actualiza su posicion, pero no avisa sobre actualizar su estado.
	 */
	@Test
	void test03_GPSActualizaSusCoordenadasPeroNoAvisaAlBuque() {
		//Exercise
		gps.setCoordenadas(new Coordenada(9,9));
		//Verify
		verifyNoInteractions(docB);
	}
}
