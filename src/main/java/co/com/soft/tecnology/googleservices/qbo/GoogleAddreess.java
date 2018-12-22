/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.soft.tecnology.googleservices.qbo;

import java.io.Serializable;

/**
 *
 * @author micha3lvega
 */
public class GoogleAddreess implements Serializable {

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

    public GoogleAddreess() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShortCity() {
        return shortCity;
    }

    public void setShortCity(String shortCity) {
        this.shortCity = shortCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortCountry() {
        return shortCountry;
    }

    public void setShortCountry(String shortCountry) {
        this.shortCountry = shortCountry;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "GoogleAddreess{" + "address=" + address + ", formattedAddress=" + formattedAddress + ", placeId=" + placeId + ", city=" + city + ", shortCity=" + shortCity + ", country=" + country + ", shortCountry=" + shortCountry + ", postalCode=" + postalCode + ", zone=" + zone + ", lat=" + lat + ", lng=" + lng + '}';
    }

}
