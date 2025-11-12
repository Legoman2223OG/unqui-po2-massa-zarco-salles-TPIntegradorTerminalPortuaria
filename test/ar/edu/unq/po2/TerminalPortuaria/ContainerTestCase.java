package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLandingEspecial;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.Producto;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;

class ContainerTestCase {
	//DOC
	private Cliente docCliente = mock(Cliente.class);
	private Cliente docCliente1 = mock(Cliente.class);
	private Cliente docCliente2 = mock(Cliente.class);
	private BillOfLanding docbl = mock(BillOfLanding.class);
	private BillOfLanding docbl2 = mock(BillOfLanding.class);
	private BillOfLanding docbl3 = mock(BillOfLanding.class);
	private BillOfLandingEspecial docble = mock(BillOfLandingEspecial.class);
	//SUT
	private DryContainer dry;
	private DryContainer dryCompuesto;
	private TankContainer tank;
	private ReeferContainer reefer;

	@BeforeEach
	void setUp() throws Exception{
		dry = new DryContainer(3,3,3,docbl);
		dryCompuesto = new DryContainer(3,3,3,docble);
		tank = new TankContainer(3,3,3,"BELE3020302",docbl2);
		reefer = new ReeferContainer(3,3,3,"GINI2010304",docbl3,100);
		when(docCliente.getNombre()).thenReturn("Juan");
		when(docCliente1.getNombre()).thenReturn("Pedro");
		when(docCliente2.getNombre()).thenReturn("Maria");
	}
	/**
	 * Indica que los datos de un dry container de 1 solo dueño son los siguientes:
	 * -Dueño: Cliente: "Juan"
	 * -Productos: 2, Detergente (Peso 20), Jabon (Peso 10).
	 * -Peso total: 30.
	 */
	@Test
	void test01_DatosDeUnDryContainerDeUnSoloDuenio() {
		when(docbl.getPeso()).thenReturn(30.0);
		when(docbl.getDuenios()).thenReturn(Arrays.asList(docCliente));
		when(docbl.getProductos()).thenReturn(Arrays.asList(new Producto("Detergente",20), new Producto("Jabon",10)));
		when(docbl.getBillOfLandings()).thenReturn(Arrays.asList(docbl));
		//Exercise
		double peso = dry.getPeso();
		Cliente cliente = dry.getDuenio().getFirst();
		List<Producto> productos= dry.getBillsOfLanding().get(0).getProductos();
		//Verify
		Assertions.assertEquals(20 + 10, peso);
		Assertions.assertEquals("Juan", docCliente.getNombre());
		Assertions.assertEquals(Arrays.asList(new Producto("Detergente",20), new Producto("Jabon",10)), productos);
		verify(docbl, times(1)).getPeso();
		verify(docbl, times(1)).getDuenios();
		verify(docbl, times(1)).getBillOfLandings();
	}

