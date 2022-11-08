package com.kelompok4.wecare.model.healthEducation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetHealthEducation {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<HealthEducation> listHealthEducations;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HealthEducation> getListHealthEducations() {
        return listHealthEducations;
    }

    public void setListHealthEducations(List<HealthEducation> listHealthEducations) {
        this.listHealthEducations = listHealthEducations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
