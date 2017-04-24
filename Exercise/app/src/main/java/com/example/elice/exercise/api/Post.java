package com.example.elice.exercise.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pokeoseu on 2017. 4. 24..
 */

public class Post {

    public Channel channel;

    public class Channel {

        public static final String url = "https://apis.daum.net/search/image?apikey=68bb419bdb007bd752defe7dfcddbd21&q=농구&output=json";


        @SerializedName("item")
        @Expose
        public List<Item> item ;

        public class Item {

            @SerializedName("title")
            @Expose
            public String title;
            @SerializedName("height")
            @Expose
            public String height;
            @SerializedName("image")
            @Expose
            public String image;
            @SerializedName("cpname")
            @Expose
            public String cpname;

            public String getTitle() {
                return title;
            }

            public String getHeight() {
                return height;
            }

            public String getImage() {
                return image;
            }

            public String getCpname() {
                return cpname;
            }
        }
    }

}
