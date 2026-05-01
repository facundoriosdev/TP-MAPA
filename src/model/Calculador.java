package model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Calculador {
	public Calculador() {}
	
	public Grafo calcularCostoFibra(Grafo mapa, double costoKM, double porcentajePorExceso, double costoIntervencionProvincial) {
		Grafo arbolGeneradorMinimo= new Grafo();
		List<Ubicacion> todasLasLocalidades= mapa.getUbicaciones();
		
		if(todasLasLocalidades== null) {
			return arbolGeneradorMinimo;
		}
		Set<Ubicacion> ubicacionesVisitadas= new HashSet<>();
		PriorityQueue<Conexion> conexionesDisponibles = new PriorityQueue<>(
	            Comparator.comparingDouble(conexion -> 
	                conexion.CalcularCostoFinal(costoKM, porcentajePorExceso, costoIntervencionProvincial)
	            )
	        );
		Ubicacion ubicacionActual=todasLasLocalidades.get(0);
		ubicacionesVisitadas.add(ubicacionActual);
		arbolGeneradorMinimo.agregarUbicacion(ubicacionActual);
		while(ubicacionesVisitadas.size()<todasLasLocalidades.size()) {
			for(Conexion c: arbolGeneradorMinimo.obtenerConexionesDesde(ubicacionActual)) {
				if(!ubicacionesVisitadas.contains(c.getDestino(ubicacionActual))) {
					conexionesDisponibles.add(c);
				}
				
			}
			Conexion conexionMasBarata=conexionesDisponibles.poll();
			Ubicacion nuevaUbicacion=conexionMasBarata.getDestino(ubicacionActual);
			if(!ubicacionesVisitadas.contains(nuevaUbicacion)) {
				ubicacionesVisitadas.add(nuevaUbicacion);
				arbolGeneradorMinimo.agregarUbicacion(nuevaUbicacion);
				arbolGeneradorMinimo.agregarConexion(conexionMasBarata);
			ubicacionActual= nuevaUbicacion;
			}
		
			
		}
		return arbolGeneradorMinimo;
	}
	
	}
