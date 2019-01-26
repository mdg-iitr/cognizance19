package com.mdgiitr.karthik.cognizance19.models;

import java.util.List;

public class SpotlightModel {
    private String category;
    private List<SpotlightGuestModel> guestList;

    public SpotlightModel() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SpotlightGuestModel> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<SpotlightGuestModel> guestList) {
        this.guestList = guestList;
    }
}
