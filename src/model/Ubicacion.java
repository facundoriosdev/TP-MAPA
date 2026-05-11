package model;

import java.util.Objects;

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
	public String getlocalidad() {
		return localidad;
	}

	public double getLongitud() {
		
		return this.longitud;
	}

	public double getLatitud() {
		
		return this.latitud;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Ubicacion that = (Ubicacion) o;
	    // Comparamos por nombre y provincia para asegurar identidad
	    return localidad.equals(that.localidad) && provincia.equals(that.provincia);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(localidad, provincia);
	}

}
