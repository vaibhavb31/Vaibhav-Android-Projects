package com.example.flickrphotosearchapp;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("farm")
    private String farm;
    @SerializedName("id")
    private String id;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("title")
    private String title;

    public Photos(String farm, String id, String secret, String server) {
        this.farm = farm;
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.title = title;
    }



    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}