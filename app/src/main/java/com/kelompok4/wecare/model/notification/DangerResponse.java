package com.kelompok4.wecare.model.notification;

import android.hardware.lights.LightState;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kelompok4.wecare.model.user.User;

import java.util.List;

public class DangerResponse {

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("sendTo")
    @Expose
    List<User> sendTo;

    public DangerResponse(String message, List<User> sendTo) {
        this.message = message;
        this.sendTo = sendTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getSendTo() {
        return sendTo;
    }

    public void setSendTo(List<User> sendTo) {
        this.sendTo = sendTo;
    }
}

