package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Cliente.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLandingEspecial;
import ar.edu.unq.po2.TerminalPortuaria.Container.Producto;

class BillOfLandingTestCase {
	//DOC
	Cliente docC = mock(Cliente.class);
	Cliente docC1 = mock(Cliente.class);
	Cliente docC2 = mock(Cliente.class);
	Cliente docC3 = mock(Cliente.class);
	Producto docP = mock(Producto.class);
	Producto docP1 = mock(Producto.class);
	Producto docP2 = mock(Producto.class);
	Producto docP3 = mock(Producto.class);
	Producto docP4 = mock(Producto.class);
	Producto docP5 = mock(Producto.class);
	Producto docP6 = mock(Producto.class);
	//SUT
	BillOfLanding bl;
	BillOfLanding bl2;
	BillOfLanding bl3;
	BillOfLanding bl4;
	BillOfLandingEspecial ble;
	
	/**
	 * Crea un escenario donde hay un BL normal con 2 productos, un BL Especial el cual contiene los siguientes BL:
	 * - BL1: 2 Prods.
	 * - BL2: 2 Prods.
	 * - BL3: 1 Prod.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bl = new BillOfLanding(docC, docP,docP1);
		bl2 = new BillOfLanding(docC1,docP2,docP3);
		bl3 = new BillOfLanding(docC2, docP4,docP5);
		bl4 = new BillOfLanding(docC3, docP6);
		ble = new BillOfLandingEspecial(bl2,bl3,bl4);
	}

	/**
	 * Un BL no especial describe su dueño.
	 */
	@Test
	void test01_UnBillOfLandingNormalDescribeSuDuenio() {
		//Exercise
		Cliente cliente = bl.getDuenios().getFirst();
		//Verify
		Assertions.assertEquals(docC, cliente);
	}
	
	/**
	 * Un BL Especial describe sus dueños
	 */
	@Test
	void test02_UnBillOfLandingEspecialDescribeSusDuenios() {
		//Exercise
		List<Cliente> clientes = ble.getDuenios();
		//Verify
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(docC1,docC2,docC3)), clientes);
	}
	
	/**
	 * Un BL Especial y un BL Normal describen sus productos.
	 */
	@Test
	void test03_UnBillOfLandingDescribeSusProductos() {
		//Exercise
		List<Producto> prods1 = bl.getProductos();
		List<Producto> prods2 = ble.getProductos();
		//Verify
		Assertions.assertEquals(Arrays.asList(docP,docP1), prods1);
		Assertions.assertEquals(Arrays.asList(docP2,docP3,docP4,docP5,docP6), prods2);
	}
	
	/**
	 * Un BL Especial describe sus BLs.
	 * Un BL Normal se auto describe en una lista.
	 */
	@Test
	void test04_UnBillOfLandingDescribeSusBLsOAsiMismo() {
		//Exercise
		BillOfLanding blSolo = bl.getBillOfLandings().getFirst();
		List<BillOfLanding> blsMultiples = ble.getBillOfLandings();
		//Verify
		Assertions.assertEquals(bl, blSolo);
		Assertions.assertEquals(Arrays.asList(bl2,bl3,bl4), blsMultiples);
	}

	/**
	 * Los Bill of Landing describen sus pesos con respecto a sus productos listados.
	 * El BL Normal tiene un peso de 300 (100 + 200).
	 * El BL Especial tiene un peso de 900 (200 + 300 + 100 + 100 + 200).
	 */
	@Test
	void test05_UnBLDescribeSuPeso() {
		//DOC
		when(docP.getPeso()).thenReturn(100.0);
		when(docP1.getPeso()).thenReturn(200.0);
		when(docP2.getPeso()).thenReturn(200.0);
		when(docP3.getPeso()).thenReturn(300.0);
		when(docP4.getPeso()).thenReturn(100.0);
		when(docP5.getPeso()).thenReturn(100.0);
		when(docP6.getPeso()).thenReturn(200.0);
		//Exercise
		double peso1 = bl.getPeso();
		double peso2 = ble.getPeso();
		//Verify
		Assertions.assertEquals(100.0 + 200.0, peso1);
		Assertions.assertEquals(200.0 + 300.0 + 100.0 + 100.0 + 200.0, peso2);
	}
	
	/**
	 * Un Bill Of Landing Especial agrega un nuevo BL a su lista.
	 * @throws Exception 
	 */
	@Test
	void test06_UnBillOfLandingEspecialAgregaUnBL() throws Exception {
		//DOC
		Cliente clienteDocNew = mock(Cliente.class);
		Producto prodDoc = mock(Producto.class);
		BillOfLanding blNew = new BillOfLanding(clienteDocNew,prodDoc);
		//Exercise
		ble.agregarBillOfLanding(blNew);
		List<Cliente> clientes = ble.getDuenios();
		//Verify
		Assertions.assertEquals(Arrays.asList(docC1,docC2,docC3,clienteDocNew), clientes);
	}
	
	/**
	 * Un Bill Of Landing Normal no puede agregar BLs.
	 */
	@Test
	void test07_UnBillOfLandingNormalNoPuedeAgregarBLs() {
		//DOC
		BillOfLanding blDoc = mock(BillOfLanding.class);
		//Exercise
		Exception blNormalExc = Assertions.assertThrows(Exception.class, () -> bl.agregarBillOfLanding(blDoc));
		//Verify
		Assertions.assertEquals("No se puede agregar un bill of landing a un bill of landing normal", blNormalExc.getMessage());
	}
}
