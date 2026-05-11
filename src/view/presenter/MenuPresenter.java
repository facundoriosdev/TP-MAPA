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

    /**
     * Este es el método que llama la vista cuando se pulsa el botón.
     * Aquí es donde vive la "inteligencia" de la pantalla de inicio.
     */
    public void onIniciarPulsado() {
        // 1. Obtenemos los strings crudos de la vista
        String sCostoKm = view.getCostoKm();
        String sCostoInter = view.getCostoInterprovincial();
        String sAumento = view.getPorcentajeAumento();

        // 2. Validación de campos vacíos (Lógica de control)
        if (sCostoKm.isEmpty() || sCostoInter.isEmpty() || sAumento.isEmpty()) {
            view.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        try {
            // 3. Conversión de tipos
            double costoKm = Double.parseDouble(sCostoKm);
            double costoInter = Double.parseDouble(sCostoInter);
            double aumento = Double.parseDouble(sAumento);

            // 4. Validaciones de Negocio (Reglas de la UNGS)
            if (costoKm <= 0) {
                view.mostrarMensaje("El costo por kilómetro debe ser mayor a cero.");
                return;
            }
            
            if (aumento < 0 || aumento > 100) {
                view.mostrarMensaje("El porcentaje de aumento debe estar entre 0 y 100.");
                return;
            }

            // 5. Navegación: Si todo está OK, pasamos a la siguiente etapa.
            // Usamos el lanzador para abrir la pantalla de selección de ciudades.
            navigator.lanzarConexion(costoKm, aumento, costoInter);
            
            // 6. Cerramos la vista actual
            view.cerrar();

        } catch (NumberFormatException e) {
            // Si el usuario metió letras en vez de números
            view.mostrarMensaje("Error: Ingrese valores numéricos válidos (use punto para decimales).");
        } catch (Exception e) {
            view.mostrarMensaje("Error inesperado al iniciar el sistema: " + e.getMessage());
        }
    }
}