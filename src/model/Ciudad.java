package model;
import java.util.Objects;

public class Ciudad {
  private String nombre; //Localidad
  private String provincia;
  private double latitud;
  private double longitud;

  public Ciudad(String nombre, String provincia, double latitud, double longitud){
    this.nombre = nombre;
    this.provincia = provincia;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public double getLatitud() {
    return latitud;
  }

  public void setLatitud(double latitud) {
    this.latitud = latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public void setLongitud(double longitud) {
    this.longitud = longitud;
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitud, longitud, nombre, provincia);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Ciudad other = (Ciudad) obj;

    return Double.doubleToLongBits(latitud) == Double.doubleToLongBits(other.latitud)
            && Double.doubleToLongBits(longitud) == Double.doubleToLongBits(other.longitud)
            && Objects.equals(nombre, other.nombre) && Objects.equals(provincia, other.provincia);
  }
}
