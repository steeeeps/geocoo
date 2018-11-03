package com.geocoo.earthview.model;


import javax.persistence.*;
import java.util.Date;

/**
 * desc:
 * google earth view object
 *
 * @author taopy
 * @create 2018-10-29 5:40 PM
 */
@Entity
public class EarthView {

    @Id
    private Integer id;
    @Transient
    private String slug;
    @Transient
    private String url;
    private String api;
    private String title;
    private Float lat;
    private Float lng;
    @Transient
    private String photoUrl;
    @Transient
    private String thumbUrl;
    @Transient
    private String downloadUrl;
    private String region;
    private String country;
    private String attribution;
    private String mapsLink;
    private String mapsTitle;
    private String earthLink;
    private String earthTitle;
    private String nextApi;
    @Transient
    private String nextUrl;
    private String prevApi;
    @Transient
    private String preUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fetchtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    public String getMapsTitle() {
        return mapsTitle;
    }

    public void setMapsTitle(String mapsTitle) {
        this.mapsTitle = mapsTitle;
    }

    public String getEarthLink() {
        return earthLink;
    }

    public void setEarthLink(String earthLink) {
        this.earthLink = earthLink;
    }

    public String getEarthTitle() {
        return earthTitle;
    }

    public void setEarthTitle(String earthTitle) {
        this.earthTitle = earthTitle;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getNextApi() {
        return nextApi;
    }

    public void setNextApi(String nextApi) {
        this.nextApi = nextApi;
    }

    public String getPrevApi() {
        return prevApi;
    }

    public void setPrevApi(String prevApi) {
        this.prevApi = prevApi;
    }

    public Date getFetchtime() {
        return this.fetchtime;
    }

    public void setFetchtime(Date fetchtime) {
        this.fetchtime = fetchtime;
    }
}