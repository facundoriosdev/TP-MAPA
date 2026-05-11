package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Calculador;
import model.Grafo;
import model.Ubicacion;

public class CalculadorTest {

    @Test
    
    public void testCalcularCostoFibraGrafoVacio() {
        Calculador calc = new Calculador();
        Grafo vacio = new Grafo();
        
        Grafo arbol = calc.calcularCostoFibra(vacio, 10, 0, 0);
        assertEquals("El arbol minimo de un grafo vacio debe tener 0 ubicaciones", 0, arbol.getUbicaciones().size());
    }

    @Test
    public void testCalcularCostoFibraSoloUnNodo() {
        Calculador calc = new Calculador();
        Grafo unNodo = new Grafo();
        unNodo.agregarUbicacion(new Ubicacion("Solitario", "Prov", 0, 0));
        
        Grafo arbol = calc.calcularCostoFibra(unNodo, 10, 0, 0);
        assertEquals("El arbol debe devolver el mismo nodo", 1, arbol.getUbicaciones().size());
        assertEquals("No debe tener conexiones", 0, arbol.getConexiones().size());
    }

    @Test
    public void testPrimEligeConexionMasBarata() {
        Calculador calc = new Calculador();
        Grafo completo = new Grafo();
        Ubicacion u1 = new Ubicacion("A", "Prov", -34.0, -58.0);
        Ubicacion u2 = new Ubicacion("B", "Prov", -34.1, -58.0); 
        Ubicacion u3 = new Ubicacion("C", "Prov", -39.0, -60.0); 
        
        completo.agregarUbicacion(u1);
        completo.agregarUbicacion(u2);
        completo.agregarUbicacion(u3);
        completo.generarConexionesCompletas();
        
        Grafo arbol = calc.calcularCostoFibra(completo, 1.0, 0.0, 0.0);
        
        
        assertEquals("El arbol minimo debe conectar todos los nodos", 3, arbol.getUbicaciones().size());
        assertEquals("El arbol minimo debe tener exactamente 2 conexiones", 2, arbol.getConexiones().size());
    }
}