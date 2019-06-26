package co.com.soft.tecnology.googleservices.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import co.com.soft.tecnology.googleservices.GoogleApiException;
import co.com.soft.tecnology.googleservices.qbo.ContextFactory;

public class RouteServices {

  public final String URL_API = "https://maps.googleapis.com/maps/api/directions/json";

  private LatLng startRoute;
  private LatLng endRoute;
  private String key;
  private List<String> points;
  private Boolean optimized;

  public RouteServices(double startLat, double startLog, double endLat, double endLog, String key) {

    this.startRoute = new LatLng(startLat, startLog);
    this.endRoute = new LatLng(endLat, endLog);
    this.key = key;

  }

  public DirectionsResult calculateRoute() throws GoogleApiException {

    if (this.key == null) {
      throw new GoogleApiException("invalidate Key");
    }

    if (this.optimized == null) {
      System.out.println("Optimizacion por defecto");
      this.optimized = true;
    }

    DirectionsApiRequest request = DirectionsApi.newRequest(ContextFactory.getContext(this.key))
        .origin(this.startRoute).destination(this.endRoute).mode(TravelMode.DRIVING);

    StringBuilder waypoints = null;
    if (this.points != null) {
      waypoints = new StringBuilder();
      for (String waypoint : this.points) {
        waypoints.append(waypoint);
        waypoints.append("|");
      }
    }

    if ((waypoints != null) && !waypoints.toString().isEmpty()) {
      request.waypoints(waypoints.toString());
    }

    request.optimizeWaypoints(true);

    try {
      return request.await();
    } catch (ApiException | InterruptedException | IOException e) {
      throw new GoogleApiException(e.getMessage());
    }

  }



  /**
   * @return the endRoute
   */
  public LatLng getEndRoute() {
    return this.endRoute;
  }

  /**
   * @param endRoute the endRoute to set
   */
  public void setEndRoute(LatLng endRoute) {
    this.endRoute = endRoute;
  }

  /**
   * @param key the key to set
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @return the startRoute
   */
  public LatLng getStartRoute() {
    return this.startRoute;
  }

  /**
   * @param startRoute the startRoute to set
   */
  public void setStartRoute(LatLng startRoute) {
    this.startRoute = startRoute;
  }

  /**
   * @return the points
   */
  public List<String> getPoints() {
    return this.points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(List<String> points) {
    this.points = points;
  }

  public void addPoint(double lat, double log) {

    if (this.points == null) {
      this.points = new ArrayList<>();
    }

    this.points.add(lat + "," + log);

  }

  /**
   * @return the optimized
   */
  public Boolean getOptimized() {
    return this.optimized;
  }

  /**
   * @param optimized the optimized to set
   */
  public void setOptimized(Boolean optimized) {
    this.optimized = optimized;
  }



}
