package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLanding;
import ar.edu.unq.po2.TerminalPortuaria.Container.BillOfLandingEspecial;
import ar.edu.unq.po2.TerminalPortuaria.Container.DryContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.ReeferContainer;
import ar.edu.unq.po2.TerminalPortuaria.Container.TankContainer;

class ContainerTestCase {
	//DOC
	private BillOfLanding docbl = mock(BillOfLanding.class);
	private BillOfLanding docbl1 = mock(BillOfLanding.class);
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
		dryCompuesto.agregarBillOfLanding(docbl1);
		tank = new TankContainer(3,3,3,"BELE3020302",docbl2);
		reefer = new ReeferContainer(3,3,3,"GINI2010304",docbl3,20);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
