package com.kelompok4.wecare.model.checkupHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckupHistoryModel {

    @SerializedName("date")
    @Expose
    String date;

    @SerializedName("place")
    @Expose
    String place;

    @SerializedName("doctorName")
    @Expose
    String doctorName;

    @SerializedName("cholesterol")
    @Expose
    double cholesterol;

    @SerializedName("bloodSugar")
    @Expose
    double bloodSugar;

    @SerializedName("hemoglobin")
    @Expose
    double hemoglobin;

    @SerializedName("gout")
    @Expose
    double gout;

    @SerializedName("elderId")
    @Expose
    String elderId;

    public CheckupHistoryModel(){}

    public CheckupHistoryModel(String date, String place, String doctorName, double cholesterol, double bloodSugar, double hemoglobin, double gout, String elderId) {
        this.date = date;
        this.place = place;
        this.doctorName = doctorName;
        this.cholesterol = cholesterol;
        this.bloodSugar = bloodSugar;
        this.hemoglobin = hemoglobin;
        this.gout = gout;
        this.elderId = elderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(double bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public double getGout() {
        return gout;
    }

    public void setGout(double gout) {
        this.gout = gout;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }
}
