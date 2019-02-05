package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponseModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private UserDetailsResponseModel details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetailsResponseModel getDetails() {
        return details;
    }

    public void setDetails(UserDetailsResponseModel details) {
        this.details = details;
    }

}
