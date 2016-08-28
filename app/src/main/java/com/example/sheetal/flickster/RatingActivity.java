package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sheetal.flickster.helpers.VideoPlayerHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;

public class RatingActivity extends YouTubeBaseActivity {

    AsyncHttpClient client;
    JSONArray videoJsonResult = null;
    String source;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        RatingBar rtBar = (RatingBar)findViewById(R.id.rtBar);
        TextView tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        YouTubePlayerView playerOnDetailView = (YouTubePlayerView) findViewById(R.id.playerOnDetail);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        Double voteAverage = intent.getDoubleExtra("voteAverage", 0.0);
        String popularity = intent.getStringExtra("popularity");
        String hasVideo = intent.getStringExtra("hasVideo");
        String synopsis = intent.getStringExtra("synopsis");
        String release_date = "Release Date: "+ intent.getStringExtra("release_date");

        VideoPlayerHelper videoPlayerHelper = new VideoPlayerHelper();

        videoPlayerHelper.showVideo(id, playerOnDetailView);
        Log.d("DEBUG", "Youtube id:" + id);

        //showVideo(id);

        tvTitle.setText(title);
        tvReleaseDate.setText(release_date);
        rtBar.setRating(voteAverage.floatValue());
        tvSynopsis.setText(synopsis);

    }

}

