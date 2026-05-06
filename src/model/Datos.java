package model;
import org.json.JSONException;

import apis.ApiCiudades;

import java.io.IOException;
import java.util.List;

public class Datos{

  private List<Ciudad> ciudades;
  private List<String> provincias;

  public Datos() throws IOException, JSONException {
    ApiCiudades apiCiudades = new ApiCiudades();
    ciudades = apiCiudades.getCiudades();
    provincias = apiCiudades.getProvincias();
  }

  public List<Ciudad> getCiudades() {
    return ciudades;
  }

  public List<String> getProvincias() {
    return provincias;
  }
}


