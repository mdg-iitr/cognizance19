package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("day")
    Integer day;

    @SerializedName("time")
    String time;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
