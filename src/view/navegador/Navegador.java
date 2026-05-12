package view.navegador;

import javax.swing.JFrame;

import model.Grafo;
import view.presenter.MapaPresenter;
import view.presenter.MenuPresenter;
import view.presenter.UbicacionPresenter;
import view2.Mapa;
import view2.Menu;
import view2.EntradaUbicaciones;

public class Navegador {

    
    public void lanzarMenu() {
        Menu vista = new Menu();
        MenuPresenter presenter = new MenuPresenter(vista, this);
        
        vista.setPresenter(presenter);
        configurarVentana(vista.getFrame()); 
    }

    
    public void lanzarConexion(double costoKm, double aumento, double costoInter) {
EntradaUbicaciones vista = new EntradaUbicaciones();
        
       
        UbicacionPresenter presenter = new UbicacionPresenter(vista, this, costoKm, aumento, costoInter);
        
 
        vista.setPresenter(presenter);
        
   
        configurarVentana(vista.getFrame());
    }

 
    private void configurarVentana(JFrame frame) {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    public void lanzarMapa(Grafo grafoCompleto, double cKm, double aument, double cInter) {
        Mapa vista = new Mapa();
        MapaPresenter presenter = new MapaPresenter(vista, grafoCompleto, cKm, aument, cInter);
        vista.setPresenter(presenter);
        
        vista.getFrame().setVisible(true);
        vista.getFrame().setLocationRelativeTo(null);
    }
}