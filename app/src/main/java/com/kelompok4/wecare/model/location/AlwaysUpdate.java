package com.kelompok4.wecare.model.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlwaysUpdate {

    @SerializedName("latitude")
    @Expose
    double latitude;

    @SerializedName("longitude")
    @Expose
    double longitude;

    @SerializedName("fcmToken")
    @Expose
    String fcmToken;

    public AlwaysUpdate(){}

    public AlwaysUpdate(double latitude, double longitude, String fcmToken) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.fcmToken = fcmToken;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
