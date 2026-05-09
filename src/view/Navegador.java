package view;

import javax.swing.JFrame;
import view.presenter.MenuPresenter;

public class Navegador {
	
	public void lanzarMenu() {
		MenuFrame menu = new MenuFrame();
		MenuPresenter presenter = new MenuPresenter(menu, this);
		ajustesPantalla(menu);
	}
	
	public void lanzarConexion(double costPerKilometer, double increaseLongDistanceCost, double fixedCrossProvincialCost) throws Exception {
		ConexionFrame conexion = new ConexionFrame(costPerKilometer, increaseLongDistanceCost, fixedCrossProvincialCost);
		ajustesPantalla(conexion);
	}
	
	public void lanzarMapa() {
		MapaFrame mapa = new MapaFrame();
		ajustesPantalla(mapa);
	}
	
	private void ajustesPantalla(JFrame frame) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
