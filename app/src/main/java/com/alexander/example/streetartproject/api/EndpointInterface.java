package com.alexander.example.streetartproject.api;

import com.alexander.example.streetartproject.api.model.Artwork;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointInterface {


    @GET("artworks")
    Call<ArrayList<Artwork>> getArtworks();

    /*
    @GET("artworks")
    Call<Artwork> getArtworks();
    */

}
