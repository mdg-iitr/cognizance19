package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CenterstageOrDepartmentalEventsResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("centerstage")
    @Expose
    private List<Centerstage> centerstage = null;
    @SerializedName("departmental")
    @Expose
    private List<Departmental> departmental = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Centerstage> getCenterstage() {
        return centerstage;
    }

    public void setCenterstage(List<Centerstage> centerstage) {
        this.centerstage = centerstage;
    }

    public List<Departmental> getDepartmental() {
        return departmental;
    }

    public void setDepartmental(List<Departmental> departmental) {
        this.departmental = departmental;
    }
}
