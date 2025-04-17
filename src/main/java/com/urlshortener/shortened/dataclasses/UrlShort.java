package com.urlshortener.shortened.dataclasses;

public class UrlShort {
    String long_url;
    String short_url;

    public UrlShort(String long_url, String short_url) {
        this.long_url = long_url;
        this.short_url = short_url;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @Override
    public String toString() {
        return "UrlShort{" +
                "long_url='" + long_url + '\'' +
                ", short_url='" + short_url + '\'' +
                '}';
    }
}
