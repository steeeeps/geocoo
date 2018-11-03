package com.geocoo.earthview.model;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-01 10:28 PM
 */
public class EarthViewWebModel {


    private Integer id;
    private String attr;
    private Float lat;
    private Float lng;

    private String thumbUrl;
    private String fullUrl;
    private String region;
    private String country;
    private String earthLink;


    public EarthViewWebModel(EarthView earthView) {
        this.id = earthView.getId();
        this.attr = earthView.getAttribution();
        this.lat = earthView.getLat();
        this.lng = earthView.getLng();
        this.thumbUrl =  earthView.getThumbUrl();
        this.region = earthView.getRegion();
        this.country = earthView.getCountry();
        this.earthLink=earthView.getEarthLink();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getEarthLink() {
        return earthLink;
    }

    public void setEarthLink(String earthLink) {
        this.earthLink = earthLink;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }


}