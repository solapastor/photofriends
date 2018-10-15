package org.datsenko.photofriends.network;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<BODY> {

    @SerializedName("response")
    public BODY body;
}
