package com.example.githubissuesexample;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comments {
    @SerializedName("body")
    private String body;
    @SerializedName("user")
    private Users user;

    public Comments(String body, Users user) {
        this.body = body;
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {

        this.body = body;

    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
