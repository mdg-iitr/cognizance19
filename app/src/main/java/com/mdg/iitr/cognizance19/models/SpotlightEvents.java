package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kaishu on 12/3/19.
 */

public class SpotlightEvents {

    @SerializedName("event")
    @Expose
    public List<Data> events;

    public class Data {

        @Expose
        public int day;

        @SerializedName("events")
        public EventData eventData;

        public class EventData {
            @Expose
            public List<String> guest_lecture;

            @Expose
            public List<String> techtainment;

            @Expose
            public List<String> panel_discussion;

        }
    }

}
