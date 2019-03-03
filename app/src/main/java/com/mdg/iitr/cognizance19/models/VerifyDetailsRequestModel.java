package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.SerializedName;

public class VerifyDetailsRequestModel {

    @SerializedName("gender")
    String gender = "";
    @SerializedName("mobile")
    String mobile = "";

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
