package com.alexander.example.streetartproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ArtworkDao {


    @Query("SELECT * FROM artworkdata")
    List<ArtworkData> getAll();

    /*
    @Query("SELECT * FROM artworkdata")
    LiveData<ArrayList<ArtworkData>> getAll();
    */
    @Query("DELETE FROM artworkdata")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<ArtworkData> artworks);

}
