package com.android.movieappdemo.rest;

import com.android.movieappdemo.model.GenresResponse;
import com.android.movieappdemo.model.MoviesResponse;
import com.android.movieappdemo.model.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {

    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(@Query("api_key") String apiKey);
    @GET("genre/{genre_id}/movies")
    Call<MoviesResponse> getMoviesByGenres(@Path("genre_id") int genre_id, @Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("genre/movielist")
    Call<MoviesResponse> getMovieslist(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowplayingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvResponse> getTVDetails(@Path("tv_id") int id, @Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<TvResponse> getTopRatedTv(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TvResponse> getPopularTv(@Query("api_key") String apiKey);

    @GET("tv/on_the_air")
    Call<TvResponse> getONTv(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    Call<TvResponse> getAiringTv(@Query("api_key") String apiKey);
}
