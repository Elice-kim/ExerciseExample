package com.example.elice.exercise.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elice.exercise.R;

/**
 * Created by pokeoseu on 2017. 4. 24..
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    ImageView ivImg, ivLike, ivShare;
    TextView ivLikeCount, ivPostText, ivUserName;

    public PostViewHolder(View itemView) {
        super(itemView);

        ivImg = (ImageView) itemView.findViewById(R.id.postimg);
        ivLike = (ImageView) itemView.findViewById(R.id.iv_like);
        ivShare = (ImageView) itemView.findViewById(R.id.iv_share);

        ivLikeCount = (TextView) itemView.findViewById(R.id.iv_like_count);
        ivUserName = (TextView) itemView.findViewById(R.id.userName);
        ivPostText = (TextView) itemView.findViewById(R.id.post_text);
    }
}
