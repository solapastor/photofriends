package org.datsenko.photofriends.photos;

import org.datsenko.photofriends.model.Photo;

import java.io.IOException;
import java.util.List;

public interface IPhotosListener {
    void onPhotosFetched(List<Photo> list);
    void onFailure();
}
