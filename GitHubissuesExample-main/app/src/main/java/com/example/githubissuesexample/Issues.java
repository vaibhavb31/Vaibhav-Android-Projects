package com.example.githubissuesexample;

import com.google.gson.annotations.SerializedName;

public class Issues {
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("comments_url")
    private String comments_url;


    public Issues(String title, String body, String comments_url) {
        this.title = title;
        this.body = body;
        this.comments_url = comments_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCommentURL() {
        return comments_url;
    }

    public void setCommentURL(String comments_url) {
        this.comments_url = comments_url;
    }
}