	/**
	 * Indica que los datos de un dry container con varios duenios son los siguientes:
	 * -Dueños: Cliente: "Juan", Cliente: "Pedro", Cliente: "Maria".
	 * -Productos: Juan: (Detergente (20 peso), Jabon (10 peso)), Pedro: (Mesa (50 peso)), Maria: (Taladro (40 peso), Silla (30 peso))
	 * -Peso total: 150.
	 * @throws Exception
	 */
	@Test
	void test02_DatosDeUnDryContainerCompuesto() throws Exception {
		//DOC
		BillOfLanding blJuan = mock(BillOfLanding.class);
		when(blJuan.getProductos()).thenReturn(Arrays.asList(new Producto("Detergente",20), new Producto("Jabon",10)));
		BillOfLanding blPedro = mock(BillOfLanding.class);
		when(blPedro.getProductos()).thenReturn(Arrays.asList(new Producto("Mesa",50)));
		BillOfLanding blMaria = mock(BillOfLanding.class);
		when(blMaria.getProductos()).thenReturn(Arrays.asList(new Producto("Taladro",40), new Producto("Silla",30)));
		docble.agregarBillOfLanding(blJuan);
		docble.agregarBillOfLanding(blPedro);
		docble.agregarBillOfLanding(blMaria);
		when(docble.getPeso()).thenReturn(150.0);
		when(docble.getDuenios()).thenReturn(Arrays.asList(docCliente,docCliente1,docCliente2));
		when(docble.getProductos()).thenReturn(Arrays.asList(new Producto("Detergente",20), new Producto("Jabon",10), new Producto("Mesa",50), new Producto("Taladro",40), new Producto("Silla",30)));
		when(docble.getBillOfLandings()).thenReturn(Arrays.asList(blJuan, blPedro, blMaria));
		//Exercise
		double peso = dryCompuesto.getPeso();
		List<Cliente> cliente = dryCompuesto.getDuenio();
		List<Producto> productos= dryCompuesto.getBillsOfLanding().stream().flatMap(bl -> bl.getProductos().stream()).toList();
		//Verify
		Assertions.assertEquals(20 + 10 + 50 + 40 + 30, peso);
		Assertions.assertEquals(Arrays.asList("Juan","Pedro","Maria"), cliente.stream().map(Cliente::getNombre).toList());
		Assertions.assertEquals(Arrays.asList(new Producto("Detergente",20), new Producto("Jabon",10), new Producto("Mesa",50), new Producto("Taladro",40), new Producto("Silla",30)), productos);
		verify(docble, times(3)).agregarBillOfLanding(any(BillOfLanding.class));
		verify(docble, times(1)).getPeso();
		verify(docble, times(1)).getDuenios();
		verify(docble, times(1)).getBillOfLandings();
	}

	/**
	 * Indica que los datos de un Tank Container son:
	 * -Dueño: Cliente "Pedro".
	 * -Productos: 2, Gas (20 de peso), Agua (100 de peso).
	 * -Peso total: 120.
	 */
	@Test
	void test03_DatosDeUnTankContainer() {
		//DOC
		when(docbl2.getDuenios()).thenReturn(Arrays.asList(docCliente1));
		when(docbl2.getPeso()).thenReturn(120.0);
		when(docbl2.getProductos()).thenReturn(Arrays.asList(new Producto("Gas",20), new Producto("Agua",100)));
		when(docbl2.getBillOfLandings()).thenReturn(Arrays.asList(docbl2));
		//Exercise
		double peso = tank.getPeso();
		Cliente cliente = tank.getDuenio().getFirst();
		List<Producto> productos= tank.getBillsOfLanding().getFirst().getProductos();
		//Verify
		Assertions.assertEquals(20 + 100, peso);
		Assertions.assertEquals("Pedro", cliente.getNombre());
		Assertions.assertEquals(Arrays.asList(new Producto("Gas",20), new Producto("Agua",100)), productos);
		verify(docbl2, times(1)).getPeso();
		verify(docbl2, times(1)).getDuenios();
		verify(docbl2, times(1)).getBillOfLandings();
	}

	/**
	 * Indica que los datos de un Reefer Container son:
	 * -Dueño: Cliente "Maria".
	 * -Productos: 3, Carne (50 de peso), Leche (30 de peso), Bidon (30 de peso).
	 * -Peso total: 110.
	 * -Consumo De Electricidad: 100.
	 */
	@Test
	void test04_DatosDeUnReeferContainer() {
		//DOC
		when(docbl3.getDuenios()).thenReturn(Arrays.asList(docCliente2));
		when(docbl3.getPeso()).thenReturn(110.0);
		when(docbl3.getProductos()).thenReturn(Arrays.asList(new Producto("Carne",50), new Producto("Leche",30), new Producto("Bidon",30)));
		when(docbl3.getBillOfLandings()).thenReturn(Arrays.asList(docbl3));
		//Exercise
		double peso = reefer.getPeso();
		Cliente cliente = reefer.getDuenio().getFirst();
		List<Producto> productos= reefer.getBillsOfLanding().getFirst().getProductos();
		double consumoDeElectricidad = reefer.consumoElectricidad();
		//Verify
		Assertions.assertEquals(50 + 30 + 30, peso);
		Assertions.assertEquals("Maria", cliente.getNombre());
		Assertions.assertEquals(Arrays.asList(new Producto("Carne",50), new Producto("Leche",30), new Producto("Bidon",30)), productos);
		Assertions.assertEquals(100, consumoDeElectricidad);
		verify(docbl3, times(1)).getPeso();
		verify(docbl3, times(1)).getDuenios();
		verify(docbl3, times(1)).getBillOfLandings();
	}
	
