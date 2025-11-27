package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.Container;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.AlmacenamientoExcediente;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Desconsolidado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Electricidad;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Lavado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Pesado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.RevisionPerdidas;


class ServicioTestCase {
	//DOC
	private DryContainer dc = mock(DryContainer.class);
	private DryContainer dcc = mock(DryContainer.class);
	private TankContainer tc = mock(TankContainer.class);
	private ReeferContainer rc = mock(ReeferContainer.class);
	//SUT
	private Lavado lavadoService;
	private Electricidad electricidadService;
	private Pesado pesadoService;
	private AlmacenamientoExcediente almacenamientoExcediente;
	private Desconsolidado desconsolidadoService;
	private RevisionPerdidas revisionPerdidasService;

	/**
	 * Crea un escenario donde cada servicio tiene vinculado un respectivo container.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		lavadoService = new Lavado(dc,2000.0,1000.0);
		electricidadService = new Electricidad(rc, 1500);
		pesadoService = new Pesado(dcc, 1300);
		almacenamientoExcediente = new AlmacenamientoExcediente(dc, 2000);
		desconsolidadoService = new Desconsolidado(dcc);
		revisionPerdidasService = new RevisionPerdidas(tc);
	}

	/**
	 * Indica que el servicio de lavado cobra 2000 por trabajar con un Container con capacidad de 71 metros cubicos.
	 */
	@Test
	void test01_UnServicioDeLavadoCobra2000() {
		//DOC
		when(dc.getCapacidad()).thenReturn(80.0);
		//Exercise
		double precio = lavadoService.calcularPrecio();
		//Verify
		Assertions.assertEquals(2000.0, precio);
	}

	/**
	 * Indica que el servicio de lavado cobra 1000 por trabajar con un Container con capacidad de 70 metros cubicos.
	 */
	@Test
	void test02_UnServicioDeLavadoCobra1000() {
		//DOC
		when(dc.getCapacidad()).thenReturn(70.0);
		//Exercise
		double precio = lavadoService.calcularPrecio();
		//Verify
		Assertions.assertEquals(1000.0, precio);
	}

	/**
	 * Indica que un servicio de electricidad con un reefer con consumo de 120 kw/h y con 1500$ de precio fijo por el consumo
	 * cobra 180.000$
	 * @throws Exception
	 */
	@Test
	void test03_PrecioDeUnServicioDeElectricidadConPrecioFijo1500() throws Exception {
		//DOC
		when(rc.consumoElectricidad()).thenReturn(120.0);
		//Exercise
		double precio = electricidadService.calcularPrecio();
		//Verify
		Assertions.assertEquals(180000.0, precio);
	}

	/**
	 * Indica que un servicio de Pesado con un precio fijo de 1300$ por peso, pesa un container de 40 de peso
	 * y cobra 52.000$
	 * @throws Exception
	 */
	@Test
	void test04_PrecioDeUnServicioDePesadoConPrecioFijo1300() throws Exception {
		//DOC
		when(dcc.getPeso()).thenReturn(40.0);
		//Exercise
		double precio = pesadoService.calcularPrecio();
		//Verify
		Assertions.assertEquals(52000.0, precio);
	}

	/**
	 * Indica que un servicio de almacenamiento excediente con precio fijo de 2000$ cobra 2000$
	 * @throws Exception
	 */
	@Test
	void test05_PrecioDeUnServicioDeAlmacenamientoExcedienteConPrecioFijo2000() throws Exception {
		//Exercise
		double precio = almacenamientoExcediente.calcularPrecio();
		//Verify
		Assertions.assertEquals(2000.0, precio);
		verifyNoInteractions(dc);
	}

	/**
	 * Indica que un servicio de Desconsolidación obtiene el BillOfLanding de un cliente en un Dry Container Compuesto.
	 */
	@Test
	void test06_ServicioDeDesconsolidacionObtieneBillOfLandingDeUnCliente() {
		//DOC
		Cliente cl1 = mock(Cliente.class);
		Cliente cl2 = mock(Cliente.class);
		Cliente cl3 = mock(Cliente.class);
		BillOfLanding bl1 = mock(BillOfLanding.class);
		when(bl1.getDuenios()).thenReturn(Arrays.asList(cl2));
		BillOfLanding bl2 = mock(BillOfLanding.class);
		when(bl2.getDuenios()).thenReturn(Arrays.asList(cl3));
		BillOfLanding bl3 = mock(BillOfLanding.class);
		when(bl3.getDuenios()).thenReturn(Arrays.asList(cl1));
		when(dcc.getBillsOfLanding()).thenReturn(Arrays.asList(bl1,bl2,bl3));
		//Exercise
		BillOfLanding bl = desconsolidadoService.billOfLandingDelCliente(cl1);
		//Verify
		Assertions.assertEquals(bl3, bl);
		verify(bl1, times(1)).getDuenios();
		verify(bl2, times(1)).getDuenios();
		verify(bl3, times(1)).getDuenios();
	}

