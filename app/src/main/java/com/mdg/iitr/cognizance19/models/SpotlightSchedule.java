package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kaishu on 12/3/19.
 */

public class SpotlightSchedule {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("events")
    @Expose
    private SpotlightMenu menu;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public SpotlightMenu getMenu() {
        return menu;
    }

    public void setMenu(SpotlightMenu menu) {
        this.menu = menu;
    }

}