	/**
	 * Describe el ancho de un container
	 */
	@Test
	void test05_anchoDeUnContainer() {
		//Exercise
		double ancho = dry.getAncho();
		//Verify
		Assertions.assertEquals(3, ancho);
	}
	
	/**
	 * Describe el alto de un container
	 */
	@Test
	void test06_alturaDeUnContainer() {
		//Exercise
		double altura = dry.getAltura();
		//Verify
		Assertions.assertEquals(3, altura);
	}
	
	/**
	 * Describe el largo de un container
	 */
	@Test
	void test07_largoDeUnContainer() {
		//Exercise
		double largo = dry.getLargo();
		//Verify
		Assertions.assertEquals(3, largo);
	}
	
	/**
	 * Describe el dueño del container, en caso del dry basico solo describe 1.
	 */
	@Test
	void test08_ContainerDescribeSuDuenio() {
		//DOC
		when(docbl2.getDuenios()).thenReturn(Arrays.asList(docCliente1));
		when(docbl3.getDuenios()).thenReturn(Arrays.asList(docCliente2));
		when(docbl.getDuenios()).thenReturn(Arrays.asList(docCliente));
		//Exercise
		Cliente clientedry = dry.getDuenio().getFirst();
		Cliente clientetank = tank.getDuenio().getFirst();
		Cliente clientereefer = reefer.getDuenio().getFirst();
		//Verify
		Assertions.assertEquals(docCliente, clientedry);
		Assertions.assertEquals(docCliente1, clientetank);
		Assertions.assertEquals(docCliente2, clientereefer);
	}
	
	/**
	 * Describe los duenios del container si es un dry compuesto.
	 */
	@Test
	void test09_DryCompuestoDescribeSusClientes() {
		//DOC
		when(docble.getDuenios()).thenReturn(Arrays.asList(docCliente, docCliente2, docCliente1));
		//Exercise
		List<Cliente> cliente = dryCompuesto.getDuenio();
		//Verify
		Assertions.assertEquals(Arrays.asList(docCliente, docCliente2, docCliente1), cliente);
	}
	
	/**
	 * Describe los Bills of Landing de un container con solo 1 BL
	 */
	@Test
	void test10_BillsOfLandingDelContainer() {
		//DOC
		when(docbl.getBillOfLandings()).thenReturn(Arrays.asList(docbl));
		when(docbl2.getBillOfLandings()).thenReturn(Arrays.asList(docbl2));
		when(docbl3.getBillOfLandings()).thenReturn(Arrays.asList(docbl3));
		//Exercise
		BillOfLanding bl = dry.getBillsOfLanding().getFirst();
		BillOfLanding bl2 = tank.getBillsOfLanding().getFirst();
		BillOfLanding bl3 = reefer.getBillsOfLanding().getFirst();
		//Verify
		Assertions.assertEquals(docbl, bl);
		Assertions.assertEquals(docbl2, bl2);
		Assertions.assertEquals(docbl3, bl3);
	}
	
	/**
	 * Describe los Bills of landing de un container compuesto.
	 */
	@Test
	void test11_BillsOfLandingDeUnContainerCompuesto() {
		//DOC
		when(docble.getBillOfLandings()).thenReturn(Arrays.asList(docbl, docbl3, docbl2));
		//Exercise
		List<BillOfLanding> bl = dryCompuesto.getBillsOfLanding();
		//Verify
		Assertions.assertEquals(Arrays.asList(docbl,docbl3,docbl2), bl);
	}
	
