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
import com.android.movieappdemo.model.TvShows;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 26-09-2017.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private List<TvShows> shows;
    private int rowLayout;
    private Context context;


    public static class ShowViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView poster;


        public ShowViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            poster = (ImageView) v.findViewById(R.id.movieimage);
        }
    }

    public ShowAdapter(List<TvShows> shows, int rowLayout, Context context) {
        this.shows = shows;
        this.rowLayout = rowLayout;
        this.context = context;
    }



    @Override
    public ShowAdapter.ShowViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ShowAdapter.ShowViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ShowAdapter.ShowViewHolder holder, final int position) {
        holder.movieTitle.setText(shows.get(position).getOriginalname());
        holder.data.setText(shows.get(position).getFirstairdate());
        holder.movieDescription.setText(shows.get(position).getOverview());
        holder.rating.setText(shows.get(position).getVoteaverage().toString());
        String path = "https://image.tmdb.org/t/p/w500" + shows.get(position).getPosterpath();

        Picasso.with(context).load(path).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }
}
