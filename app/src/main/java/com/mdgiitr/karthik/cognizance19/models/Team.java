package com.mdgiitr.karthik.cognizance19.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {
    @SerializedName("members")
    @Expose
    private List<TeamMember> members = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("abstract")
    @Expose
    private boolean _abstract;

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

}
