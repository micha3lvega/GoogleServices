/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soft.tecnology.googleservices.qbo;

import co.com.soft.tecnology.googleservices.util.Constants;
import com.google.maps.GeoApiContext;

/**
 *
 * @author m.vega.carrillo
 */
public class ContextFactory {

    public static GeoApiContext getContext() {
        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(Constants.KEY).build();
            return context;
        } catch (Exception e) {
            Constants.LOG.error("[ContextFactory] (getContext) Exception: " + e.getLocalizedMessage(), e);
            return null;
        }
    }

}
