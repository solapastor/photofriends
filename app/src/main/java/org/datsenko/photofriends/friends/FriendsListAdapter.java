package org.datsenko.photofriends.friends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.datsenko.photofriends.R;
import org.datsenko.photofriends.model.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendsListViewHolder> {

    private List<Friend> friendList;

    public FriendsListAdapter(List<Friend> friendList, FriendsActivity context){
        this.friendList = friendList;

        ButterKnife.bind(this, context);
    }

    @NonNull
    @Override
    public FriendsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friendslist, viewGroup, false);
        view.setOnClickListener(v -> {
            
        });
        return new FriendsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsListViewHolder friendsListViewHolder, int i) {
        friendsListViewHolder.viewData(friendList.get(i));
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public void setFriendList(List<Friend> friendList){
        this.friendList = friendList;
        notifyDataSetChanged();
    }

    class FriendsListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_avatar)
        CircleImageView ivAvatar;

        @BindView(R.id.tv_name)
        TextView tvName;

        public FriendsListViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void viewData(Friend friend){
            tvName.setText(friend.getName());
            Picasso.with(ivAvatar.getContext())
                    .load(friend.getAvatarURL())
                    .placeholder(R.drawable.contact)
                    .into(ivAvatar);
        }
    }
}
