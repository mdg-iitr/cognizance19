package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSPPResponseModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private UserDetailsSPPResponseModel details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetailsSPPResponseModel getDetails() {
        return details;
    }

    public void setDetails(UserDetailsSPPResponseModel details) {
        this.details = details;
    }

}
