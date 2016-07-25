package com.example.sheetal.flickster.adaptors;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheetal.flickster.R;
import com.example.sheetal.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sheetal on 7/21/16.
 */
public class MovieArrayAdaptor extends ArrayAdapter<Movie> {

    private static class ViewHolder
    {
        TextView txtViewTitle;
        TextView txtViewOverview;
        ImageView imgView;

    }

    private  static  class ViewHolderPopular
    {
        ImageView imgPopular;
    }

    public  MovieArrayAdaptor(Context context, List<Movie> movies)
    {
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get data item from the position
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        ViewHolderPopular viewHolderPopular;
        viewHolder = new ViewHolder();
        viewHolderPopular = new ViewHolderPopular();

        // check if existing view being reused

       //if(convertView == null)
        {
           // int type = getItemViewType(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (((int) movie.getVoteAverage()) >5)
            {

                convertView = inflater.inflate(R.layout.movie_popular, parent, false);

                viewHolderPopular.imgPopular =(ImageView) convertView.findViewById(R.id.ivPopular);

                convertView.setTag(viewHolderPopular);

            }
            else if(((int) movie.getVoteAverage()) <=5)
            {
                convertView = inflater.inflate(R.layout.movie_item, parent, false);

                //find the image view ,text views
                viewHolder.imgView = (ImageView) convertView.findViewById(R.id.ivImage);
                viewHolder.imgView.setImageResource(0);
                viewHolder.txtViewTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.txtViewOverview= (TextView) convertView.findViewById(R.id.tvOverview);

                convertView.setTag(viewHolder);

            }

        }
     /*  else
        {
            if (((int) movie.getVoteAverage()) >5) {
                viewHolderPopular = (ViewHolderPopular) convertView.getTag(1);

            }
            else if(((int) movie.getVoteAverage()) <=5)
            {
                viewHolder = (ViewHolder) convertView.getTag(2);
            }

        }
        */


       // viewHolder.txtViewTitle.setText(movie.getOriginalTitle().toString());
       // viewHolder.txtViewOverview.setText(movie.getOverview().toString());

        int orientation = this.getContext().getResources().getConfiguration().orientation;

        // with placeholder
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Picasso.with(this.getContext()).load(movie.getPosterPath()).into(viewHolder.imgView);
            if( ((int) movie.getVoteAverage())>5)
            {
                Picasso.with(this.getContext()).load(movie.getBackdropPath()).placeholder(R.mipmap.ic_movie_placeholder_error)
                        .error(R.mipmap.ic_movie_placeholder_error).into(viewHolderPopular.imgPopular);

            }
            else if(((int) movie.getVoteAverage()) <=5) {
                 viewHolder.txtViewTitle.setText(movie.getOriginalTitle().toString());
                 viewHolder.txtViewOverview.setText(movie.getOverview().toString());

                 Picasso.with(this.getContext()).load(movie.getPosterPath()).placeholder(R.mipmap.ic_movie_placeholder_error)
                        .error(R.mipmap.ic_movie_placeholder_error).into(viewHolder.imgView);
            }
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Picasso.with(this.getContext()).load(movie.getBackdropPath()).into(viewHolder.imgView);
            if( ((int) movie.getVoteAverage())>5)
            {
                Picasso.with(this.getContext()).load(movie.getBackdropPath()).placeholder(R.mipmap.ic_movie_placeholder_error)
                        .error(R.mipmap.ic_movie_placeholder_error).into(viewHolderPopular.imgPopular);

            }
            else if(((int) movie.getVoteAverage()) <=5) {

                viewHolder.txtViewTitle.setText(movie.getOriginalTitle().toString());
                viewHolder.txtViewOverview.setText(movie.getOverview().toString());

                Picasso.with(this.getContext()).load(movie.getPosterPath()).placeholder(R.mipmap.ic_movie_placeholder_error)
                        .error(R.mipmap.ic_movie_placeholder_error).into(viewHolder.imgView);
            }
        }

        return convertView;

    }


}
