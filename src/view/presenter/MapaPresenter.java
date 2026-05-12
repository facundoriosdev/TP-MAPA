package view.presenter;

import model.Grafo;

import java.util.Arrays;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import model.Calculador;
import model.Conexion;
import model.Ubicacion;
import view2.Mapa;

public class MapaPresenter {
    private Mapa view;
    private Grafo grafoFinal;

    public MapaPresenter(Mapa view, Grafo grafoCompleto, double cKm, double aument, double cInter) {
        this.view = view;
        
        Calculador calc = new Calculador();
        this.grafoFinal = calc.calcularCostoFibra(grafoCompleto, cKm, aument, cInter);
        
        mostrarEnMapa(cKm, aument, cInter);
    }

    private void mostrarEnMapa(double cKm, double aument, double cInter) {
        double costoTotal = 0;

        for (Ubicacion u : grafoFinal.getUbicaciones()) {
            Coordinate coord = new Coordinate(u.getLatitud(), u.getLongitud());
            view.agregarMarcador(new MapMarkerDot(u.getlocalidad(), coord)); 
        }
        for (Conexion c : grafoFinal.getConexiones()) {
            Coordinate c1 = new Coordinate(c.getUbicacionA().getLatitud(), c.getUbicacionA().getLongitud());
            Coordinate c2 = new Coordinate(c.getUbicacionB().getLatitud(), c.getUbicacionB().getLongitud());
            
            view.agregarPoligono(new MapPolygonImpl(Arrays.asList(c1, c2, c2)));
            costoTotal += c.CalcularCostoFinal(cKm, aument, cInter);
        }
        view.actualizarInfoCostos("Costo Total de la Red: $" + String.format("%.2f", costoTotal));
        
    }
}