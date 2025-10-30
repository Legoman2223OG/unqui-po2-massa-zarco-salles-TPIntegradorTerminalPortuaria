package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTestCase {
	//SUT
	private DryContainer dry;
	private TankContainer tank;
	private ReeferContainer reefer;
	
	@BeforeEach
	void setUp() throws Exception{
		dry = new DryContainer("ALAN3040908");
		tank = new TankContainer("BELE3020302");
		reefer = new ReeferContainer("GINI2010304");
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
