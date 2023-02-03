package com.kelompok4.wecare.model.medicineSchedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicineScheduleList {

    @SerializedName("status")
    private int status;

    @SerializedName("result")
    private List<MedicineSchedule> medicineScheduleList;

    public MedicineScheduleList(int status, List<MedicineSchedule> medicineScheduleList) {
        this.status = status;
        this.medicineScheduleList = medicineScheduleList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MedicineSchedule> getMedicineScheduleList() {
        return medicineScheduleList;
    }

    public void setMedicineScheduleList(List<MedicineSchedule> medicineScheduleList) {
        this.medicineScheduleList = medicineScheduleList;
    }
}
