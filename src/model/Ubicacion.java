package model;

public class Ubicacion {
	private String provincia;
	private String localidad;
	private double latitud;
	private double longitud;
	public Ubicacion(String provincia, String localidad, double latitud, double longitud) {
		this.provincia= provincia;
		this.localidad=localidad;
		this.latitud=latitud;
		this.longitud=longitud;
	}

	public String getProvincia() {
		
		return this.provincia;
	}
	public String localidad() {
		return this.localidad;
	}

	public double getLongitud() {
		
		return this.longitud;
	}

	public double getLatitud() {
		
		return this.latitud;
	}

}
