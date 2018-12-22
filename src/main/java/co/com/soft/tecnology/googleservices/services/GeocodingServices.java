/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soft.tecnology.googleservices.services;

import co.com.soft.tecnology.googleservices.qbo.ContextFactory;
import co.com.soft.tecnology.googleservices.qbo.GoogleAddreess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import java.io.IOException;

/**
 *
 * @author micha3lvega
 */
public class GeocodingServices {

    public static GoogleAddreess geocodingAddress(String address) throws ApiException, InterruptedException, IOException {

        GoogleAddreess googleAddreess = new GoogleAddreess();

        GeoApiContext context = ContextFactory.getContext();

        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (GeocodingResult result : results) {

            googleAddreess.setAddress(address);
            googleAddreess.setFormattedAddress(result.formattedAddress);
            googleAddreess.setPlaceId(result.placeId);

            AddressComponent[] addressComponents = result.addressComponents;

            for (AddressComponent addressComponent : addressComponents) {
                AddressComponentType[] types = addressComponent.types;

                for (AddressComponentType type : types) {

                    switch (type.name()) {
                        case "ADMINISTRATIVE_AREA_LEVEL_1":
                            googleAddreess.setCity(addressComponent.longName);
                            googleAddreess.setShortCity(addressComponent.shortName);
                            break;
                        case "POLITICAL":
                            googleAddreess.setCountry(addressComponent.longName);
                            googleAddreess.setShortCountry(addressComponent.shortName);
                            break;
                        case "POSTAL_CODE":
                            googleAddreess.setPostalCode(addressComponent.longName);
                            break;
                        case "LOCALITY":
                            googleAddreess.setZone(addressComponent.longName);
                            break;
                    }
                }
            }

            Geometry geometry = result.geometry;
            googleAddreess.setLat(geometry.location.lat);
            googleAddreess.setLng(geometry.location.lng);

        }

        return googleAddreess;
    }

}
