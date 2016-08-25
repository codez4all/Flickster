package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

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

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        Double voteAverage = intent.getDoubleExtra("voteAverage", 0.0);
        String popularity = intent.getStringExtra("popularity");
        String hasVideo = intent.getStringExtra("hasVideo");
        String synopsis = intent.getStringExtra("synopsis");
        String release_date = "Release Date: "+ intent.getStringExtra("release_date");


        showVideo(id);

        tvTitle.setText(title);
        tvReleaseDate.setText(release_date);
        rtBar.setRating(voteAverage.floatValue());
        tvSynopsis.setText(synopsis);

    }


    public void showVideo(String id)
    {
        String url = "https://api.themoviedb.org/3/movie/"+id+"/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                try {
                    videoJsonResult = response.getJSONArray("youtube");

                    for (int i = 0; i < videoJsonResult.length(); i++) {
                        JSONObject obj = videoJsonResult.getJSONObject(i);
                        source = obj.getString("source");

                    }

                    if (source != null) {

                        YouTubePlayerView playerOnDetailView = (YouTubePlayerView) findViewById(R.id.playerOnDetail);
                        playerOnDetailView.initialize("AIzaSyAkRl_3AQSMKeczZWieReo6JhglolvsmOg",
                                new YouTubePlayer.OnInitializedListener() {
                                    @Override
                                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                        YouTubePlayer youTubePlayer, boolean b) {

                                        // do any work here to cue video, play video, etc.
                                        youTubePlayer.cueVideo(source);
                                        // youTubePlayer.cueVideo(videoJsonResult.getJSONArray);
                                    }

                                    @Override
                                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                        YouTubeInitializationResult youTubeInitializationResult) {
                                        Log.d("Video Failed", "");
                                    }
                                });
                    }

                    Log.d("Debug", videoJsonResult.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("failed JSON array ", "");
            }

        });


    }
}

