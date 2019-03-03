package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentRequestModel {

    @SerializedName("type")
    @Expose
    private String type="";
    @SerializedName("gender")
    @Expose
    private String gender="";
    @SerializedName("referalCode")
    @Expose
    private String referalCode="";
    @SerializedName("events")
    @Expose
    private List<PaymentEvent> events = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReferalCode() {
        return referalCode;
    }

    public void setReferalCode(String referalCode) {
        this.referalCode = referalCode;
    }

    public List<PaymentEvent> getEvents() {
        return events;
    }

    public void setEvents(List<PaymentEvent> events) {
        this.events = events;
    }
}
