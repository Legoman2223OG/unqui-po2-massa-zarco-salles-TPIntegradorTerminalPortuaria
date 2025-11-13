package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container.Producto;

class ProductoTestCase {
	//SUT
	private Producto prod;
	/**
	 * Crea un caso donde hay un producto llamado "Test" con peso de 20.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		prod = new Producto("Test", 20.0);
	}

	/**
	 * Un producto describe su nombre.
	 */
	@Test
	void test01_NombreDelProducto() {
		//Exercise
		String nombre = prod.getNombre();
		//Verify
		Assertions.assertEquals("Test", nombre);
	}
	
	/**
	 * Un producto describe su peso.
	 */
	@Test
	void test02_PesoDelProducto() {
		//Exercise
		double peso = prod.getPeso();
		//Verify
		Assertions.assertEquals(20.0, peso);
	}
	
	/**
	 * Indica que dos productos son equivalentes si tienen el mismo nombre y peso.
	 * @throws Exception 
	 */
	@Test
	void test03_DosProductosIguales() throws Exception {
		//DOC
		Producto prodEquiv = new Producto("Test",20.0);
		//Verify
		Assertions.assertTrue(prod.equals(prodEquiv));
	}
	
	/**
	 * Un producto no puede tener peso negativo
	 */
	@Test
	void test04_NoPuedeTenerPesoNegativo() {
		//Exercise
		Exception prodExc = Assertions.assertThrows(Exception.class, () -> new Producto("Test", -1.0));
		//Verify
		Assertions.assertEquals("El peso no puede ser negativo o 0", prodExc.getMessage());
	}

}
