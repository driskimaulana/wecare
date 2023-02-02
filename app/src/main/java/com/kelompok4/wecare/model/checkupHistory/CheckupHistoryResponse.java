package com.kelompok4.wecare.model.checkupHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckupHistoryResponse extends CheckupHistoryModel {

    @SerializedName("_id")
    @Expose
    String id;

    @SerializedName("relativeId")
    @Expose
    String relativeId;

    @SerializedName("createdAd")
    @Expose
    String createdAt;

    public CheckupHistoryResponse(String id, String date, String place, String doctorName, double cholesterol, double bloodSugar, double hemoglobin, double gout, String elderId,  String relativeId, String createdAt) {
        super(date, place, doctorName, cholesterol, bloodSugar, hemoglobin, gout, elderId);
        this.id = id;
        this.relativeId = relativeId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
