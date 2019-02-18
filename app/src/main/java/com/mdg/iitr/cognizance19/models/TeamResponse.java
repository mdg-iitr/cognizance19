package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("team")
    @Expose
    private Team team;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
