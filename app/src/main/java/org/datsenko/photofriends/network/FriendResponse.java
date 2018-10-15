package org.datsenko.photofriends.network;

import com.google.gson.annotations.SerializedName;

import org.datsenko.photofriends.model.Friend;

import java.util.List;

public class FriendResponse {
    @SerializedName("items")
    private List<Friend> friendList;

    public List<Friend> getFriendList(){
        return friendList;
    }
}
