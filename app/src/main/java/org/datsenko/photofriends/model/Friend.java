package org.datsenko.photofriends.model;

import com.google.gson.annotations.SerializedName;

public class Friend {
    @SerializedName("id")
    private String id;

    @SerializedName("last_name")
    private String lastname;

    @SerializedName("first_name")
    private String firstname;

    @SerializedName("photo_50")
    private String avatarURL;

    public String getId(){
        return id;
    }

    public String getLastname(){
        return lastname;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getAvatarURL(){
        return avatarURL;
    }

    public String getName(){
        return firstname + " " + lastname;
    }
}
