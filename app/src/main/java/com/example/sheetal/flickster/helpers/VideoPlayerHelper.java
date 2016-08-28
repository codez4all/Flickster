package com.example.sheetal.flickster.helpers;

import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sheetal on 8/27/16.
 */
public class VideoPlayerHelper  {

    String source =null ;
    AsyncHttpClient client;
    JSONArray videoJsonResult = null;

    public VideoPlayerHelper()
    {

    }

    public void showVideo(String id, final YouTubePlayerView playerOnDetailView)
    {
        String url = "https://api.themoviedb.org/3/movie/"+id+"/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        //final int itemID = itemId;

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
