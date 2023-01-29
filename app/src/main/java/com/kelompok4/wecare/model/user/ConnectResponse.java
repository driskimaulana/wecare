package com.kelompok4.wecare.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConnectResponse {

    @SerializedName("status")
    @Expose
    int status;
    @SerializedName("message")
    @Expose
    String message;

    public ConnectResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
