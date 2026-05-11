package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Ubicacion;

public class UbicacionTest {

    @Test
    public void testCreacionUbicacion() {
        Ubicacion u = new Ubicacion("Buenos Aires", "José C. Paz", -34.51, -58.76);
        assertEquals("Buenos Aires", u.getProvincia());
        assertEquals("José C. Paz", u.getlocalidad());
    }

    @Test
    public void testEqualsMismaUbicacion() {
        Ubicacion u1 = new Ubicacion("Luján", "Buenos Aires", -34.56, -59.10);
        Ubicacion u2 = new Ubicacion("Luján", "Buenos Aires", -34.56, -59.10);
        
        assertTrue("Dos ubicaciones con el mismo nombre y provincia deben ser iguales", u1.equals(u2));
    }

    @Test
    public void testEqualsDistintaUbicacion() {
        Ubicacion u1 = new Ubicacion("Tucumán", "Tucumán", -26.80, -65.21);
        Ubicacion u2 = new Ubicacion("Salta", "Salta", -24.78, -65.41);
        
        assertFalse("Ubicaciones con distintos datos no deben ser iguales", u1.equals(u2));
    }
}
