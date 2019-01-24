package com.mdgiitr.karthik.cognizance19.models;

import android.media.Image;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class VerticalItemsModel {
    private String name;
    private List<String> mdata1;
    private List<String> mdata2;
    private List<Image> mImage;

    public VerticalItemsModel(String name, List<Image> image, List<String> data1, List<String> data2) {
        this.name = name;
        this.mImage = image;
        this.mdata1 = data1;
        this.mdata2 = data2;
    }

    public String getName(){
        return name;
    }

    public List<Image> getTags(){
        return mImage;
    }

    public List<String> getData1(){
        return mdata1;
    }

    public List<String> getData2(){
        return mdata2;
    }
}
