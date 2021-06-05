package com.example.flickrphotosearchapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photodata {
    @SerializedName("photo")
    List<Photos> photo;
    @SerializedName("pages")
    String pages;

    public Photodata(List<Photos> photo, String pages) {
        this.photo = photo;
        this.pages= pages;
    }


    public List<Photos> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photos> photo) {
        this.photo = photo;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
