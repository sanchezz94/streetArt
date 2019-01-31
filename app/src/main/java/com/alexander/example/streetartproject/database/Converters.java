package com.alexander.example.streetartproject.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<ArtworkData> fromString(String data) {
        Type type = new TypeToken<ArrayList<ArtworkData>>() {}.getType();
        return new Gson().fromJson(data, type);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<ArtworkData> artworkData) {
        Gson gson = new Gson();
        return gson.toJson(artworkData);
    }

}
