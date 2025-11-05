package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLandingEspecial;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.Producto;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.Cliente;

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
}
