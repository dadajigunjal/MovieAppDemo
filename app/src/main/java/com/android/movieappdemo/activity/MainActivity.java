package com.android.movieappdemo.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.movieappdemo.MyRecyclerItemClickListener;
import com.android.movieappdemo.R;
import com.android.movieappdemo.adapter.GenresAdapter;
import com.android.movieappdemo.adapter.MoviesAdapter;
import com.android.movieappdemo.adapter.ShowAdapter;
import com.android.movieappdemo.model.Genre;
import com.android.movieappdemo.model.GenresResponse;
import com.android.movieappdemo.model.Movie;
import com.android.movieappdemo.model.MoviesResponse;
import com.android.movieappdemo.model.TvResponse;
import com.android.movieappdemo.model.TvShows;
import com.android.movieappdemo.rest.APIClient;
import com.android.movieappdemo.rest.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView, recycler_view_geners;

    LinearLayout btnlayout, moviebtnlayout,showbtnlayout;

    ProgressDialog progressDialog;


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "f17e9c5e6c34ad9dc2bf6aab852c0cc7"; //"5322d1109bc9489f0d5961c99f4faf34";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recycler_view_geners = (RecyclerView) findViewById(R.id.recycler_view_geners);
        recycler_view_geners.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviebtnlayout = (LinearLayout)findViewById(R.id.moviebtnlayout);
        moviebtnlayout.setVisibility(View.VISIBLE);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading movies, please wait..!");

        loadGenresList();
        callMovieListAsPerGenres(28);

        recyclerView.addOnItemTouchListener(
                new MyRecyclerItemClickListener(this, new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                   //     showCustomDialog(view,position);
                    }
                })
        );

    }

    private void loadGenresList() {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<GenresResponse> call = apiService.getGenres(API_KEY);
        call.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
//                int statusCode = response.code();
                // The number of Columns
                mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recycler_view_geners.setLayoutManager(mLayoutManager);

                List<Genre> genresList = response.body().getGenres();
                recycler_view_geners.setAdapter(new GenresAdapter(genresList, MainActivity.this, R.layout.list_item_genre, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    public void callDetailPage(String imagePath, Double userRating, String movieName,
                               String description, String releaseDate) {
        Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
        intent.putExtra("image_name",imagePath);
        intent.putExtra("userRating", userRating);
        intent.putExtra("movieName",movieName);
        intent.putExtra("description",description);
        intent.putExtra("releaseDate",releaseDate);
        startActivity(intent);
    }

    public void callMovieListAsPerGenres(final Integer genresId) {
        if (isConnectingToInternet()) {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.show();
            }
            APIInterface apiService =
                    APIClient.getClient().create(APIInterface.class);

            Call<MoviesResponse> call = apiService.getMoviesByGenres(genresId, API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    int statusCode = response.code();
                    List<Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(movies, MainActivity.this, R.layout.list_item_movie, getApplicationContext()));
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MainActivity.this, "Something went wrong..!", Toast.LENGTH_SHORT).show();
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }else{
            Toast.makeText(this, "Check your internet connection and try again..!", Toast.LENGTH_SHORT).show();
        }
    }

    /* method to check for internet connection */
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager)this.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
}
