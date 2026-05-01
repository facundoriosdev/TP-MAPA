package model;

public class Conexion {
	private Ubicacion ubicacionA;
	private Ubicacion ubicacionB;
	public Conexion(Ubicacion ubicacionA, Ubicacion ubicacionB) {
		this.ubicacionA= ubicacionA;
		this.ubicacionB= ubicacionB;
	}

	public double CalcularCostoFinal(double costoKM, double porcentajePorExceso, double costoIntervencionProvincial) {
		double distancia= calcularDistancia(ubicacionA,ubicacionB);
		double costo= costoKM*distancia;
		if(distancia>300) {
			costo=costo+(costo*(porcentajePorExceso/100.0));
		}
		if(!ubicacionA.getProvincia().equals(ubicacionB.getProvincia())) {
			costo+= costoIntervencionProvincial;
		}
		return costo;
	}

	private double calcularDistancia(Ubicacion ubicacionA, Ubicacion ubicacionB) {
		// Radio promedio de la Tierra en kilómetros
	    final int RADIO_TIERRA = 6371;

	    // 1. Obtener las latitudes y longitudes
	    double lat1 = ubicacionA.getLatitud();
	    double lon1 = ubicacionA.getLongitud();
	    double lat2 = ubicacionB.getLatitud();
	    double lon2 = ubicacionB.getLongitud();

	    // 2. Convertir los grados a radianes (¡Súper importante! Java usa radianes para trigonometría)
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);

	    // 3. Aplicar la fórmula de Haversine
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    // 4. Calcular la distancia final
	    double distancia = RADIO_TIERRA * c;

	    return distancia;
	}
	public Ubicacion getUbicacionA() {
		return this.ubicacionA;
	}
	public Ubicacion getUbicacionB() {
		return this.ubicacionB;
	}



	public Ubicacion getDestino(Ubicacion ubicacionActual) {
		if(this.ubicacionA.equals(ubicacionActual)) {
			return this.ubicacionA;
		}
		return this.ubicacionB;
	}

}
