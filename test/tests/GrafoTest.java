package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Conexion;
import model.Grafo;
import model.Ubicacion;

public class GrafoTest {

	@Test
    public void testAgregarUbicacionEvitaDuplicados() {
        Grafo grafo = new Grafo();
        Ubicacion u1 = new Ubicacion("CABA", "CABA", -34.6, -58.3);
        Ubicacion u2 = new Ubicacion("CABA", "CABA", -34.6, -58.3); 
        
        grafo.agregarUbicacion(u1);
        grafo.agregarUbicacion(u2);
        
        assertEquals("El grafo solo debería tener 1 ubicación", 1, grafo.getUbicaciones().size());
    }

    @Test
    public void testAgregarConexionBidireccional() {
        Grafo grafo = new Grafo();
        Ubicacion u1 = new Ubicacion("NodoA", "Provincia", 1, 1);
        Ubicacion u2 = new Ubicacion("NodoB", "Provincia", 2, 2);
        grafo.agregarUbicacion(u1);
        grafo.agregarUbicacion(u2);
        
        grafo.agregarConexion(new Conexion(u1, u2));
        
        assertEquals("NodoA debe tener 1 conexion", 1, grafo.obtenerConexionesDesde(u1).size());
        assertEquals("NodoB debe tener 1 conexion", 1, grafo.obtenerConexionesDesde(u2).size());
    }

    @Test
    public void testGenerarConexionesCompletas() {
        Grafo grafo = new Grafo();
        grafo.agregarUbicacion(new Ubicacion("A", "Prov", 1, 1));
        grafo.agregarUbicacion(new Ubicacion("B", "Prov", 2, 2));
        grafo.agregarUbicacion(new Ubicacion("C", "Prov", 3, 3));
        grafo.agregarUbicacion(new Ubicacion("D", "Prov", 4, 4));
        
        grafo.generarConexionesCompletas();
        
        assertEquals("Deberían generarse 6 conexiones", 6, grafo.getConexiones().size());
    }
}
