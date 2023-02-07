package com.kelompok4.wecare.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElderResetPasswordRequest extends  ResetPasswordRequest{

    @SerializedName("elderId")
    @Expose
    private String elderId;

    public ElderResetPasswordRequest(String oldPassword, String newPassword, String elderId) {
        super(oldPassword, newPassword);
        this.elderId = elderId;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }
}
