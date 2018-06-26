package com.android.movieappdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.movieappdemo.R;
import com.android.movieappdemo.activity.MainActivity;
import com.android.movieappdemo.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 20-09-2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    public MainActivity mainActivity;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView poster;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            poster = (ImageView) v.findViewById(R.id.movieimage);
        }
    }

    public MoviesAdapter(List<Movie> movies, MainActivity activity, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mainActivity = activity;
    }



    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        String path = "https://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath();

        Picasso.with(context).load(path).into(holder.poster);
       /* Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w500/"+path)
                .error(R.drawable.error_gallery_image)
                .into(holder.poster);*/

        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.callDetailPage(
                        "http://image.tmdb.org/t/p/w500/"+movies.get(position).getPosterPath(),
                        movies.get(position).getVoteAverage(),
                        movies.get(position).getTitle(),
                        movies.get(position).getOverview(),
                        movies.get(position).getReleaseDate());
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
