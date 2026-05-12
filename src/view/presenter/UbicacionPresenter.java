package view.presenter;

import java.util.List;

import apis.ApiCiudades;
import model.Grafo;
import view.navegador.Navegador;
import view2.EntradaUbicaciones;
import model.Ubicacion;

public class UbicacionPresenter {
	private EntradaUbicaciones view;
    private Navegador navigator;
    private Grafo grafo;
    private List<Ubicacion> listaCompletaApi;
    
    private double costoKm;
    private double aumento;
    private double costoInter;

    public UbicacionPresenter(EntradaUbicaciones view, Navegador navigator,double costoKm, double aumento, double costoInter) {
        this.view = view;
        this.navigator = navigator;
        this.costoKm=costoKm;
        this.aumento = aumento;
        this.costoInter = costoInter;
        this.grafo = new Grafo();
        cargarDatosIniciales();
        }
    private void cargarDatosIniciales() {
        try {
            ApiCiudades api = new ApiCiudades();
            
            this.listaCompletaApi = api.getUbicacion(); 
           
            String[] nombresParaCombo = listaCompletaApi.stream()
                .map(u -> u.getlocalidad() + " (" + u.getProvincia() + ")").toArray(String[]::new);
            
            view.cargarCombo(nombresParaCombo);
        } catch (Exception e) {
            view.mostrarError("No se pudo cargar la lista de ciudades: " + e.getMessage());
        }
    }

	public void eliminarCiudadPulsado(int filaSeleccionada) {
		if (filaSeleccionada == -1) {
            view.mostrarError("Seleccione una ciudad de la tabla para eliminarla.");
            return;
        }
		Ubicacion aEliminar = grafo.getUbicaciones().get(filaSeleccionada);
		grafo.getUbicaciones().remove(aEliminar);
		view.eliminarFilaTabla(filaSeleccionada);
		
	}

	public void agregarCiudadPulsado(int indice) {
		if (indice < 0) return;

        Ubicacion seleccionada = listaCompletaApi.get(indice);

        if (grafo.getUbicaciones().contains(seleccionada)) {
            view.mostrarError("La ciudad " + seleccionada.getlocalidad() + " ya está en la lista.");
            return;
        }

        grafo.agregarUbicacion(seleccionada);
        
   
        view.agregarFilaTabla(seleccionada.getlocalidad(), seleccionada.getProvincia());
    }
	
	public void agregarCiudadManualPulsado(String ciudad, String provincia, String latStr, String lonStr) {
	    // 1. Validar que los campos no estén vacíos
	    if (ciudad.isEmpty() || provincia.isEmpty() || latStr.isEmpty() || lonStr.isEmpty()) {
	        view.mostrarError("Todos los campos manuales son obligatorios.");
	        return;
	    }

	    double latitud;
	    double longitud;

	    // 2. Validar que latitud y longitud sean números válidos
	    try {
	        latitud = Double.parseDouble(latStr);
	        longitud = Double.parseDouble(lonStr);
	    } catch (NumberFormatException e) {
	        view.mostrarError("La latitud y longitud deben ser valores numéricos .");
	        return;
	    }
	    
	    if (latitud <= -54 || latitud >= -22) {
	        view.mostrarError("La latitud ingresada (" + latitud + ") está fuera del rango permitido (-54 < x < -22).");
	        return;
	    }

	    if (longitud <= -70 || longitud >= -53) {
	        view.mostrarError("La longitud ingresada (" + longitud + ") está fuera del rango permitido (-70 < x < -53).");
	        return;
	    }

	    // 3. Crear el objeto Ubicacion
	    Ubicacion nuevaUbicacion = new Ubicacion(provincia, ciudad, latitud, longitud);

	    // 4. Validar que no exista ya en el grafo
	    if (grafo.getUbicaciones().contains(nuevaUbicacion)) {
	        view.mostrarError("La ciudad " + ciudad + " ya está en la lista.");
	        return;
	    }

	    // 5. Agregar al grafo (Modelo) y a la tabla (Vista)
	    grafo.agregarUbicacion(nuevaUbicacion);
	    view.agregarFilaTabla(ciudad, provincia);
	}
		
	

	public void generarConexionPulsado() {
List<Ubicacion> seleccionadas = grafo.getUbicaciones();
        
        if (seleccionadas.size() < 2) {
            view.mostrarError("Se necesitan al menos 2 ciudades para conectar la red.");
            return;
        }
        grafo.generarConexionesCompletas();
        //System.out.println("Cables creados antes de Prim: " + grafo.getConexiones().size());
        navigator.lanzarMapa(grafo, costoKm, aumento, costoInter);
		
	}

}
