package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
	private List <Ubicacion> ubicaciones;
	private List <Conexion> todasLasConexiones;
	private Map<Ubicacion,List<Conexion>> adyacencias;
	public Grafo() {
		this.ubicaciones= new ArrayList<>();
		this.todasLasConexiones= new ArrayList<>();
		this.adyacencias= new HashMap<>();
		}
	public void generarConexionesCompletas() {
	    this.todasLasConexiones.clear();
	    for (List<Conexion> lista : adyacencias.values()) {
	        lista.clear();
	    }

	    for (int i = 0; i < ubicaciones.size(); i++) {
	        for (int j = i + 1; j < ubicaciones.size(); j++) {
	            Conexion nueva = new Conexion(ubicaciones.get(i), ubicaciones.get(j));
	            this.agregarConexion(nueva);
	        }
	    }
	}

	

	public void agregarUbicacion(Ubicacion ubicacionActual) {
		if(!ubicaciones.contains(ubicacionActual)) {
			ubicaciones.add(ubicacionActual);
			adyacencias.put(ubicacionActual, new ArrayList<>());
		}
	}

	public List<Conexion> obtenerConexionesDesde(Ubicacion ubicacionActual) {
		List<Conexion> conexionesDesde= new ArrayList<>();
		conexionesDesde= adyacencias.getOrDefault(ubicacionActual,new ArrayList<>());
		return conexionesDesde;
	}

	public void agregarConexion(Conexion conexionMasBarata) {
		todasLasConexiones.add(conexionMasBarata);
		if (adyacencias.containsKey(conexionMasBarata.getUbicacionA())) {
	        adyacencias.get(conexionMasBarata.getUbicacionA()).add(conexionMasBarata);
	    }
	    if (adyacencias.containsKey(conexionMasBarata.getUbicacionB())) {
	        adyacencias.get(conexionMasBarata.getUbicacionB()).add(conexionMasBarata);
	    }
	}
	public List<Ubicacion> getUbicaciones() {
		return this.ubicaciones;
	}
	public List<Conexion> getConexiones(){
		return this.todasLasConexiones;
	}

}
