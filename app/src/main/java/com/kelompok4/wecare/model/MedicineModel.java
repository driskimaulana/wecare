package com.kelompok4.wecare.model;

public class MedicineModel {
    private String time;
    private String name;
    private String desc;

    public MedicineModel(String time, String name, String desc) {
        this.time = time;
        this.name = name;
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
