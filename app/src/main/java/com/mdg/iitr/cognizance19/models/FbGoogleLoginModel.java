package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.SerializedName;

public class FbGoogleLoginModel {

    @SerializedName("accessToken")
    String accessToken;
    @SerializedName("email")
    String email;
    @SerializedName("type")
    String type;
    @SerializedName("name")
    String name;
    @SerializedName("imageUrl")
    String imageUrl;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