	/**
	 * Describe el peso de un container.
	 */
	@Test
	void test12_PesoDeContainer() {
		//DOC
		when(docbl.getPeso()).thenReturn(100.0);
		when(docbl2.getPeso()).thenReturn(300.0);
		when(docbl3.getPeso()).thenReturn(600.0);
		when(docble.getPeso()).thenReturn(1000.0);
		//Exercise
		double peso1 = dry.getPeso();
		double peso2 = tank.getPeso();
		double peso3 = reefer.getPeso();
		double peso4 = dryCompuesto.getPeso();
		//Verify
		Assertions.assertEquals(100.0, peso1);
		Assertions.assertEquals(300.0, peso2);
		Assertions.assertEquals(600.0, peso3);
		Assertions.assertEquals(1000.0, peso4);
	}
	
	/**
	 * Un Container describe su capacidad.
	 */
	@Test
	void test13_CapacidadDeContainer() {
		//Exercise
		double cap1 = dry.getCapacidad();
		double cap2 = tank.getCapacidad();
		double cap3 = reefer.getCapacidad();
		double cap4 = dryCompuesto.getCapacidad();
		//Verify
		Assertions.assertEquals(3 * 3 * 3, cap1);
		Assertions.assertEquals(3 * 3 * 3, cap2);
		Assertions.assertEquals(3 * 3 * 3, cap3);
		Assertions.assertEquals(3 * 3 * 3, cap4);
	}
	
	/**
	 * Un Reefer container describe su consumo de electricidad.
	 */
	@Test
	void test14_ConsumoElectricidadReefer() {
		//Exercise
		double consumoElectricidad = reefer.consumoElectricidad();
		//Verify
		Assertions.assertEquals(100.0, consumoElectricidad);
	}
	
	/**
	 * Un Container Compuesto agrega un bl a su lista de bls
	 * @throws Exception 
	 */
	@Test
	void test15_ContainerCompuestoAgregaBL() throws Exception {
		//DOC
		BillOfLanding docBLTest = mock(BillOfLanding.class);
		//Exercise
		dryCompuesto.agregarBillOfLanding(docBLTest);
		//Verify
		verify(docble, times(1)).agregarBillOfLanding(docBLTest);
	}
	
	/**
	 * Un container que no es compuesto no deberia poder agregar BLs.
	 * @throws Exception 
	 */
	@Test
	void test16_UnContainerNoCompuestoNoPuedeAgregarBLs() throws Exception {
		//DOC
		BillOfLanding docBLTest = mock(BillOfLanding.class);
		doThrow(new Exception("No se puede agregar un bill of landing a un bill of landing normal"))
	    	.when(docbl)
	    	.agregarBillOfLanding(docBLTest);
		//Exercise
		Exception dryNormalException = Assertions.assertThrows(Exception.class, () -> dry.agregarBillOfLanding(docBLTest));
		Exception tankException = Assertions.assertThrows(Exception.class, () -> tank.agregarBillOfLanding(docBLTest));
		Exception reeferException = Assertions.assertThrows(Exception.class, () -> reefer.agregarBillOfLanding(docBLTest));
		//Verify
		Assertions.assertEquals("No se puede agregar un bill of landing a un bill of landing normal", dryNormalException.getMessage());
		Assertions.assertEquals("No se puede agregar otro Bill of Landing a este contenedor", tankException.getMessage());
		Assertions.assertEquals("No se puede agregar otro Bill of Landing a este contenedor", reeferException.getMessage());
	}
	
	/**
	 * Un Container que no requiere consumo electrico, no puede calcular un consumo electrico
	 */
	@Test
	void test17_UnContainerSinConsumoElectricoNoPuedeCalcularConsumoElectrico() {
		//Exercise
		Exception dryNormalException = Assertions.assertThrows(Exception.class, () -> dry.consumoElectricidad()); 
		Exception tankException = Assertions.assertThrows(Exception.class, () -> tank.consumoElectricidad());
		//Verify
		Assertions.assertEquals("Este contenedor no requiere de conexion a una fuente de electricidad", dryNormalException.getMessage());
		Assertions.assertEquals("Este contenedor no requiere de conexion a una fuente de electricidad", tankException.getMessage());
	}
	
