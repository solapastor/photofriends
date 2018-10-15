package org.datsenko.photofriends.network;

import com.google.gson.annotations.SerializedName;

public class AuthorizationResponse {
    @SerializedName("access_token")
    String accessToken;

    public AuthorizationResponse(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken(){
        return accessToken;
    }
}
