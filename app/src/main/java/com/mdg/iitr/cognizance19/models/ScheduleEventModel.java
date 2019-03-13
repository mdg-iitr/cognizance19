package com.mdg.iitr.cognizance19.models;

public class ScheduleEventModel {

    public String name = "";
    public String time = "";
    public String venue = "";
    public int eventId;

    public ScheduleEventModel(String name, String time, int eventId, String venue) {
        this.name = name;
        this.time = time;
        this.eventId = eventId;
        this.venue = venue;
    }

}
