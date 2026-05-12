package view2;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import model.Ubicacion;
import model.Conexion;
import view.presenter.MapaPresenter;

public class Mapa	 {
	private JFrame frame;
    private JMapViewer mapa;
    private JLabel lblCostos;
    private MapaPresenter presenter;

    public Mapa() {
    	initialize();
    }
    
    private void initialize() {
    	frame = new JFrame(); 
        frame.setTitle("Mapa de Conexiones de Fibra Óptica");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        mapa = new JMapViewer();
        frame.getContentPane().add(mapa, BorderLayout.CENTER);
        
        Coordinate coordenada = new Coordinate(-34.521, -58.719);
        mapa.setDisplayPosition(coordenada, 5);

        JPanel panelInfo = new JPanel();
        lblCostos = new JLabel("Calculando costos...");
        panelInfo.add(lblCostos);
        frame.getContentPane().add(panelInfo, BorderLayout.SOUTH);
    }

    public void setPresenter(MapaPresenter presenter) {
        this.presenter = presenter;
    }

    public void dibujarUbicacion(double lat, double lon, String nombre) {
        mapa.addMapMarker(new MapMarkerDot(nombre, new Coordinate(lat, lon)));
    }

    public void agregarPoligono(MapPolygonImpl poligono) {
        mapa.addMapPolygon(poligono);
    }

    public void agregarMarcador(MapMarkerDot marcador) {
        mapa.addMapMarker(marcador);
    }

    public void actualizarInfoCostos(String texto) {
        lblCostos.setText(texto);
    }
  
    
    public JFrame getFrame() { return frame; }
}