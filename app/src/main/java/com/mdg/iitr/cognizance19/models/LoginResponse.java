package com.mdg.iitr.cognizance19.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    public String message;

    @SerializedName("token")
    public String token;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("role")
    public String role;

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
