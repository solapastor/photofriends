package org.datsenko.photofriends.photos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.datsenko.photofriends.PhotoActivity;
import org.datsenko.photofriends.R;
import org.datsenko.photofriends.friends.RecyclerItemClickListener;
import org.datsenko.photofriends.model.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosActivity extends AppCompatActivity implements IPhotosListener {

    @BindView(R.id.rv_photos)
    RecyclerView rvPhotos;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PhotosPresenter presenter = new PhotosPresenter();
    PhotosGridAdapter adapter;

    private List<Photo> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        ButterKnife.bind(this);

        adapter = new PhotosGridAdapter(new ArrayList<>(), PhotosActivity.this);
        rvPhotos.setAdapter(adapter);
        rvPhotos.setLayoutManager(new GridLayoutManager(this, 3));
        rvPhotos.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
            intent.putExtra("url", photoList.get(position).getSizeList().get(6).getUrl());
            startActivity(intent);
        }));

        presenter.init(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        fetchPhotos(getIntent().getStringExtra("user_id"));
    }

    @Override
    public void onPhotosFetched(List<Photo> list) {
        photoList = list;
        adapter.setPhotos(list);
    }

    @Override
    public void onFailure() {

    }

    private void fetchPhotos(final String userID){
        presenter.fetchPhotos(userID);
    }
}
