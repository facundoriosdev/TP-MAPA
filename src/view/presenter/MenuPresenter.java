package view.presenter;

import view2.Menu;
import view.navegador.*;

public class MenuPresenter {
    private Menu view;
    private Navegador navigator;

    public MenuPresenter(Menu view, Navegador navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    public void onIniciarPulsado() {
        
        String sCostoKm = view.getCostoKm();
        String sCostoInter = view.getCostoInterprovincial();
        String sAumento = view.getPorcentajeAumento();


        if (sCostoKm.isEmpty() || sCostoInter.isEmpty() || sAumento.isEmpty()) {
            view.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        try {
            double costoKm = Double.parseDouble(sCostoKm);
            double costoInter = Double.parseDouble(sCostoInter);
            double aumento = Double.parseDouble(sAumento);

           
            if (costoKm <= 0) {
                view.mostrarMensaje("El costo por kilómetro debe ser mayor a cero.");
                return;
            }
            
            if (aumento < 0 || aumento > 100) {
                view.mostrarMensaje("El porcentaje de aumento debe estar entre 0 y 100.");
                return;
            }

            navigator.lanzarConexion(costoKm, aumento, costoInter);
            
          
            view.cerrar();

        } catch (NumberFormatException e) {
            view.mostrarMensaje("Error: Ingrese valores numéricos válidos (use punto para decimales).");
        } catch (Exception e) {
            view.mostrarMensaje("Error inesperado al iniciar el sistema: " + e.getMessage());
        }
    }
}