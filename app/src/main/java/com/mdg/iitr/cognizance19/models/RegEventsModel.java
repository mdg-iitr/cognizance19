package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegEventsModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("abstract")
    @Expose
    private Boolean _abstract;
    @SerializedName("registrationClosed")
    @Expose
    private Boolean registrationClosed;
    @SerializedName("cost")
    @Expose
    private Object cost;
    @SerializedName("CentralId")
    @Expose
    private Integer centralId;
    @SerializedName("DepartmentId")
    @Expose
    private Object departmentId;
    @SerializedName("teamLimit")
    @Expose
    private Integer teamLimit;


    public RegEventsModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean get_abstract() {
        if(_abstract == null) return false;
        else return _abstract;
    }

    public void set_abstract(Boolean _abstract) {
        this._abstract = _abstract;
    }

    public Boolean getRegistrationClosed() {
        return registrationClosed;
    }

    public void setRegistrationClosed(Boolean registrationClosed) {
        this.registrationClosed = registrationClosed;
    }

    public Object getCost() {
        return cost;
    }

    public void setCost(Object cost) {
        this.cost = cost;
    }

    public Integer getCentralId() {
        return centralId;
    }

    public void setCentralId(Integer centralId) {
        this.centralId = centralId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getTeamLimit() {
        return teamLimit;
    }

    public void setTeamLimit(Integer teamLimit) {
        this.teamLimit = teamLimit;
    }
}
