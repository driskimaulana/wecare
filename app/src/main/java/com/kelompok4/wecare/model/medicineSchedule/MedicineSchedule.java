package com.kelompok4.wecare.model.medicineSchedule;

import com.google.gson.annotations.SerializedName;

public class MedicineSchedule {

    @SerializedName("medicineName")
    public String medicineName;

    @SerializedName("eatTime")
    public String eatTime;

    @SerializedName("startDate")
    public String startDate;

    @SerializedName("endDate")
    public String endDate;

    @SerializedName("elderId")
    public String elderId;

    @SerializedName("note")
    public String note;

    public MedicineSchedule(String medicineName, String eatTime, String startDate, String endDate, String note, String elderId) {
        this.medicineName = medicineName;
        this.eatTime = eatTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.elderId = elderId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getEatTime() {
        return eatTime;
    }

    public void setEatTime(String eatTime) {
        this.eatTime = eatTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }
}
