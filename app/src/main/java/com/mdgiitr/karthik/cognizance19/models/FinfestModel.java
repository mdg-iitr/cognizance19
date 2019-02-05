package com.mdgiitr.karthik.cognizance19.models;

import java.util.List;

public class FinfestModel {
    private String category;
    private List<FinfestEventModel> eventList;

    public FinfestModel() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FinfestEventModel> getEventList() {
        return eventList;
    }

    public void setEventList(List<FinfestEventModel> eventList) {
        this.eventList = eventList;
    }
}
