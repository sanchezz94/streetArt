package com.alexander.example.streetartproject.ui.artwork;

import com.arellomobile.mvp.MvpView;

public interface ArtworkView extends MvpView {

    void setArtworkData(String photoUrl, String name, String author, String address, String description);

}
