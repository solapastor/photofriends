package org.datsenko.photofriends.network;

import com.google.gson.annotations.SerializedName;

import org.datsenko.photofriends.model.Photo;

import java.util.List;

public class PhotoResponse {

    @SerializedName("items")
    private List<Photo> photoList;

    public List<Photo> getPhotoList(){
        return photoList;
    }
}
