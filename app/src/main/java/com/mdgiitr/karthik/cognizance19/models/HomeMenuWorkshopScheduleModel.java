package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.SerializedName;

public class HomeMenuWorkshopScheduleModel {

    @SerializedName("day")
    int day;

    @SerializedName("time")
    String time;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
