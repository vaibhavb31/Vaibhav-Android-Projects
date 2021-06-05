package com.example.flickrphotosearchapp;

import com.google.gson.annotations.SerializedName;


public class ApiResponce {
    @SerializedName("photos")
    private Photodata photos;

    public ApiResponce(Photodata photos) {
        this.photos = photos;
    }

    public Photodata getPhotos() {
        return photos;
    }

    public void setPhotos(Photodata photos) {
        this.photos = photos;
    }
}