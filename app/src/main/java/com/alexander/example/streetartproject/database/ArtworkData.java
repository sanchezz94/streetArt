package com.alexander.example.streetartproject.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity
public class ArtworkData implements Serializable, Parcelable {

    @PrimaryKey
    private Long mId;
    private String mImage;
    private String mName;
    private String mAuthor;
    private String mAddress;
    private String mDescription;
    private Double mLat;
    private Double mLng;

    public ArtworkData(long id, String image, String name, String author, String address, String description, double lat, double lng) {
        mId = id;
        mImage = image;
        mName = name;
        mAuthor = author;
        mAddress = address;
        mDescription = description;
        mLat = lat;
        mLng = lng;
    }

    protected ArtworkData(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mImage = in.readString();
        mName = in.readString();
        mAuthor = in.readString();
        mAddress = in.readString();
        mDescription = in.readString();
        if (in.readByte() == 0) {
            mLat = null;
        } else {
            mLat = in.readDouble();
        }
        if (in.readByte() == 0) {
            mLng = null;
        } else {
            mLng = in.readDouble();
        }
    }

    public static final Creator<ArtworkData> CREATOR = new Creator<ArtworkData>() {
        @Override
        public ArtworkData createFromParcel(Parcel in) {
            return new ArtworkData(in);
        }

        @Override
        public ArtworkData[] newArray(int size) {
            return new ArtworkData[size];
        }
    };

    public void setId(Long id) {
        mId = id;
    }

    public Long getId() {
        return mId;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getImage() {
        return mImage;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getAuthor() {
        return mAuthor;
    }
    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAddress() {
        return mAddress;
    }
    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLng(Double lng) {
        mLng = lng;
    }

    public Double getLng() {
        return mLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mImage);
        dest.writeString(mName);
        dest.writeString(mAuthor);
        dest.writeString(mAddress);
        dest.writeString(mDescription);
        if (mLat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mLat);
        }
        if (mLng == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mLng);
        }
    }
}
