package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("event")
    @Expose
    private EventSpecificModel event;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventSpecificModel getEvent() {
        return event;
    }

    public void setEvent(EventSpecificModel event) {
        this.event = event;
    }

}
