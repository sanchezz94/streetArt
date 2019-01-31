package com.alexander.example.streetartproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private String BASE_URL = "https://street-art-server.herokuapp.com/";

    private Retrofit getRetrofit() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()));
        retrofitBuilder.client(getHttpClient());
        return retrofitBuilder.build();
    }

    public EndpointInterface getEndpointInterface() {
        return getRetrofit().create(EndpointInterface.class);
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
                .setLenient();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClientBuilder.build();
    }

}