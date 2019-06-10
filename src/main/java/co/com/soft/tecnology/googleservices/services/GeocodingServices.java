package co.com.soft.tecnology.googleservices.services;

import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;

import co.com.soft.tecnology.googleservices.qbo.ContextFactory;
import co.com.soft.tecnology.googleservices.qbo.GoogleAddreess;

public class GeocodingServices {

  private static String key;
  private static GeocodingServices instace;
  private static GeoApiContext context;

  /**
   * protected constructor
   */
  protected GeocodingServices() {}

  /**
   * protected constructor
   */
  protected GeocodingServices(String key) {
    GeocodingServices.key = key;
  }

  public static GeocodingServices getInstace(String key) {
    if (GeocodingServices.instace == null) {
      GeocodingServices.instace = new GeocodingServices(key);
      GeocodingServices.context = ContextFactory.getContext(key);
    }
    return GeocodingServices.instace;
  }

  public GoogleAddreess geocodingAddress(String address)
      throws ApiException, InterruptedException, IOException, Exception {

    if (GeocodingServices.key == null) {
      throw new Exception("invalidate Key");
    }

    if ((address == null) || address.isEmpty()) {
      throw new Exception("invalidate address");
    }

    GoogleAddreess googleAddreess = new GoogleAddreess();

    System.out.println("address: " + address);
    if (address.contains("#")) {
      address = address.replace("#", "N");
      System.out.println("new address: " + address);
    }

    GeocodingResult[] results = GeocodingApi.geocode(GeocodingServices.context, address).await();
    new GsonBuilder().setPrettyPrinting().create();
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
