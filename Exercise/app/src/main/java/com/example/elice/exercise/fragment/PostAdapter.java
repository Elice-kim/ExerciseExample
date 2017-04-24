package com.example.elice.exercise.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.elice.exercise.R;
import com.example.elice.exercise.api.Post;

import java.util.ArrayList;

/**
 * Created by pokeoseu on 2017. 4. 24..
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context mContext;
    private ArrayList<Post.Channel.Item> arrayList;

    public PostAdapter(Context mContext, ArrayList<Post.Channel.Item> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.post_item, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(v);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post.Channel.Item item = arrayList.get(position);

        String url = item.getImage();

        Glide.with(mContext).load(url).into(holder.ivImg);
        holder.ivImg.setImageURI(Uri.parse(item.getImage()));
        holder.ivPostText.setText(item.getTitle());
        holder.ivUserName.setText(item.getCpname());
        holder.ivLikeCount.setText(item.getHeight());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
