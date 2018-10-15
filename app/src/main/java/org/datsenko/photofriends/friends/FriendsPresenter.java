package org.datsenko.photofriends.friends;

import org.datsenko.photofriends.App;
import org.datsenko.photofriends.network.BaseResponse;
import org.datsenko.photofriends.network.FriendResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsPresenter implements IFriendsPresenter {

    IFriendsListener listener;

    public void init(IFriendsListener context){
        listener = context;
    }

    @Override
    public void fetchFriends() {
        App.getApi().fetchFriends(App.accessToken, "5.85", "photo_50, nickname").enqueue(new Callback<BaseResponse<FriendResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<FriendResponse>> call, Response<BaseResponse<FriendResponse>> response) {
                listener.onFriendsFetched(response.body().body.getFriendList());
            }

            @Override
            public void onFailure(Call<BaseResponse<FriendResponse>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
