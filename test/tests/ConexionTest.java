package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Conexion;
import model.Ubicacion;

public class ConexionTest {

    @Test
    public void testCalculoCostoFinalSinAumentoProvincial() {
        Ubicacion u1 = new Ubicacion("A", "ProvinciaX", 0, 0);
        Ubicacion u2 = new Ubicacion("B", "ProvinciaX", 0, 1); 
        Conexion c = new Conexion(u1, u2);
        
        
        double costo = c.CalcularCostoFinal(10.0, 0.20, 5000.0);
        assertTrue("El costo debe ser mayor a 0", costo > 0);
    }

    @Test
    public void testCalculoCostoFinalConCruceProvincial() {
        Ubicacion u1 = new Ubicacion("A", "ProvinciaX", 0, 0);
        Ubicacion u2 = new Ubicacion("B", "ProvinciaY", 0, 1); 
        Conexion c = new Conexion(u1, u2);
        
        double costo = c.CalcularCostoFinal(10.0, 0.0, 5000.0); 
       
        assertTrue("Debe incluir el recargo provincial", costo > 5000.0);
    }

    @Test
    public void testGetDestino() {
        Ubicacion origen = new Ubicacion("San Miguel", "BA", 1, 1);
        Ubicacion destino = new Ubicacion("Moreno", "BA", 2, 2);
        Conexion c = new Conexion(origen, destino);
        
        assertEquals("Debe devolver el destino opuesto al origen", destino, c.getDestino(origen));
        assertEquals("Debe devolver el origen si le paso el destino", destino, c.getDestino(origen));
    }
}
