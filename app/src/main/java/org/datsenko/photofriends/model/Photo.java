package org.datsenko.photofriends.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("sizes")
    private List<Size> sizeList;

    public List<Size> getSizeList(){
        return sizeList;
    }
}
