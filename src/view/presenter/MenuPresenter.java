package view.presenter;

import view.MenuFrame;
import view.Navegador;

public class MenuPresenter {
	private MenuFrame view;
	private Navegador nav;
	
	public MenuPresenter(MenuFrame view, Navegador nav) {
		this.view = view;
		this.nav = nav;
		view.setPresenter(this);
	}

	public void seleccionarCiudades() {
		double[] values = view.getSliderValues();
		if (values[0] == 0) {
			view.mostrarMensaje("Debe seleccionar el costo por kilometro");
			return;
		}
		try {
			view.cerrar();
			nav.lanzarConexion(values[0], values[1], values[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
