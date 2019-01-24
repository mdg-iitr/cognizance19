package com.mdgiitr.karthik.cognizance19.models;

import java.util.List;

public class RegEventsModel {
    private String StringArray;

    public RegEventsModel(String listString) {
        this.StringArray = listString;
    }

    public String getString(){
        return StringArray;
    }
}
