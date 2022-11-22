package com.kelompok4.wecare.model.auth;

import com.google.gson.annotations.SerializedName;
import com.kelompok4.wecare.model.user.User;

import java.util.List;

public class AuthResponse {

    @SerializedName("result")
    List<User> result;
    @SerializedName("token")
    String token;

    public  AuthResponse() {

    }

    public AuthResponse(List<User> result, String token) {
        this.result = result;
        this.token = token;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
