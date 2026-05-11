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

    // Abre la pantalla inicial
    public void lanzarMenu() {
        Menu vista = new Menu();
        MenuPresenter presenter = new MenuPresenter(vista, this);
        
        vista.setPresenter(presenter);
        configurarVentana(vista.getFrame()); // Usamos el frame interno de tu clase
    }

    // Abre la pantalla donde se cargan las ciudades y se calcula el MST
    public void lanzarConexion(double costoKm, double aumento, double costoInter) {
EntradaUbicaciones vista = new EntradaUbicaciones();
        
        // 2. Creamos su Presenter pasando los costos que vienen del Menu
        UbicacionPresenter presenter = new UbicacionPresenter(vista, this, costoKm, aumento, costoInter);
        
        // 3. Vinculamos
        vista.setPresenter(presenter);
        
        // 4. Mostramos
        configurarVentana(vista.getFrame());
    }

    // Método auxiliar para no repetir código de centrado y visibilidad
    private void configurarVentana(JFrame frame) {
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // La centra en la pantalla
    }
    public void lanzarMapa(Grafo grafoCompleto, double cKm, double aument, double cInter) {
        Mapa vista = new Mapa();
        MapaPresenter presenter = new MapaPresenter(vista, grafoCompleto, cKm, aument, cInter);
        vista.setPresenter(presenter);
        
        vista.getFrame().setVisible(true);
        vista.getFrame().setLocationRelativeTo(null);
    }
}