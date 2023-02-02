package com.kelompok4.wecare.model.checkupHistory;

import com.google.gson.annotations.SerializedName;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;

import java.util.List;

public class ListCheckupHistory {

    @SerializedName("status")
    int status;
    @SerializedName("result")
    List<CheckupHistoryResponse> listCheckupHistoryResponses;
    @SerializedName("message")
    String message;

    public ListCheckupHistory(int status, List<CheckupHistoryResponse> listCheckupHistoryResponses, String message) {
        this.status = status;
        this.listCheckupHistoryResponses = listCheckupHistoryResponses;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CheckupHistoryResponse> getListCheckupHistoryResponses() {
        return listCheckupHistoryResponses;
    }

    public void setListCheckupHistoryResponses(List<CheckupHistoryResponse> listCheckupHistoryResponses) {
        this.listCheckupHistoryResponses = listCheckupHistoryResponses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
