package com.example.sheetal.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sheetal.flickster.adaptors.MovieArrayAdaptor;
import com.example.sheetal.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity
{

    ArrayList<Movie> movies;
    MovieArrayAdaptor movieArrayAdaptor;
    ListView lvItems;
    String url;
    AsyncHttpClient client;
    private SwipeRefreshLayout swipeRefreshContainer;
    RecyclerView rvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        movies = new ArrayList<>();
        movieArrayAdaptor = new MovieArrayAdaptor(this, movies);
        lvItems = (ListView) findViewById(R.id.lvMovies);

        lvItems.setAdapter(movieArrayAdaptor);

        url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        client = new AsyncHttpClient();

        fetchTimelineAsync();

        swipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeRefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync();

            }
        });


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Movie movie= (Movie) lvItems.getItemAtPosition(position);

                if(((int) movie.getVoteAverage())> 5)
                {
                    Intent intentPlayer = new Intent(MovieActivity.this, QuickPlayActivity.class);
                    startActivity(intentPlayer);
                }
                else {

                    Intent intentRating = new Intent(MovieActivity.this, RatingActivity.class);
                    intentRating.putExtra("id",movie.getId());
                    intentRating.putExtra("voteAverage", movie.getVoteAverage());
                    intentRating.putExtra("popularity", movie.getPopularity());
                    intentRating.putExtra("hasVideo", movie.getVideo());
                    intentRating.putExtra("synopsis",movie.getOverview());
                    startActivity(intentRating);
                }

            }
        });
    }

    public void fetchTimelineAsync()
    {
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                movies.clear();
                movieArrayAdaptor.clear();

                JSONArray movieJsonResult = null;
                try {
                    movieJsonResult = response.getJSONArray("results");
                    movies.addAll(Movie.fromJasonArray(movieJsonResult));
                    movieArrayAdaptor.notifyDataSetChanged();
                    Log.d("Debug", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("failed JSON array ","");
            }
        });
    }


}

