package com.alexander.example.streetartproject.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.alexander.example.streetartproject.ArtworksRecyclerViewAdapter;
import com.alexander.example.streetartproject.EndlessScrollListener;
import com.alexander.example.streetartproject.R;
import com.alexander.example.streetartproject.database.ArtworkData;
import com.alexander.example.streetartproject.ui.artwork.ArtworkFragment;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements MainView, ArtworksRecyclerViewAdapter.OnItemClickListener {

    @InjectPresenter
    MainPresenter mMainPresenter;

    private TextView textView;
    private Button buttonLoadArtworks;
    private RecyclerView mRecyclerView;
    private ArtworksRecyclerViewAdapter mArtworksRecyclerViewAdapter;
    EndlessScrollListener mScrollListener;
    private ProgressBar progressBar;

    private FrameLayout fragmentContainer;

    ArrayList<ArtworkData> mArtworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);

        mArtworks = new ArrayList<>();

        textView = findViewById(R.id.textViewMessage);
        buttonLoadArtworks = findViewById(R.id.buttonLoadArtworks);
        buttonLoadArtworks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnLoadArtworks();
            }
        });
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mArtworksRecyclerViewAdapter = new ArtworksRecyclerViewAdapter(getApplicationContext(), this, mArtworks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView = findViewById(R.id.recyclerViewArtworks);
        mRecyclerView.setAdapter(mArtworksRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mMainPresenter.loadMoreArtworks();
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);

        mMainPresenter.setContext(getApplicationContext());
    }

    private void clickOnLoadArtworks() {
        loadingStart();
        mArtworks.clear();
        mMainPresenter.startArtworkLoading();
    }

    @Override
    public void showMessage(String message) {
        textView.setText(message);
        loadingEnd();
    }

    @Override
    public void showArtworkList(ArrayList<ArtworkData> artworkList) {
        loadingEnd();
        mArtworks.addAll(artworkList);
        mArtworksRecyclerViewAdapter.notifyItemInserted(mArtworks.size() - 1);
    }

    private void loadingStart() {
        mArtworksRecyclerViewAdapter.notifyItemRangeRemoved(0, mArtworks.size());
        buttonLoadArtworks.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void loadingEnd() {
        buttonLoadArtworks.setClickable(true);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void OnItemClick(int position) {
        Log.d("MainActivity", "position = " + position);
        ArtworkData artworkData = mMainPresenter.getArtworkData(position);
        showArtworkFragment(artworkData);
    }

    private void showArtworkFragment(ArtworkData artworkData) {
        Log.d("MainActivity", "showArtworkFragment Name = " + artworkData.getName());
        final ArtworkFragment artworkFragment = new ArtworkFragment();
        buttonLoadArtworks.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putParcelable("artwork", artworkData);
        artworkFragment.setArguments(bundle);
        artworkFragment.setCloseButtonClickListener(new ArtworkFragment.CloseButtonClickListener() {
            @Override
            public void CloseButtonClickListener() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(artworkFragment);
                fragmentTransaction.commit();
                buttonLoadArtworks.setVisibility(View.VISIBLE);
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragmentContainer.getId(), artworkFragment);
        fragmentTransaction.commit();
    }

}