	/**
	 * Indica que un servicio de Revision de Perdidas asegura que hay una perdida en un Tank Container.
	 */
	@Test
	void test07_ServicioDeRevisionDePerdidasAseguraUnaPerdida() {
		Assertions.assertTrue(revisionPerdidasService.contienePerdidas());
	}
	
	/**
	 * Un servicio no puede tener un precio fijo negativo.
	 */
	@Test
	void test08_UnServicioNoPuedeTenerPrecioFijoNegativo() {
		//Exercise
		Exception almExcException = Assertions.assertThrows(Exception.class, () -> new AlmacenamientoExcediente(dc, -2000));
		Exception elecException = Assertions.assertThrows(Exception.class, () -> new Electricidad(rc, -1500));
		Exception pesadoException = Assertions.assertThrows(Exception.class, () -> new Pesado(dcc, -1300));
		Exception lavadoException = Assertions.assertThrows(Exception.class, () -> new Lavado(dc, -2000,1000));
		Exception lavadoException2 = Assertions.assertThrows(Exception.class, () -> new Lavado(dc, 2000,-1000));
		//Verify
		Assertions.assertEquals("El numero ingresado no es positivo", almExcException.getMessage());
		Assertions.assertEquals("El numero ingresado no es positivo", elecException.getMessage());
		Assertions.assertEquals("El numero ingresado no es positivo", pesadoException.getMessage());
		Assertions.assertEquals("No se puede ingresar un precio negativo", lavadoException.getMessage());
		Assertions.assertEquals("No se puede ingresar un precio negativo", lavadoException2.getMessage());
	}
	
	/**
	 * Un servicio que no calcula precio no puede darte un precio.
	 * Entre ellos estan:
	 * - Desconsolidado
	 * - RevisionPerdidas
	 */
	@Test
	void test09_UnServicioQueNoCalculaPrecioNoPuedeCalcularPrecio() {
		//Exercise
		Exception descException = Assertions.assertThrows(Exception.class, () -> desconsolidadoService.calcularPrecio());
		Exception revPerException = Assertions.assertThrows(Exception.class, () -> revisionPerdidasService.calcularPrecio());
		//Verify
		Assertions.assertEquals("Este servicio no se encarga de calcular un precio", descException.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de calcular un precio", revPerException.getMessage());
	}
	
	/**
	 * Un servicio que no sea el de desconsolidado, no puede buscar el bill of landing de un cliente.
	 */
	@Test
	void test10_UnServicioDiferenteADesconolidadoNoPuedeBuscarBL() {
		//DOC
		Cliente cl1 = mock(Cliente.class);
		//Exercise
		Exception lavExc = Assertions.assertThrows(Exception.class, () -> lavadoService.billOfLandingDelCliente(cl1));
		Exception elecExc = Assertions.assertThrows(Exception.class, () -> electricidadService.billOfLandingDelCliente(cl1));
		Exception pesExc = Assertions.assertThrows(Exception.class, () -> pesadoService.billOfLandingDelCliente(cl1));
		Exception revPerExc = Assertions.assertThrows(Exception.class, () -> revisionPerdidasService.billOfLandingDelCliente(cl1));
		Exception almExcedExc = Assertions.assertThrows(Exception.class, () -> almacenamientoExcediente.billOfLandingDelCliente(cl1));
		//Verify
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", lavExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", elecExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", pesExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", revPerExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", almExcedExc.getMessage());
	}
	
	/**
	 * Un servicio que no sea el de revision de perdidas, no puede revisar perdidas en un contenedor.
	 */
	@Test
	void test11_UnServicioQueNoSeEncargaDeLasPerdidasNoPuedeRevisarPerdidas() {
		//Exercise
		Exception lavExc = Assertions.assertThrows(Exception.class, () -> lavadoService.contienePerdidas());
		Exception elecExc = Assertions.assertThrows(Exception.class, () -> electricidadService.contienePerdidas());
		Exception pesExc = Assertions.assertThrows(Exception.class, () -> pesadoService.contienePerdidas());
		Exception descExc = Assertions.assertThrows(Exception.class, () -> desconsolidadoService.contienePerdidas());
		Exception almExcedExc = Assertions.assertThrows(Exception.class, () -> almacenamientoExcediente.contienePerdidas());
		//Verify
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", lavExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", elecExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", pesExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", descExc.getMessage());
		Assertions.assertEquals("Este servicio no se encarga de esta operacion", almExcedExc.getMessage());
	}
	
	/**
	 * Cada servicio del escenario describe su propio container.
	 */
	@Test
	void test12_UnServicioDescribeSuContainer() {
		//Exercise
		Container container = electricidadService.getContainer();
		Container container1 = lavadoService.getContainer();
		Container container2 = pesadoService.getContainer();
		Container container3 = almacenamientoExcediente.getContainer();
		Container container4 = desconsolidadoService.getContainer();
		Container container5 = revisionPerdidasService.getContainer();
		//Verify
		Assertions.assertEquals(rc, container);
		Assertions.assertEquals(dc, container1);
		Assertions.assertEquals(dcc, container2);
		Assertions.assertEquals(dc, container3);
		Assertions.assertEquals(dcc, container4);
		Assertions.assertEquals(tc, container5);
		
	}
}
