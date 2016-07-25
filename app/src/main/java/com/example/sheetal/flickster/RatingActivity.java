package com.example.sheetal.flickster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


        TextView tvPopularity = (TextView)findViewById(R.id.tvPopularity);
        RatingBar rtBar = (RatingBar)findViewById(R.id.rtBar);

        Intent intent = getIntent();

        Double voteAverage = intent.getDoubleExtra("voteAverage", 0.0);
        String popularity = intent.getStringExtra("popularity");
        String hasVideo = intent.getStringExtra("hasVideo");

        rtBar.setRating(voteAverage.floatValue());
        tvPopularity.setText(popularity);
    }
}
