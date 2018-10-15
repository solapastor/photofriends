package org.datsenko.photofriends.photos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.datsenko.photofriends.R;
import org.datsenko.photofriends.model.Photo;
import org.datsenko.photofriends.model.SizeType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.PhotosGridViewHolder> {

    private List<Photo> photoList;
    private LayoutInflater inflater;

    public PhotosGridAdapter(List<Photo> photoList, PhotosActivity context){
        this.photoList = photoList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PhotosGridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_photosgrid, viewGroup, false);
        return new PhotosGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosGridViewHolder photosGridViewHolder, int i) {
        photosGridViewHolder.viewData(photoList.get(i));
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotos(List<Photo> photoList){
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    class PhotosGridViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        public PhotosGridViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void viewData(Photo photo){
            Picasso.with(ivPhoto.getContext())
                    .load(photo.getSizeList().get(5).getUrl())
                    .placeholder(R.drawable.image)
                    .into(ivPhoto);
        }
    }
}
