package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Buque.*;
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
		buque1.moverA(new Coordenada(29,29));
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
		buque1.moverA(new Coordenada(29,29));
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
		buque1.moverA(new Coordenada(29,29));
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
		buque1.moverA(new Coordenada(29,29));
		//Pasamos a Outbound.
		buque1.moverA(new Coordenada(60,60));
		//Pasamos otra vez a Inbound para confirmar que se envia otra vez el mensaje
		buque1.moverA(new Coordenada(29,29));
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
		//Verfy
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
		//Verfy
		Assertions.assertEquals(docT, terminalDestino);
	}
	
	/**
	 * Indica que el buque describe su terminal de origen.
	 */
	@Test
	void test07_BuqueDescribeSuTerminalDeOrigen() {
		//Exercise
		TerminalPortuaria terminalOrigen = buque1.getOrigen();
		//Verfy
		Assertions.assertEquals(docT, terminalOrigen);
	}
	
	/**
	 * Describe las coordenadas del buque.
	 */
	@Test
	void test08_CoordenadasDelBuque() {
		//Exercise
		Coordenada coords = buque1.getCoordenadas();
		//Verfy
		Assertions.assertEquals(new Coordenada(60,60), coords);
	}
	
	/**
	 * Indica que un buque cambio sus coordenadas porque se movio.
	 * @throws Exception 
	 */
	@Test
	void test09_UnBuqueSeMueve() throws Exception {
		//Exercise
		buque1.moverA(new Coordenada(62,62));
		Coordenada coords = buque1.getCoordenadas();
		//Verfy
		Assertions.assertEquals(new Coordenada(62,62), coords);
	}
	
	/**
	 * Describe el nombre del buque.
	 */
	@Test
	void test10_UnBuqueConsultaSuNombre() {
		//Exercise
		String nombre = buque1.getNombre();
		//Verify
		Assertions.assertEquals("Test",nombre);
	}
	
	/**
	 * Indica que el buque sin haberlo movido, su gps indica que esta en la coordenada 60,60.
	 */
	@Test
	void test11_GPSDeUnBuque() {
		//Exercise
		Coordenada coords = buque1.getGps().getCoordenadas();
		//Verify
		Assertions.assertEquals(new Coordenada(60,60), coords);
	}
	
	/**
	 * Describe el viaje actual del buque.
	 */
	@Test
	void test12_ViajeDeUnBuque() {
		//Exercise
		Viaje viaje = buque1.getViaje();
		//Verify
		Assertions.assertEquals(docV, viaje);
	}
	
	/**
	 * Un buque cambia su viaje por otro viaje.
	 */
	@Test
	void test13_BuqueCambiaDeViaje() {
		//DOC
		Viaje docV2 = mock(Viaje.class);
		//Exercise
		buque1.setViaje(docV2);
		Viaje viaje = buque1.getViaje();
		//Verify
		Assertions.assertEquals(docV2, viaje);
	}
	
	/**
	 * Un buque que estaba a outbound y pasa a inbound, settea su estado a outbound de vuelta y luego de moverse de nuevo,
	 * deberia de haberle mandado 2 mensajes a la terminal de destino.
	 * @throws Exception 
	 */
	@Test
	void test14_BuqueSetteaSuEstado() throws Exception {
		//Exercise
		buque1.moverA(new Coordenada(29,29));
		buque1.setStatus(new Outbound());
		buque1.moverA(new Coordenada(29,29));
		//Verify
		verify(docT, times(2)).proximoAArribar(docV);
	}
	
	/**
	 * Cuando el buque esta en el estado Outbound, no puede llamar a los siguientes mensajes:
	 * #working()
	 * #depart()
	 */
	@Test
	void test15_BuqueEnEstadoOutboundNoPuedeLlamarA() {
		//Exercise
		Exception workingException = Assertions.assertThrows(Exception.class, 
				() -> buque1.working(),
				"Aun no se encuentra operable en este estado");
		Exception departException = Assertions.assertThrows(Exception.class, 
				() -> buque1.depart(),
				"Aun no se encuentra operable en este estado");
		//Verify
		Assertions.assertEquals("Aun no se encuentra operable en este estado", workingException.getMessage());
		Assertions.assertEquals("Aun no se encuentra operable en este estado", departException.getMessage());
	}
	
	/**
	 * Cuando el buque esta en el estado Inbound, no puede llamar a los siguientes mensajes:
	 * #working()
	 * #depart()
	 * @throws Exception 
	 */
	@Test
	void test16_BuqueEnEstadoInboundNoPuedeLlamarA() throws Exception {
		//Exercise
		//Lo llevamos a estado Inbound
		buque1.moverA(new Coordenada(30,30));
		Exception workingException = Assertions.assertThrows(Exception.class, 
					() -> buque1.working(),
					"Aun no se encuentra operable en este estado");
		Exception departException = Assertions.assertThrows(Exception.class, 
					() -> buque1.depart(),
					"Aun no se encuentra operable en este estado");
		//Verify
		Assertions.assertEquals("No se puede pasar a este estado actualmente", workingException.getMessage());
		Assertions.assertEquals("No se puede partir en este estado", departException.getMessage());
	}
	
	/**
	 * Cuando el buque esta en el estado Arrived, no puede llamar a los siguientes mensajes:
	 * #working()
	 * #moverA(Coordenada coordenada, Buque buque)
	 * @throws Exception 
	 */
	@Test
	void test17_BuqueEnEstadoArrivedNoPuedeLlamarA() throws Exception {
		//Exercise
		//Lo llevamos a estado Arrived
		buque1.moverA(new Coordenada(0,0));
		Exception departException = Assertions.assertThrows(Exception.class, 
					() -> buque1.depart(),
					"No se puede partir en este estado");
		Exception moverAException = Assertions.assertThrows(Exception.class, 
					() -> buque1.moverA(new Coordenada(10,10)),
					"No se puede mover en este estado");
		//Verify
		Assertions.assertEquals("No se puede partir en este estado", departException.getMessage());
		Assertions.assertEquals("No se puede mover en este estado", moverAException.getMessage());
	}
	
	/**
	 * Cuando el buque se encuentra en estado Working(), no puede llamar a los siguientes mensajes:
	 * #moverA(Coordenada coordenada, Buque buque)
	 * @throws Exception 
	 */
	@Test
	void test18_BuqueEnEstadoWorkingNoPuedeLlamarA() throws Exception {
		//Exercise
		//Lo llevamos a estado Arrived
		buque1.moverA(new Coordenada(0,0));
		//Lo llevamos a Working
		buque1.working();
		Exception moverAException = Assertions.assertThrows(Exception.class, 
				() -> buque1.moverA(new Coordenada(10,10)),
				"No se puede mover en este estado");
		//Verify
		Assertions.assertEquals("No se puede mover en este estado", moverAException.getMessage());
	}
	
	/**
	 * Cuando el buque se encuentra en estado Depart, no puede llamar a los siguientes mensajes:
	 * #working()
	 * @throws Exception 
	 */
	@Test
	void test19_BuqueEnEstadoDepartNoPuedeLlamarA() throws Exception{
		//Exercise
		//Lo llevamos a estado Arrived
		buque1.moverA(new Coordenada(0,0));
		//Lo llevamos a Working
		buque1.working();
		//Lo llevamos a Depart
		buque1.depart();
		Exception workingException = Assertions.assertThrows(Exception.class, 
				() -> buque1.working(),
				"Aun no se encuentra operable en este estado");
		//Verify
		Assertions.assertEquals("No se puede cambiar a este estado", workingException.getMessage());
	}
	
	/**
	 * Cuando el buque cambia de viaje, deja de mandarle mensajes a la terminal de destino.
	 * @throws Exception 
	 */
	@Test
	void test20_BuqueCambiaDeViajeYYaNoOperaConLaTerminalDeDestinoAnterior() throws Exception {
		//DOC
		TerminalPortuaria docT2 = mock(TerminalPortuaria.class);
		when(docT2.getCoordenadas()).thenReturn(new Coordenada(100,100));
		Viaje docV2 = mock(Viaje.class);
		when(docV2.puertoDeLlegada()).thenReturn(docT2);
		//Exercise
		buque1.setViaje(docV2);
		buque1.moverA(new Coordenada(80,80));
		//Verify
		verifyNoMoreInteractions(docT);
		verify(docT2).proximoAArribar(docV2);
	}
	
	/**
	 * Un buque cuando deja de tener un viaje vigente, deja de enviar avisos a la terminal a la que tenia en el
	 * viaje anterior.
	 * @throws Exception 
	 */
	@Test
	void test21_BuqueDejaDeTenerUnViajeVinculado() throws Exception {
		//Exercise
		buque1.setViaje(null);
		buque1.moverA(new Coordenada(3,3));
		//Verify
		verifyNoMoreInteractions(docT);
	}
	
	/**
	 * Un buque sin viaje se le asigna un viaje y le manda avisos a la terminal en el estado Inbound.
	 * @throws Exception 
	 */
	@Test
	void test22_BuqueSinViajeSeLeAsignaUnViaje() throws Exception {
		//SUT
		Buque buqueSinViaje = new Buque("Test", new Coordenada(50,50));
		//Exercise
		//Le ponemos el viaje y lo pasamos a inbound
		buqueSinViaje.setViaje(docV);
		buqueSinViaje.moverA(new Coordenada(10,10));
		//Verify
		verify(docT).proximoAArribar(docV);
	}
}

