package org.datsenko.photofriends.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.vk.com/method/";

    String FIELDS_PARAMETER = "photo_50, screen_name";

    @GET("friends.get")
    Call<BaseResponse<FriendResponse>> fetchFriends(
            @Query("access_token") String accessToken,
            @Query("v") String apiVersion,
            @Query("fields") String fields);

    @GET("photos.getAll")
    Call<BaseResponse<PhotoResponse>> fetchPhotos(
            @Query("owner_id") String userID,
            @Query("photo_sizes") int sizes,
            @Query("access_token") String accessToken,
            @Query("v") String apiVersion);
}
