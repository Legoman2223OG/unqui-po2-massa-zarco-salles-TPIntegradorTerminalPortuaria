package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.AlmacenamientoExcediente;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Desconsolidado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Electricidad;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Lavado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.Pesado;
import ar.edu.unq.po2.TerminalPortuaria.Servicios.RevisionPerdidas;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.Cliente;

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
		lavadoService = new Lavado(dc);
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
}
