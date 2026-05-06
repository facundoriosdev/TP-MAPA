package apis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ApiDolar {
  private final String apiUsd = Config.getApiUSD();
  private double dolarDefault = Config.valorDolar();

  public double getValorDolar(){
    try {
      String json = getJsonFromApi();
      return parseDolar(json);
    }catch (Exception e){
      System.out.println("Error al obtener el valor del dolar");
      return dolarDefault;
    }
  }

  private String getJsonFromApi() throws Exception{
    URL url = new URL(apiUsd);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line;
    while((line = reader.readLine()) != null)
      response.append(line);
    reader.close();
    conn.disconnect();
    return response.toString();
  }

  private double parseDolar(String json){
    JSONObject jsonObj = new JSONObject(json);
    JSONObject oficial = jsonObj.getJSONObject("oficial");
    return oficial.getDouble("value_avg");
  }
}
