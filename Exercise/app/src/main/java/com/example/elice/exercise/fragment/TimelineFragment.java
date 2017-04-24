package com.example.elice.exercise.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elice.exercise.PostActivity;
import com.example.elice.exercise.R;
import com.example.elice.exercise.api.Post;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    public static final String GET_POST = "https://apis.daum.net/search/image?apikey=68bb419bdb007bd752defe7dfcddbd21&q=농구&output=json";
    public ArrayList<Post.Channel.Item> arrayList;
    public PostAdapter postdapter;

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fetchAsyncPosts();
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);
        arrayList = new ArrayList<>();
        RecyclerView rvList = (RecyclerView) v.findViewById(R.id.rv_list);
        postdapter = new PostAdapter(getActivity(), arrayList);

        rvList.setAdapter(postdapter);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        v.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getActivity().getPackageManager())!= null){
                    startActivityForResult(cameraIntent, 1000);
                }
            }
        });
        return v;
    }

    private void fetchAsyncPosts() {
        arrayList = new ArrayList<>();
        FetchPostTask fetch = new FetchPostTask();
        fetch.execute(GET_POST);
    }
    class FetchPostTask extends AsyncTask<String, Void, Post.Channel.Item[]> {

        @Override
        protected Post.Channel.Item[] doInBackground(String... params) {

            String url = params[0];

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(url).build();

            try {
                Response response = client.newCall(request).execute();

                Gson gson = new Gson();
                Post.Channel.Item[] posts = gson.fromJson(response.body().charStream(), Post.Channel.Item[].class);

                return posts;

            } catch (IOException e) { //서버와 통신이 안될때
                Log.d("fetchposttast", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Post.Channel.Item[] items) {
            super.onPostExecute(items);
            for (Post.Channel.Item item : items) {
                arrayList.add(item);
            }
            postdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1000 && resultCode == getActivity().RESULT_OK ) {
            Log.d("onActivityResult", "success!!");
            Intent startIntent = new Intent(getActivity(), PostActivity.class);
            startIntent.setData(data.getData());
            startActivity(startIntent);
        }
    }

}
