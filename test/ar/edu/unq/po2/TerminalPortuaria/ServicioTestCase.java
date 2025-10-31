package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
