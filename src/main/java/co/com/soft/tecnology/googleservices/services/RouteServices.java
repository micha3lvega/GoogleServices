package co.com.soft.tecnology.googleservices.services;

import java.io.IOException;
import java.io.Serializable;
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

public class RouteServices implements Serializable {

  private static final long serialVersionUID = 1L;

  private LatLng startRoute;
  private LatLng endRoute;
  private String key;
  private List<String> points;
  private Boolean optimized;

  public RouteServices(double startLat, double startLog, double endLat, double endLog, String key) {

    startRoute = new LatLng(startLat, startLog);
    endRoute = new LatLng(endLat, endLog);
    this.key = key;

  }

  public DirectionsResult calculateRoute() throws GoogleApiException {

    if (key == null) {
      throw new GoogleApiException("invalidate Key");
    }

    if (optimized == null) {
      optimized = true;
    }

    DirectionsApiRequest request = DirectionsApi.newRequest(ContextFactory.getContext(key))
        .origin(startRoute).destination(endRoute).mode(TravelMode.DRIVING);

    StringBuilder waypoints = null;

    if (points != null) {

      waypoints = new StringBuilder();
      for (String waypoint : points) {
        waypoints.append(waypoint);
        waypoints.append("|");
      }

    } else {
      throw new GoogleApiException("No points");
    }

    request.waypoints(waypoints.toString());
    request.optimizeWaypoints(optimized);

    try {
      return request.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new GoogleApiException(e.getMessage());
    } catch (ApiException | IOException e) {
      throw new GoogleApiException(e.getMessage());
    }

  }



  /**
   * @return the endRoute
   */
  public LatLng getEndRoute() {
    return endRoute;
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
    return startRoute;
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
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(List<String> points) {
    this.points = points;
  }

  public void addPoint(double lat, double log) {

    if (points == null) {
      points = new ArrayList<>();
    }

    points.add(lat + "," + log);

  }

  /**
   * @return the optimized
   */
  public Boolean getOptimized() {
    return optimized;
  }

  /**
   * @param optimized the optimized to set
   */
  public void setOptimized(Boolean optimized) {
    this.optimized = optimized;
  }



}
