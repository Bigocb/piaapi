package com.cloutier.piaapi.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWeatherPrefs {

    private int userId;
    private int lat;
    private int longitude;

    @Override
    public String toString() {
        return "UserWeatherPrefs{" +
                "userId=" + userId +
                ", lat=" + lat +
                ", longitude=" + longitude +
                '}';
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
