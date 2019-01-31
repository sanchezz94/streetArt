package com.alexander.example.streetartproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.alexander.example.streetartproject.database.AppDatabase;

public class App extends Application {

    public static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "app_db").allowMainThreadQueries().build();
    }

}
