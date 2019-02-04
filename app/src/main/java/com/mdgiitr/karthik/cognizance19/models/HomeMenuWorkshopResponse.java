package com.mdgiitr.karthik.cognizance19.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuWorkshopResponse {

    @SerializedName("message")
    String message;

    @SerializedName("workshops")
    List<HomeMenuWorkshopsModel> workshopsModelList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeMenuWorkshopsModel> getWorkshopsModelList() {
        return workshopsModelList;
    }

    public void setWorkshopsModelList(List<HomeMenuWorkshopsModel> workshopsModelList) {
        this.workshopsModelList = workshopsModelList;
    }
}
