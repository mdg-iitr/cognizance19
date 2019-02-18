package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamMember {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cogniId")
    @Expose
    private String cogniId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("leader")
    @Expose
    private Boolean leader;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCogniId() {
        return cogniId;
    }

    public void setCogniId(String cogniId) {
        this.cogniId = cogniId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }
}
