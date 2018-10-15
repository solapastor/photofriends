package org.datsenko.photofriends.model;

import com.google.gson.annotations.SerializedName;

public class Size {

    @SerializedName("type")
    SizeType sizeType;

    @SerializedName("url")
    String url;

    public SizeType getSizeType(){
        return sizeType;
    }

    public String getUrl(){
        return url;
    }
}
