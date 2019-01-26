package com.mdgiitr.karthik.cognizance19.models;

import android.graphics.Bitmap;

public class EventModel {
    private String eventName, eventCategory, eventFollowers, eventPicURL;

    public EventModel() {
    }

    public String getEventPicURl() {
        return eventPicURL;
    }

    public void setEventPicURL(String eventPicURL) {
        this.eventPicURL = eventPicURL;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventFollowers() {
        return eventFollowers;
    }

    public void setEventFollowers(String eventFollowers) {
        this.eventFollowers = eventFollowers;
    }
}
