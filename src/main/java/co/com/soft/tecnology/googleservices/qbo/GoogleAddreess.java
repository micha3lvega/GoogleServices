/* To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor. */
package co.com.soft.tecnology.googleservices.qbo;

import java.io.Serializable;

/**
 *
 * @author m.vega.carrillo
 */
public class GoogleAddreess implements Serializable {


  private static final long serialVersionUID = 1L;
  private String address;
  private String formattedAddress;
  private String placeId;
  private String city;
  private String shortCity;
  private String country;
  private String shortCountry;
  private String postalCode;
  private String zone;
  private Double lat;
  private Double lng;

  public GoogleAddreess() {}

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFormattedAddress() {
    return this.formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }

  public String getPlaceId() {
    return this.placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getShortCity() {
    return this.shortCity;
  }

  public void setShortCity(String shortCity) {
    this.shortCity = shortCity;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getShortCountry() {
    return this.shortCountry;
  }

  public void setShortCountry(String shortCountry) {
    this.shortCountry = shortCountry;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Double getLat() {
    return this.lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return this.lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public String getZone() {
    return this.zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  @Override
  public String toString() {
    return "GoogleAddreess{" + "address=" + this.address + ", formattedAddress="
        + this.formattedAddress + ", placeId=" + this.placeId + ", city=" + this.city
        + ", shortCity=" + this.shortCity + ", country=" + this.country + ", shortCountry="
        + this.shortCountry + ", postalCode=" + this.postalCode + ", zone=" + this.zone + ", lat="
        + this.lat + ", lng=" + this.lng + '}';
  }

}
