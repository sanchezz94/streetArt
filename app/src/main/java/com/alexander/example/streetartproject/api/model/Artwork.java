package com.alexander.example.streetartproject.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artwork {

    @SerializedName("updatedAt")
    @Expose
    private Long updatedAt;
    @SerializedName("createdAt")
    @Expose
    private Long createdAt;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("deployDate")
    @Expose
    private Integer deployDate;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("artists")
    @Expose
    private List<Artist> artists = null;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("id")
    @Expose
    private String id;

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(Integer deployDate) {
        this.deployDate = deployDate;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}