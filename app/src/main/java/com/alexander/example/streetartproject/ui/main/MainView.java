package com.alexander.example.streetartproject.ui.main;

import com.arellomobile.mvp.MvpView;
import com.alexander.example.streetartproject.database.ArtworkData;

import java.util.ArrayList;

public interface MainView extends MvpView {

    void showMessage(String message);

    void showArtworkList(ArrayList<ArtworkData> artworkList);

}
