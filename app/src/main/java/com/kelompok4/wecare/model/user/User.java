package com.kelompok4.wecare.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("_id")
    @Expose
    String id;
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
    @SerializedName("elderConnected")
    @Expose
    List<String> elderConnected;
    @SerializedName("relativeConnected")
    @Expose
    List<String> relativeConnected;
    @SerializedName("createdAt")
    @Expose
    String createdAt;
    @SerializedName("location")
    @Expose
    List<Double> location;

    public User() {

    }

    public User(String id, String name, String email, String password, String role, List<String> elderConnected, List<String> relativeConnected, String createdAt, List<Double> location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.elderConnected = elderConnected;
        this.relativeConnected = relativeConnected;
        this.createdAt = createdAt;
        this.location = location;
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

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }
}
