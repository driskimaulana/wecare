package com.kelompok4.wecare.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListElderConnected {

    @SerializedName("status")
    private int status;

    @SerializedName("result")
    private List<User> listElderConnected;

    public ListElderConnected(int status, List<User> listElderConnected) {
        this.status = status;
        this.listElderConnected = listElderConnected;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<User> getListElderConnected() {
        return listElderConnected;
    }

    public void setListElderConnected(List<User> listElderConnected) {
        this.listElderConnected = listElderConnected;
    }
}
