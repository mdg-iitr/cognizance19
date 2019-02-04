package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeMenuWorkshopsModel {

    @SerializedName("id")
    int workshopID;

    @SerializedName("thumbnail")
    String imageURL;

    @SerializedName("name")
    String workshopName;

    @SerializedName("description")
    String workshopDescription;

    @SerializedName("schedule")
    List<HomeMenuWorkshopScheduleModel> schedule;

    @SerializedName("venue")
    String venue;


    public HomeMenuWorkshopsModel() {
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public int getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(int workshopID) {
        this.workshopID = workshopID;
    }

    public String getWorkshopDescription() {
        return workshopDescription;
    }

    public void setWorkshopDescription(String workshopDescription) {
        this.workshopDescription = workshopDescription;
    }

    public List<HomeMenuWorkshopScheduleModel> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<HomeMenuWorkshopScheduleModel> schedule) {
        this.schedule = schedule;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
