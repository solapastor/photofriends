package org.datsenko.photofriends.photos;

import org.datsenko.photofriends.App;
import org.datsenko.photofriends.model.Photo;
import org.datsenko.photofriends.network.BaseResponse;
import org.datsenko.photofriends.network.PhotoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosPresenter implements IPhotosPresenter {

    IPhotosListener listener;

    public void init(IPhotosListener context){
        listener = context;
    }

    @Override
    public void fetchPhotos(String userID) {
        App.getApi().fetchPhotos(userID, 1, App.accessToken, "5.85").enqueue(new Callback<BaseResponse<PhotoResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<PhotoResponse>> call, Response<BaseResponse<PhotoResponse>> response) {
                listener.onPhotosFetched(response.body().body.getPhotoList());
            }

            @Override
            public void onFailure(Call<BaseResponse<PhotoResponse>> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
