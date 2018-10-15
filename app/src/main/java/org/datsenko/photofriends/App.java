package org.datsenko.photofriends;

import android.app.Application;
import android.content.SharedPreferences;

import com.vk.sdk.VKSdk;

import org.datsenko.photofriends.friends.FriendsActivity;
import org.datsenko.photofriends.network.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static Api api;
    private static Retrofit retrofit;

    public static String accessToken;

    @Override
    public void onCreate(){
        super.onCreate();

        VKSdk.initialize(getApplicationContext());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

        SharedPreferences preferences = getSharedPreferences(FriendsActivity.FILE_NAME, MODE_PRIVATE);
        accessToken = preferences.getString(FriendsActivity.ACCESS_TOKEN, "");
    }

    public static Api getApi(){
        return api;
    }
}
