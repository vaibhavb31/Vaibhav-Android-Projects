package com.example.githubissuesexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDataService {
    String raw="";

@GET("/repos/firebase/firebase-ios-sdk/issues")
    Call<List<Issues>> getAllIssue();

    @GET
    Call<List<Comments>> getAllComments(@Url String url);


}
