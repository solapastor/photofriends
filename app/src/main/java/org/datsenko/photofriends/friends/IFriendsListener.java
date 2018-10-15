package org.datsenko.photofriends.friends;

import org.datsenko.photofriends.model.Friend;

import java.io.IOException;
import java.util.List;

public interface IFriendsListener {
    void onFriendsFetched(List<Friend> list);
    void onFailure();
}
