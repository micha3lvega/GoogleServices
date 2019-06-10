/* To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor. */
package co.com.soft.tecnology.googleservices.qbo;

import com.google.maps.GeoApiContext;

/**
 *
 * @author m.vega.carrillo
 */
public class ContextFactory {


  private static GeoApiContext context;

  public static GeoApiContext getContext(String key) {

    if (ContextFactory.context == null) {
      ContextFactory.context = new GeoApiContext.Builder().apiKey(key).build();
    }
    return ContextFactory.context;

  }

}
