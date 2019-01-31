package com.alexander.example.streetartproject.ui.artwork;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.alexander.example.streetartproject.GlideApp;
import com.alexander.example.streetartproject.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ArtworkFragment extends MvpAppCompatFragment implements ArtworkView {

    @InjectPresenter
    ArtworkPresenter mArtworkPresenter;

    private PhotoView photoViewImage;
    private TextView textViewName;
    private TextView textViewAuthor;
    private TextView textViewAddress;
    private TextView textViewDescription;
    private Button buttonClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artwork_fragment, container, false);
        photoViewImage = view.findViewById(R.id.imageViewPhoto);
        textViewName = view.findViewById(R.id.textViewName);
        textViewAuthor = view.findViewById(R.id.textViewAuthor);
        textViewAddress = view.findViewById(R.id.textViewAddress);
        textViewDescription = view.findViewById(R.id.textViewDescription);
        buttonClose = view.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (closeButtonClickListener != null) {
                    closeButtonClickListener.CloseButtonClickListener();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //
        mArtworkPresenter.setBundle(this.getArguments());
        return view;
    }

    @Override
    public void setArtworkData(String photoUrl, String name, String author, String address, String description) {
        Log.d("setArtworkData", "name = " + name);
        GlideApp.with(getActivity()).load(photoUrl).centerCrop().into(photoViewImage);
        textViewName.setText(name);
        textViewAuthor.setText(author);
        textViewAddress.setText(address);
        textViewDescription.setText(description);
    }

    public interface CloseButtonClickListener {
        public void CloseButtonClickListener();
    }

    private CloseButtonClickListener closeButtonClickListener;

    public void setCloseButtonClickListener (CloseButtonClickListener closeButtonClickListener) {
        this.closeButtonClickListener = closeButtonClickListener;
    }

}
