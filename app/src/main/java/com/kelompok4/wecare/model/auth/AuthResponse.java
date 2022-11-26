package com.kelompok4.wecare.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kelompok4.wecare.model.user.User;

import java.util.List;

public class AuthResponse {

    @SerializedName("result")
    @Expose
    User result;

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("message")
    @Expose
    String message;

    public  AuthResponse() {

    }

    public AuthResponse(User result, String token, String message) {
        this.result = result;
        this.token = token;
        this.message = message;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}