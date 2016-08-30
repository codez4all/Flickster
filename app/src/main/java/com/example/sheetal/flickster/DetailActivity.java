package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sheetal.flickster.helpers.VideoPlayerHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends YouTubeBaseActivity {

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.rtBar) RatingBar rtBar;
    @BindView(R.id.tvSynopsis) TextView tvSynopsis;
    @BindView(R.id.playerOnDetail) YouTubePlayerView playerOnDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

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

        tvTitle.setText(title);
        tvReleaseDate.setText(release_date);
        rtBar.setRating(voteAverage.floatValue());
        tvSynopsis.setText(synopsis);

    }

}

