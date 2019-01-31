package com.alexander.example.streetartproject.ui.artwork;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.alexander.example.streetartproject.database.ArtworkData;

@InjectViewState
public class ArtworkPresenter extends MvpPresenter<ArtworkView> {

    ArtworkData mArtworkData;

    public void setBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        mArtworkData = bundle.getParcelable("artwork");
        Log.d("setBundle", "mArtworkData.getName() = " + mArtworkData.getName());
        getViewState().setArtworkData(
                mArtworkData.getImage(),
                mArtworkData.getName(),
                mArtworkData.getAuthor(),
                mArtworkData.getAddress(),
                mArtworkData.getDescription());
    }

}