	/**
	 * No se puede hacer un container con un identificador que no respete las siguientes reglas:
	 * - Los primeros 4 caracteres son letras.
	 * - Deben haber 7 digitos despues de los caracteres.
	 * - En total debe haber 11 caracteres compuesto de 4 caracteres y 7 digitos.
	 * Dry es una excepción porque puede existir casos en donde no haya un dueño concreto por lo tanto su identificador
	 * es auto generado.
	 */
	@Test
	void test18_UnContainerNoPuedeTenerUnIdentificadorErroneo() {
		//Exercise
		Exception tankException = Assertions.assertThrows(Exception.class, () -> new TankContainer(3,3,3,"930330204050423",docbl));
		Exception tankException2 = Assertions.assertThrows(Exception.class, () -> new TankContainer(3,3,3,"93033040502",docbl));
		Exception tankException3 = Assertions.assertThrows(Exception.class, () -> new TankContainer(3,3,3,"ADAD304D040",docbl));
		Exception reeferException = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,3,3,"930330204050423",docbl,3.0));
		Exception reeferException2 = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,3,3,"93033040502",docbl, 3.0));
		Exception reeferException3 = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,3,3,"ADAD304D040",docbl, 3.0));
		//Verify
		Assertions.assertEquals("El identificador no es de 11 caracteres", tankException.getMessage());
		Assertions.assertEquals("No esta incluido el nombre del importador, usar letras", tankException2.getMessage());
		Assertions.assertEquals("No se incluyo digitos despues de las letras que identifican al importador", tankException3.getMessage());
		Assertions.assertEquals("El identificador no es de 11 caracteres", reeferException.getMessage());
		Assertions.assertEquals("No esta incluido el nombre del importador, usar letras", reeferException2.getMessage());
		Assertions.assertEquals("No se incluyo digitos despues de las letras que identifican al importador", reeferException3.getMessage());		
	}
	
	/**
	 * Un container no puede tener un ancho, largo o altura negativa o 0.
	 */
	@Test
	void test19_UnContainerNoPuedeTenerAnchoAlturaOLargoNegativoOIgualACero() {
		//Exercise
		Exception tankException = Assertions.assertThrows(Exception.class, () -> new TankContainer(-3,3,3,"BELE3020302",docbl));
		Exception tankException2 = Assertions.assertThrows(Exception.class, () -> new TankContainer(3,-3,3,"BELE3020302",docbl));
		Exception tankException3 = Assertions.assertThrows(Exception.class, () -> new TankContainer(3,3,-3,"BELE3020302",docbl));
		Exception reeferException = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(-3,3,3,"BELE3020302",docbl,3.0));
		Exception reeferException2 = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,-3,3,"BELE3020302",docbl, 3.0));
		Exception reeferException3 = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,3,-3,"BELE3020302",docbl, 3.0));
		Exception dryException = Assertions.assertThrows(Exception.class, () -> new DryContainer(-3,3,3,docbl));
		Exception dryException2 = Assertions.assertThrows(Exception.class, () -> new DryContainer(3,-3,3,docbl));
		Exception dryException3 = Assertions.assertThrows(Exception.class, () -> new DryContainer(3,3,-3,docbl));
		//Verify
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", tankException.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", tankException2.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", tankException3.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", reeferException.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", reeferException2.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", reeferException3.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", dryException.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", dryException2.getMessage());
		Assertions.assertEquals("Uno de los numeros ingresados para ancho, largo o altura es negativo o 0", dryException3.getMessage());
	}
	
	/**
	 * Un reefer container no puede tener un consumo de electricidad negativo o igual a 0.
	 */
	@Test
	void test20_UnReeferNoPuedeTenerConsumoElectricoNegativo() {
		//Exercise
		Exception reeferException = Assertions.assertThrows(Exception.class, () -> new ReeferContainer(3,3,-3,"BELE3020302",docbl, -2));
		//Verify
		Assertions.assertEquals("No se puede ingresar un numero negativo para consumo electrico", reeferException.getMessage());
	}
}
