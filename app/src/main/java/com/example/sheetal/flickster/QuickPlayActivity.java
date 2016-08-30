package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sheetal.flickster.helpers.VideoPlayerHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuickPlayActivity extends YouTubeBaseActivity {

    @BindView(R.id.playerOnDetail) YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("DEBUG","Youtube id:"+id);

        VideoPlayerHelper videoPlayerHelper = new VideoPlayerHelper();

        videoPlayerHelper.showVideo(id, youTubePlayerView);
    }

}
