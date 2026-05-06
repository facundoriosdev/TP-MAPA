package apis;

public class Config {
  private final static String apiCiudades = "https://infra.datos.gob.ar/catalog/modernizacion/dataset/7/distribution/7.4/download/municipios.json";
  private final static String apiUSD = "https://api.bluelytics.com.ar/v2/latest";
  private final static int maxCostPorKilometros = 500;
  private final static int crossProvinceCablingCost = 500;
  private final static double valorDolar = 1500;

  public static String getApiCiudades(){
    return apiCiudades;
  }

  public static String getApiUSD() {
    return apiUSD;
  }

  public static int getCrossProvinceCablingCost() {
    return crossProvinceCablingCost;
  }

  public static int getMaxCostPorKilometros() {
    return maxCostPorKilometros;
  }

  public static double valorDolar() {
    return valorDolar;
  }
}
