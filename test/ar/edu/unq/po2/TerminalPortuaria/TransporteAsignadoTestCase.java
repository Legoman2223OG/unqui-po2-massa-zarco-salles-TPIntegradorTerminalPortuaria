package ar.edu.unq.po2.TerminalPortuaria;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Camion;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.Chofer;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.EmpresaTransportista;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista.TransporteAsignado;

public class TransporteAsignadoTestCase {

	private Camion camion = new Camion ("AFJ713");
	private Chofer chofer = new Chofer ("Pato");
	
	
    @Test
    public void testConstructorYGetters() {

        TransporteAsignado transporte = new TransporteAsignado(chofer, camion);

        assertEquals(chofer, transporte.getChoferAsignado());
        assertEquals(camion, transporte.getCamionAsignado());
    }
    
    @Test
    public void testEmpresaTransportistaInstanciable() {
        EmpresaTransportista empresa = new EmpresaTransportista();
        assertNotNull(empresa);  // Solo verifica que se puede instanciar
    }
    
    @Test
    public void testCamion() {
    	Camion camionT = new Camion ("AFJ713");
    	assertEquals("AFJ713", camionT.getPatente());
    	
    }
    
    @Test
    public void testChofer() {
    	Chofer chofer = new Chofer ("Pato");
    	assertEquals("Pato", chofer.getNombre());
    	
    }


}
