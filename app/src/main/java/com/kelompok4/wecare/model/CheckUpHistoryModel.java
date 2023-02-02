package com.kelompok4.wecare.model;

public class CheckUpHistoryModel {


    private double asamUrat;
    private double gulaDarah;
    private double kolesterol;
    private double hemoglobin;
    private String tanggal;

    public CheckUpHistoryModel(double asamUrat, double gulaDarah, double kolesterol, double hemoglobin, String tanggal) {
        this.asamUrat = asamUrat;
        this.gulaDarah = gulaDarah;
        this.kolesterol = kolesterol;
        this.hemoglobin = hemoglobin;
        this.tanggal = tanggal;
    }

    public double getAsamUrat() {
        return asamUrat;
    }

    public void setAsamUrat(double asamUrat) {
        this.asamUrat = asamUrat;
    }

    public double getGulaDarah() {
        return gulaDarah;
    }

    public void setGulaDarah(double gulaDarah) {
        this.gulaDarah = gulaDarah;
    }

    public double getKolesterol() {
        return kolesterol;
    }

    public void setKolesterol(double kolesterol) {
        this.kolesterol = kolesterol;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
