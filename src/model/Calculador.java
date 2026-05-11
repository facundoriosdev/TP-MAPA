package model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Calculador {
	public Calculador() {}
	
	public Grafo calcularCostoFibra(Grafo mapa, double costoKM, double porcentajePorExceso, double costoIntervencionProvincial) {
        Grafo arbolGeneradorMinimo = new Grafo();
        List<Ubicacion> todasLasLocalidades = mapa.getUbicaciones();
        
        if (todasLasLocalidades == null || todasLasLocalidades.isEmpty()) {
            return arbolGeneradorMinimo;
        }

        Set<Ubicacion> ubicacionesVisitadas = new HashSet<>();
        PriorityQueue<Conexion> conexionesDisponibles = new PriorityQueue<>(
            Comparator.comparingDouble(conexion -> 
                conexion.CalcularCostoFinal(costoKM, porcentajePorExceso, costoIntervencionProvincial)
            )
        );

 
        Ubicacion inicial = todasLasLocalidades.get(0);
        ubicacionesVisitadas.add(inicial);
        arbolGeneradorMinimo.agregarUbicacion(inicial);

    
        for (Conexion c : mapa.obtenerConexionesDesde(inicial)) {
            conexionesDisponibles.add(c);
        }

       
        while (ubicacionesVisitadas.size() < todasLasLocalidades.size() && !conexionesDisponibles.isEmpty()) {
            Conexion conexionMasBarata = conexionesDisponibles.poll();
            
            Ubicacion uA = conexionMasBarata.getUbicacionA();
            Ubicacion uB = conexionMasBarata.getUbicacionB();

            boolean visitadoA = ubicacionesVisitadas.contains(uA);
            boolean visitadoB = ubicacionesVisitadas.contains(uB);

            Ubicacion destino = null;

           
            if (visitadoA && !visitadoB) {
                destino = uB;
            } else if (!visitadoA && visitadoB) {
                destino = uA;
            }

           
            if (destino != null) {
                ubicacionesVisitadas.add(destino);
                arbolGeneradorMinimo.agregarUbicacion(destino);
                arbolGeneradorMinimo.agregarConexion(conexionMasBarata);

             
                for (Conexion c : mapa.obtenerConexionesDesde(destino)) {
                    
                    Ubicacion vecino = (c.getUbicacionA() == destino || c.getUbicacionA().equals(destino)) 
                                        ? c.getUbicacionB() 
                                        : c.getUbicacionA();

                    if (!ubicacionesVisitadas.contains(vecino)) {
                        conexionesDisponibles.add(c);
                    }
                }
            }
        }
        return arbolGeneradorMinimo;
    }
}
	
