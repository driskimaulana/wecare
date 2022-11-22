package com.kelompok4.wecare.model.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("_id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("role")
    String role;
    @SerializedName("elderConnected")
    List<String> elderConnected;
    @SerializedName("relativeConnected")
    List<String> relativeConnected;
    @SerializedName("createdAt")
    String createdAt;

    public User() {

    }

    public User(String id, String name, String email, String password, String role, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getElderConnected() {
        return elderConnected;
    }

    public void setElderConnected(List<String> elderConnected) {
        this.elderConnected = elderConnected;
    }

    public List<String> getRelativeConnected() {
        return relativeConnected;
    }

    public void setRelativeConnected(List<String> relativeConnected) {
        this.relativeConnected = relativeConnected;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
