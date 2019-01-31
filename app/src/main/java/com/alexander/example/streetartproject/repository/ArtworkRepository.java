package com.alexander.example.streetartproject.repository;

import com.alexander.example.streetartproject.App;
import com.alexander.example.streetartproject.api.Api;
import com.alexander.example.streetartproject.api.model.Artwork;
import com.alexander.example.streetartproject.database.ArtworkData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtworkRepository {

    public ArtworkRepository(ArtworkRepositoryListener artworkRepositoryListener) {
        mArtworkRepositoryListener = artworkRepositoryListener;
    }

    private ArtworkRepositoryListener mArtworkRepositoryListener;

    public void startLoadArtwork() {
        Api api = new Api();

        api.getEndpointInterface().getArtworks().enqueue(new Callback<ArrayList<Artwork>>() {
            @Override
            public void onResponse(Call<ArrayList<Artwork>> call, Response<ArrayList<Artwork>> response) {
                onCorrectResponseReceived(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Artwork>> call, Throwable t) {
                getDataFromDB();
            }
        });
    }

    private void clearDatabase() {
        App.appDatabase.artworkDao().deleteAll();
    }

    private void insertInDatabase(ArrayList<ArtworkData> artworkData) {
        App.appDatabase.artworkDao().insert(artworkData);
    }

    private ArrayList<ArtworkData> getArtworksFromDatabase() {
        return new ArrayList<>(App.appDatabase.artworkDao().getAll());
    }

    private ArrayList<ArtworkData> convertResponseToArtworkData(ArrayList<Artwork> artworks) {
        if (artworks == null || artworks.size() <= 0) {
            return null;
        }
        ArrayList<ArtworkData> artworkData = new ArrayList<>();
        for (int x = 0; x < artworks.size(); x ++) {
            Artwork currentArtwork = artworks.get(x);
            artworkData.add(new ArtworkData(x,
                    currentArtwork.getPhotos().get(0).getImage(),
                    currentArtwork.getName(),
                    currentArtwork.getArtists().get(0).getName(),
                    currentArtwork.getLocation().getAddress(),
                    currentArtwork.getDescription(),
                    currentArtwork.getLocation().getLat(),
                    currentArtwork.getLocation().getLng()));
        }
        return artworkData;
    }

    private void getDataFromDB() {
        // no data received from server, try to get data from local DB
        ArrayList<ArtworkData> artworkData = getArtworksFromDatabase();
        if (artworkData.size() <= 0) {
            // there is no data in local DB, error
            onErrorReceived();
        } else {
            sendArtworkData(artworkData);
        }
    }

    private void onErrorReceived() {
        if (mArtworkRepositoryListener != null) {
            mArtworkRepositoryListener.onErrorReceived();
        }
    }

    private void onCorrectResponseReceived(ArrayList<Artwork> artwork) {
        ArrayList<ArtworkData> artworkData = convertResponseToArtworkData(artwork);
        if (artworkData == null || artwork.size() <= 0) {
            getDataFromDB();
            return;
        }
        // correct data received
        clearDatabase();
        insertInDatabase(artworkData);
        sendArtworkData(artworkData);
    }

    private void sendArtworkData(ArrayList<ArtworkData> artworkData) {
        if (mArtworkRepositoryListener != null) {
            mArtworkRepositoryListener.onArtworkListReceived(artworkData);
        }
    }

    public interface ArtworkRepositoryListener {
        void onArtworkListReceived(ArrayList<ArtworkData> artwork);
        void onErrorReceived();
    }

}
