package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTestCase {
	//DOC
	//Terminal Principal
	private TerminalPortuaria docT = mock(TerminalPortuaria.class);
	//Viaje
	private Viaje docV = mock(Viaje.class);
	//SUT
	private Buque buque1;
	
	/**
	 * Crea un escenario donde un buque se encuentra alejado a mas de 50km de una terminal destino.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		buque1 = new Buque(new Coordenada(60,60),docT, docV);
		//DOC
		when(docT.getCoordenadas()).thenReturn(new Coordenada(0,0));
	}

	/**
	 * Indica que el buque pasa a inbound y le manda un mensaje de aviso a la terminal.
	 * @throws Exception 
	 */
	@Test
	void test01_ElBuquePasaAInbound() throws Exception {
		//Exercise
		buque1.moverA(new Coordenada(49,49));
		//Verify
		verify(docT).proximoAArribar(docV);
	}

}
