package com.alexander.example.streetartproject.ui.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.alexander.example.streetartproject.R;
import com.alexander.example.streetartproject.database.ArtworkData;
import com.alexander.example.streetartproject.repository.ArtworkRepository;

import java.util.ArrayList;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements ArtworkRepository.ArtworkRepositoryListener {

    Context mContext;
    ArrayList<ArtworkData> mArtwork;
    int mNumOfArtworks;

    private final static int NUM_OF_ARTWORKS_IN_ONE_PAGE = 10;
    public int mCurrentPage;

    public void setContext(Context context) {
        mContext = context;
    }

    protected void onFirstViewAttach() {
        mArtwork = new ArrayList<>();
        mNumOfArtworks = 0;
        mCurrentPage = 0;
    }

    public void startArtworkLoading() {
        ArtworkRepository artworkRepository = new ArtworkRepository(this);
        artworkRepository.startLoadArtwork();
    }

    public void loadMoreArtworks() {
        mCurrentPage++;
        loadArtworks(mCurrentPage);
    }

    private void loadArtworks(int page) {
        int startIndex = page * NUM_OF_ARTWORKS_IN_ONE_PAGE;
        if (startIndex >= mNumOfArtworks) {
            return;
        }
        int endIndex = startIndex + NUM_OF_ARTWORKS_IN_ONE_PAGE;
        if (endIndex > mNumOfArtworks) {
            endIndex = mNumOfArtworks - 1;
        }
        ArrayList<ArtworkData> dataForView = new ArrayList<>(mArtwork.subList(startIndex, endIndex));
        if (dataForView.size() > 0) {
            getViewState().showArtworkList(dataForView);
        }
    }

    public ArtworkData getArtworkData(int index) {
        if (mArtwork == null || index >= mArtwork.size()) {
            return null;
        }
        return mArtwork.get(index);
    }

    @Override
    public void onArtworkListReceived(ArrayList<ArtworkData> artwork) {
        mArtwork = artwork;
        mNumOfArtworks = artwork.size();
        mCurrentPage = 0;
        loadArtworks(mCurrentPage);
        if (artwork.size() > 0) {
            getViewState().showMessage(mContext.getResources().getString(R.string.load_complete));
        } else {
            getViewState().showMessage(mContext.getResources().getString(R.string.no_artworks));
        }
    }

    @Override
    public void onErrorReceived() {
        getViewState().showMessage(mContext.getResources().getString(R.string.unknown_error));
    }

}
