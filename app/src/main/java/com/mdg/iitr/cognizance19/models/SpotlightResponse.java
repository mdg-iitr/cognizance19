package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotlightResponse {
    @SerializedName("events")
    @Expose
    private List<SpotlightSchedule> scheduleList = null;

    public List<SpotlightSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<SpotlightSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
