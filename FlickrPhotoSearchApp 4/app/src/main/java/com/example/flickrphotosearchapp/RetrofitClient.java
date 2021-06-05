package com.example.flickrphotosearchapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String baseUrl="https://www.flickr.com";
    public static Retrofit getRetrofit() {
        if(retrofit==null)
        {
            HttpLoggingInterceptor logging= new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient= new OkHttpClient.Builder().addInterceptor(logging);

            retrofit= new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }


        return retrofit;
    }


}
