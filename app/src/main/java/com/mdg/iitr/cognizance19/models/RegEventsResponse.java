package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegEventsResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("events")
    @Expose
    private List<RegEventsModel> events = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RegEventsModel> getEvents() {
        return events;
    }

    public void setEvents(List<RegEventsModel> events) {
        this.events = events;
    }
}
