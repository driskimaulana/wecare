package com.kelompok4.wecare.model;

public class FallHistoryModel {

    private String tanggal;
    private String location;
    private String status;

    public FallHistoryModel(String tanggal, String location, String status) {
        this.tanggal = tanggal;
        this.location = location;
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
