package org.datsenko.photofriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.datsenko.photofriends.model.Photo;
import org.datsenko.photofriends.photos.IPhotosListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity{

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");

        Picasso.with(ivPhoto.getContext())
                .load(url)
                .placeholder(R.drawable.image)
                .into(ivPhoto);

        ivPhoto.setOnClickListener(v -> finish());
    }
}
