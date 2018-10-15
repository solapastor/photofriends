package org.datsenko.photofriends.friends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import org.datsenko.photofriends.App;
import org.datsenko.photofriends.R;
import org.datsenko.photofriends.model.Friend;
import org.datsenko.photofriends.photos.PhotosActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity implements IFriendsListener {

    @BindView(R.id.rv_friends)
    RecyclerView rvFriends;

    FriendsPresenter presenter = new FriendsPresenter();
    FriendsListAdapter adapter;

    private List<Friend> friendList = new ArrayList<>();

    public static final String ACCESS_TOKEN = "access_token";
    public static final String FILE_NAME = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ButterKnife.bind(this);

        rvFriends.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        presenter.init(this);

        if (App.accessToken == ""){
            VKSdk.login(this, "friends, photos");
        }

        adapter = new FriendsListAdapter(new ArrayList<>(), FriendsActivity.this);
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        rvFriends.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), PhotosActivity.class);
            intent.putExtra("user_id", friendList.get(position).getId());
            startActivity(intent);
        }));

        fetchFriends();
    }

    @Override
    public void onFriendsFetched(List<Friend> list) {
        friendList = list;
        adapter.setFriendList(list);
    }

    @Override
    public void onFailure() {

    }

    private void fetchFriends(){
        new Thread(() -> presenter.fetchFriends()).start();
    }

    private void saveToken(String token){
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                App.accessToken = res.accessToken;
                saveToken(res.accessToken);
                fetchFriends();
            }

            @Override
            public void onError(VKError error) {
                finish();
            }
        }));
    }
}
