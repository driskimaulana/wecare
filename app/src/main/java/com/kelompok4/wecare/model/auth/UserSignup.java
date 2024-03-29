package com.kelompok4.wecare.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSignup {

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("role")
    @Expose
    String role;


    public UserSignup(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
