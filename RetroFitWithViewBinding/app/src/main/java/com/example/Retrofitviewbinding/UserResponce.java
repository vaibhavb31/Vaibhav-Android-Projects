package com.example.Retrofitviewbinding;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponce {
    @SerializedName("data")
    private List<RetroUsers> data;

    public List<RetroUsers> getData() {
        return data;
    }

    public void setData(List<RetroUsers> data) {
        this.data = data;
    }
}
