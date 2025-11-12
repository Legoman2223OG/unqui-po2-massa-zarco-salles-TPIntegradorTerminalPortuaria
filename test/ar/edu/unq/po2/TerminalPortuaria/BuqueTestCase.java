package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.Buque;
import ar.edu.unq.po2.TerminalPortuaria.Buque.Coordenada;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.Viaje;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;

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
		buque1 = new Buque("Test",new Coordenada(60,60), docV);
		when(docV.puertoDeLlegada()).thenReturn(docT);
		when(docV.getPuertoInicio()).thenReturn(docT);
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

	/**
	 * Indica que el buque pasa de arrived a working.
	 * @throws Exception
	 */
	@Test
	void test02_ElBuquePasaAWorking() throws Exception{
		//Exercise
		//Pasamos a Inbound.
		buque1.moverA(new Coordenada(49,49));
		//Pasamos a Arrived
		buque1.moverA(new Coordenada(0,0));
		//Verify
		verify(docT, times(2)).getCoordenadas();
	}

	/**
	 * Indica que el buque pasa de Working, a Depart y luego a Outbound de vuelta, mandandole un mensaje de vuelta a la
	 * terminal.
	 * @throws Exception
	 */
	@Test
	void test03_ElBuquePasaDeWorkingADepartYLuegoOutbound() throws Exception {
		//Exercise
		//Pasamos a Inbound.
		buque1.moverA(new Coordenada(49,49));
		//Pasamos a Arrived
		buque1.moverA(new Coordenada(0,0));
		buque1.working();
		//Pasamos a Depart
		buque1.depart();
		//Finalmente nos movemos y volvemos a Outbound.
		buque1.moverA(new Coordenada(20,20));
		//Verify
		verify(docT).partiendoAViaje(docV);
	}

	/**
	 * Indica que el buque que estaba en inbound vuelve a estar en outbound, debido a problemas ambientales que desviaron
	 * su curso.
	 */
	@Test
	void test04_ElBuquePasaDeInboundAOutbound() throws Exception{
		//Exercise
		//Pasamos a Inbound.
		buque1.moverA(new Coordenada(49,49));
		//Pasamos a Outbound.
		buque1.moverA(new Coordenada(60,60));
		//Pasamos otra vez a Inbound para confirmar que se envia otra vez el mensaje
		buque1.moverA(new Coordenada(49,49));
		//Verify
		verify(docT,times(2)).proximoAArribar(docV);
	}
	
	/**
	 * Indica que el buque tras actualizar sus coordenadas a iguales a la de la terminal de destino, pasa a arrived y luego a working.
	 * @throws Exception
	 */
	@Test
	void test05_ElBuquePasaDeOutboundAArrivedYLuegoAWorking() throws Exception{
		//Exercise
		//Pasamos a Arrived
		buque1.moverA(new Coordenada(0,0));
		buque1.working();
		verify(docT).proximoAArribar(docV);
		verify(docT, times(1)).getCoordenadas();
	}
	
	/**
	 * Indica que el buque describe su terminal de destino.
	 */
	@Test
	void test06_BuqueDescribeSuTerminalDeDestino() {
		//Exercise
		TerminalPortuaria terminalDestino = buque1.getDestino();
		Assertions.assertEquals(docT, terminalDestino);
	}
	
	/**
	 * Indica que el buque describe su terminal de origen.
	 */
	@Test
	void test07_BuqueDescribeSuTerminalDeOrigen() {
		//Exercise
		TerminalPortuaria terminalOrigen = buque1.getOrigen();
		Assertions.assertEquals(docT, terminalOrigen);
	}
	
	/**
	 * Describe las coordenadas del buque.
	 */
	@Test
	void test08_CoordenadasDelBuque() {
		//Exercise
		Coordenada coords = buque1.getCoordenadas();
		Assertions.assertEquals(new Coordenada(60,60), coords);
	}
}

