package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotlightMenu {
    @SerializedName("guest_lecture")
    @Expose
    private List<SpotlightEventModel> guestLecture = null;
    @SerializedName("techtainment")
    @Expose
    private List<SpotlightEventModel> techtainment = null;
    @SerializedName("panel_discussion")
    @Expose
    private List<SpotlightEventModel> panelDiscussion = null;

    public List<SpotlightEventModel> getGuestLecture() {
        return guestLecture;
    }

    public void setGuestLecture(List<SpotlightEventModel> guestLecture) {
        this.guestLecture = guestLecture;
    }

    public List<SpotlightEventModel> getTechtainment() {
        return techtainment;
    }

    public void setTechtainment(List<SpotlightEventModel> techtainment) {
        this.techtainment = techtainment;
    }

    public List<SpotlightEventModel> getPanelDiscussion() {
        return panelDiscussion;
    }

    public void setPanelDiscussion(List<SpotlightEventModel> panelDiscussion) {
        this.panelDiscussion = panelDiscussion;
    }
}
