package model;
import org.json.JSONException;

import apis.ApiDolar;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.Normalizer;
import java.util.List;

public class ConectarCiudades {
  private Datos datos;
  private List<String> provinciaValida;
  private double costoPorKilometro;
  private double incrementarCostoLargaDistancia;
  private double costoInterprovincial;
  private double dolarOficial;

  public ConectarCiudades(double costoPorKilometro, double incrementarCostoLargaDistancia, double costoInterprovincial) throws Exception{
    this.costoPorKilometro = costoPorKilometro;
    this.incrementarCostoLargaDistancia = incrementarCostoLargaDistancia;
    this.costoInterprovincial = costoInterprovincial;
    this.datos = new Datos();
    this.dolarOficial = new ApiDolar().getValorDolar();
    this.provinciaValida = datos.getProvincias();
  }

  public ConectarCiudades(){
  }

  public double getDolarOficial(){
    return dolarOficial;
  }

  public List<Ciudad> buscarCiudad() throws JSONException, IOException{
    return datos.getCiudades();
  }

  public Ciudad crearCiudad(String nombre, String provincia, double latitud, double longitud) throws InvalidParameterException, JSONException, IOException{
    if(!validarParametrosCiudad(nombre, provincia, latitud, longitud))
      throw new InvalidParameterException("Parametros invalidos para la ciudad");
    return new Ciudad(nombre, provincia, latitud, longitud);
  }

  public Ciudad crearCiudadFromApi(String nombre, String provincia, double latitud, double longitud){
    return new Ciudad(nombre, provincia, latitud, longitud);
  }

  public boolean validarParametrosCiudad(String nombre, String provincia, double latitud, double longitud) throws JSONException, IOException{
    if(latitud < -54 || latitud > -22 || longitud < -70 || longitud > -53)
      return false;
    for(String p: provinciaValida)
      if(normalize(p).equalsIgnoreCase(normalize(provincia)))
        return true;
    return false;
  }

  private String normalize(String text){
    return Normalizer.normalize(text, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
            .toLowerCase();
  }
}
