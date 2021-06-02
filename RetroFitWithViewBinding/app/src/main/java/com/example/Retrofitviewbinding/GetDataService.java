package com.example.Retrofitviewbinding;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/public-api/users")
    Call<UserResponce> getAllUsers();

}
