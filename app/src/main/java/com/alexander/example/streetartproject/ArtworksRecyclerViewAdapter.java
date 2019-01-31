package com.alexander.example.streetartproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexander.example.streetartproject.database.ArtworkData;

import java.util.ArrayList;

public class ArtworksRecyclerViewAdapter extends RecyclerView.Adapter<ArtworksRecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private ArrayList<ArtworkData> mArtworks;

    public ArtworksRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener, ArrayList<ArtworkData> artworks) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mArtworks = artworks;
    }

    @NonNull
    @Override
    public ArtworksRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View artworkView = inflater.inflate(R.layout.artwork_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(artworkView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ArtworkData artwork = mArtworks.get(i);
        if (artwork.getImage() == null) {
            viewHolder.imageViewPhoto.setImageDrawable(mContext.getDrawable(R.drawable.no_photo));
        } else {
            GlideApp.with(mContext).load(artwork.getImage()).centerCrop().into(viewHolder.imageViewPhoto);
        }
        viewHolder.textViewName.setText(artwork.getName());
        viewHolder.textViewAuthor.setText(artwork.getAuthor());
    }

    @Override
    public int getItemCount() {
        if (mArtworks == null) {
            return 0;
        }
        return mArtworks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageViewPhoto;
        public TextView textViewName;
        public TextView textViewAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.OnItemClick(getLayoutPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

}
