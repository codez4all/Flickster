package com.example.sheetal.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sheetal on 7/21/16.
 */
public class Movie {



    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getPopularity() {
        return popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getVideo() {
        return hasVideo;
    }

    public String posterPath;
    public String originalTitle;
    public String overview;
    public String backdropPath;
    public String popularity;
    public Double voteAverage;
    public String hasVideo;

    public Movie(JSONObject movieJsonObj) throws JSONException
    {

            this.posterPath = movieJsonObj.getString("poster_path");
            this.originalTitle = movieJsonObj.getString("original_title");
            this.overview = movieJsonObj.getString("overview");
            this.backdropPath = movieJsonObj.getString("backdrop_path");
            this.popularity = movieJsonObj.getString("popularity");
            this.voteAverage = movieJsonObj.getDouble("vote_average");
            this.hasVideo = movieJsonObj.getString("video");

    }

    public  static ArrayList<Movie> fromJasonArray(JSONArray array)
    {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i =0 ; i< array.length(); i++)
        {
            try {
                results.add(new Movie(array.getJSONObject(i)));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  results;
    }

}
