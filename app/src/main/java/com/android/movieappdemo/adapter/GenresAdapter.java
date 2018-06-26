package com.android.movieappdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movieappdemo.R;
import com.android.movieappdemo.activity.MainActivity;
import com.android.movieappdemo.model.Genre;

import java.util.List;

/**
 * Created by Dell on 26-Jun-18.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {
    List<Genre> mGenresList;
    MainActivity mActivity;
    Context mContext;

    public GenresAdapter(List<Genre> genresList, MainActivity mainActivity, int list_item_genre, Context applicationContext) {
        super();
        this.mContext = applicationContext;
        this.mGenresList = genresList;
        this.mActivity = mainActivity;
    }

    @Override
    public GenresAdapter.GenresViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_genre, viewGroup, false);
        return new GenresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, final int position) {
        holder.tvGenres.setText(mGenresList.get(position).getName());

        holder.tvGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.callMovieListAsPerGenres(mGenresList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGenresList.size();
    }

    public static class GenresViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvGenres;
//        private ItemClickListener clickListener;

        public GenresViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvGenres = (TextView) itemView.findViewById(R.id.tv_species);
         }
    }
}
