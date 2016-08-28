package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sheetal.flickster.helpers.VideoPlayerHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

public class QuickPlayActivity extends YouTubeBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("DEBUG","Youtube id:"+id);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.playerOnDetail);


        VideoPlayerHelper videoPlayerHelper = new VideoPlayerHelper();

        videoPlayerHelper.showVideo(id, youTubePlayerView);
    }

}
