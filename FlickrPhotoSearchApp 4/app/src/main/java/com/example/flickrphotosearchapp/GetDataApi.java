package com.example.flickrphotosearchapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetDataApi {
        @GET("/services/rest/")
        Call<ApiResponce> getdata(@QueryMap HashMap<String, String> hashMap);
    }
