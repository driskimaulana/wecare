package com.kelompok4.wecare.model;

public class MedicineModel {
    private int imageId;
    private String time;
    private String name;
    private String desc;

    public MedicineModel(int imageId, String time, String name, String desc) {
        this.imageId = imageId;
        this.time = time;
        this.name = name;
        this.desc = desc;
    }

    public int getImageId() { return imageId;}

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setImageIdmage(int imageId) { this.imageId = imageId; }

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
