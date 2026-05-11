package apis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import model.Ubicacion;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ApiCiudades {
  private final String apiCiudades = Config.getApiCiudades();
  private JSONArray municipios;

  public List<Ubicacion> getUbicacion() throws IOException, JSONException{
    List<Ubicacion> localidades = new ArrayList<>();
    JSONArray municipios = getMunicipios();
    for(int i = 0; i < municipios.length(); i++){
      JSONObject municipio = municipios.getJSONObject(i);
      String nombre = municipio.getString("nombre");
      String provincia = municipio.getJSONObject("provincia").getString("nombre");
      double latitud = municipio.getJSONObject("centroide").getDouble("lat");
      double longitud = municipio.getJSONObject("centroide").getDouble("lon");
      Ubicacion nuevaUbicacion= new Ubicacion(provincia, nombre, latitud,longitud);
      localidades.add(nuevaUbicacion);
    }
    localidades.sort(Comparator.comparing(u -> u.getlocalidad().toUpperCase()));
    return localidades;
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
    try (Scanner scanner = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\a")){
      String jsonString = scanner.hasNext()? scanner.next() : "";
      return new JSONObject(jsonString);
    }
  }
}
