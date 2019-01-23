package com.mdgiitr.karthik.cognizance19.models;

import java.util.List;

public class FinfestModel {
    private String category;
    private List<EventModel> eventList;

    public FinfestModel() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<EventModel> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventModel> eventList) {
        this.eventList = eventList;
    }
}
