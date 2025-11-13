package ar.edu.unq.po2.TerminalPortuaria;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.BusquedaMaritima.*;
import ar.edu.unq.po2.TerminalPortuaria.NavierasYCircuitos.*;
import ar.edu.unq.po2.TerminalPortuaria.Terminal.TerminalPortuaria;


import java.util.ArrayList;
import java.util.List;

public class BusquedaMaritimaTestCase {

	Viaje viaje1, viaje2, viaje3;
    TerminalPortuaria puerto1, puerto2;
    LocalDateTime fecha1, fecha2;
    List<Viaje> listaT = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        // Mocks de fechas y terminales
        fecha1 = LocalDateTime.of(2025, 11, 12, 10, 0);
        fecha2 = LocalDateTime.of(2025, 11, 13, 12, 0);

        puerto1 = mock(TerminalPortuaria.class);
        puerto2 = mock(TerminalPortuaria.class);

        // Mocks de Viaje
        viaje1 = mock(Viaje.class);
        viaje2 = mock(Viaje.class);
        viaje3 = mock(Viaje.class);

        // Configuramos comportamientos
        when(viaje1.getFechaSalida()).thenReturn(fecha1);
        when(viaje2.getFechaSalida()).thenReturn(fecha2);
        when(viaje3.getFechaSalida()).thenReturn(fecha1);
        
        when(viaje1.fechaDeLlegada()).thenReturn(fecha1);
        when(viaje2.fechaDeLlegada()).thenReturn(fecha2);
        when(viaje3.fechaDeLlegada()).thenReturn(fecha1);

        when(viaje1.puertoDeLlegada()).thenReturn(puerto1);
        when(viaje2.puertoDeLlegada()).thenReturn(puerto2);
        when(viaje3.puertoDeLlegada()).thenReturn(puerto1);
        
        listaT.add(viaje1);
        listaT.add(viaje2);
        listaT.add(viaje3);
    }

    @Test
    public void testFiltroFechaSalidaFiltraCorrectamente() {
        FiltroFechaSalida filtro = new FiltroFechaSalida(fecha1);

        List<Viaje> viajesFiltrados = filtro.filtrar(listaT);

        assertEquals(2, viajesFiltrados.size());
        assertTrue(viajesFiltrados.contains(viaje1));
        assertTrue(viajesFiltrados.contains(viaje3));
        assertFalse(viajesFiltrados.contains(viaje2));
    }
    
    @Test
    public void testFiltroFechaLlegadaFiltraCorrectamente() {
        FiltroFechaLlegada filtro = new FiltroFechaLlegada(fecha1);

        List<Viaje> viajesFiltrados = filtro.filtrar(listaT);

        assertEquals(2, viajesFiltrados.size());
        assertTrue(viajesFiltrados.contains(viaje1));
        assertTrue(viajesFiltrados.contains(viaje3));
        assertFalse(viajesFiltrados.contains(viaje2));
    }
    
    
    @Test
    public void testFiltroPuertoDestinoFiltraCorrectamente() {
        FiltroPuertoDestino filtro = new FiltroPuertoDestino(puerto1);


        List<Viaje> viajesFiltrados = filtro.filtrar(listaT);

        assertEquals(2, viajesFiltrados.size());
        assertTrue(viajesFiltrados.contains(viaje1));
        assertTrue(viajesFiltrados.contains(viaje3));
        assertFalse(viajesFiltrados.contains(viaje2));
    }

    @Test
    public void testOperadorANDFiltraCorrectamente() {
        FiltroFechaSalida filtro1 = new FiltroFechaSalida(fecha1);
        FiltroPuertoDestino filtro2 = new FiltroPuertoDestino(puerto1);

        OperadorAND operadorAND = new OperadorAND();
        operadorAND.agregar(filtro1);
        operadorAND.agregar(filtro2);

        List<Viaje> viajesFiltrados = operadorAND.filtrar(listaT);

        // Solo viaje1 y viaje3 cumplen ambos filtros
        assertEquals(2, viajesFiltrados.size());
        assertTrue(viajesFiltrados.contains(viaje1));
        assertTrue(viajesFiltrados.contains(viaje3));
    }

    @Test
    public void testOperadorORFiltraCorrectamente() {
        FiltroFechaSalida filtro1 = new FiltroFechaSalida(fecha1);
        FiltroPuertoDestino filtro2 = new FiltroPuertoDestino(puerto2);

        OperadorOR operadorOR = new OperadorOR();
        operadorOR.agregar(filtro1);
        operadorOR.agregar(filtro2);

        List<Viaje> viajesFiltrados = operadorOR.filtrar(listaT);

        assertEquals(3, viajesFiltrados.size());
        assertTrue(viajesFiltrados.contains(viaje1));
        assertTrue(viajesFiltrados.contains(viaje2));
        assertTrue(viajesFiltrados.contains(viaje3));
    }

    @Test
    public void testFiltrosNoSoportanAgregarRemover() {
        FiltroFechaSalida filtroS = new FiltroFechaSalida(fecha1);
        FiltroFechaLlegada filtroL = new FiltroFechaLlegada(fecha2);
        FiltroPuertoDestino filtrD = new FiltroPuertoDestino(puerto1);
        assertThrows(UnsupportedOperationException.class, () -> filtroS.agregar(mock(Busqueda.class)));
        assertThrows(UnsupportedOperationException.class, () -> filtroS.remover(mock(Busqueda.class)));
        assertThrows(UnsupportedOperationException.class, () -> filtroL.agregar(mock(Busqueda.class)));
        assertThrows(UnsupportedOperationException.class, () -> filtroL.remover(mock(Busqueda.class)));
        assertThrows(UnsupportedOperationException.class, () -> filtrD.agregar(mock(Busqueda.class)));
        assertThrows(UnsupportedOperationException.class, () -> filtrD.remover(mock(Busqueda.class)));
    }
    
    @Test
    public void testFiltrosAddYRemve() {
        FiltroFechaSalida filtroS = mock(FiltroFechaSalida.class);
        FiltroFechaLlegada filtroL = mock(FiltroFechaLlegada.class);
        FiltroPuertoDestino filtrD = mock(FiltroPuertoDestino.class);
        OperadorAND filtroAnd = new OperadorAND();
        OperadorOR filtroOr = new OperadorOR();
        filtroAnd.agregar(filtroOr);
        filtroOr.agregar(filtroAnd);
        filtroOr.agregar(filtroS);
        assertTrue(filtroAnd.getViajes().contains(filtroOr));
        assertTrue(filtroOr.getViajes().contains(filtroAnd));
        assertTrue(filtroOr.getViajes().contains(filtroS));
        filtroAnd.remover(filtroOr);
        filtroOr.remover(filtroAnd);
        filtroOr.remover(filtroS);
        assertFalse(filtroAnd.getViajes().contains(filtroOr));
        assertFalse(filtroOr.getViajes().contains(filtroAnd));
        assertFalse(filtroOr.getViajes().contains(filtroS));
        
    }
    
}
