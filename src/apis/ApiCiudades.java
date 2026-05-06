package apis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Ciudad;
import model.ConectarCiudades;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ApiCiudades {
  private final String apiCiudades = Config.getApiCiudades();
  private JSONArray municipios;

  public List<Ciudad> getCiudades() throws IOException, JSONException{
    List<Ciudad> ciudades = new ArrayList<>();
    ConectarCiudades coneccion = new ConectarCiudades();
    JSONArray municipios = getMunicipios();
    for(int i = 0; i < municipios.length(); i++){
      JSONObject municipio = municipios.getJSONObject(i);
      String nombre = municipio.getString("nombre");
      String provincia = municipio.getJSONObject("provincia").getString("nombre");
      double latitud = municipio.getJSONObject("centroide").getDouble("lat");
      double longitud = municipio.getJSONObject("centroide").getDouble("lon");
      Ciudad ciudad = coneccion.crearCiudadFromApi(nombre, provincia, latitud, longitud);
      ciudades.add(ciudad);
    }
    ciudades.sort(Comparator.comparing(c -> c.getNombre().toUpperCase()));
    return ciudades;
  }

  public List<String> getProvincias() throws IOException, JSONException{
    Set<String> provincias = new HashSet<>();
    JSONArray municipios = getMunicipios();
    for(int i = 0; i < municipios.length(); i++){
      JSONObject municipio = municipios.getJSONObject(i);
      String provincia = municipio.getJSONObject("provincia").getString("nombre");
      provincias.add(provincia);
    }
    return new ArrayList<>(provincias);
  }

  private JSONArray getMunicipios() throws IOException, JSONException{
    if(municipios == null){
      JSONObject json = leerJsonDeUrl(apiCiudades);
      municipios = json.getJSONArray("municipios");
    }
    return municipios;
  }

  private JSONObject leerJsonDeUrl(String urlApi) throws IOException, JSONException{
    URL url = new URL(urlApi);
    try (Scanner scanner = new Scanner(url.openStream(), "URF-8").useDelimiter("\\a")){
      String jsonString = scanner.hasNext()? scanner.next() : "";
      return new JSONObject(jsonString);
    }
  }
}
