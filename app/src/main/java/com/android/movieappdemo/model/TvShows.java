package com.android.movieappdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 26-09-2017.
 */

public class TvShows {

    @SerializedName("original_name")
    private String originalname;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("vote_count")
    private Integer votecount;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("first_air_date")
    private String firstairdate;
    @SerializedName("backdrop_path")
    private String backdroppath;
    @SerializedName("original_language")
    private String originallanguage;

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Integer getVotecount() {
        return votecount;
    }

    public void setVotecount(Integer votecount) {
        this.votecount = votecount;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstairdate() {
        return firstairdate;
    }

    public void setFirstairdate(String firstairdate) {
        this.firstairdate = firstairdate;
    }

    public String getBackdroppath() {
        return backdroppath;
    }

    public void setBackdroppath(String backdroppath) {
        this.backdroppath = backdroppath;
    }

    public String getOriginallanguage() {
        return originallanguage;
    }

    public void setOriginallanguage(String originallanguage) {
        this.originallanguage = originallanguage;
    }

    public Double getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(Double voteaverage) {
        this.voteaverage = voteaverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public List<String> getOrigincountry() {
        return origincountry;
    }

    public void setOrigincountry(List<String> origincountry) {
        this.origincountry = origincountry;
    }

    @SerializedName("vote_average")
    private Double voteaverage;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String posterpath;
    @SerializedName("origin_country")
    private List<String> origincountry = new ArrayList<String>();

    public TvShows(String originalname, String name, String popularity, Integer votecount, List<Integer> genreIds, Integer id,
                 String firstairdate, String backdroppath, String originallanguage, Double voteaverage, String overview,
                 String posterpath, List<String>origincountry) {
        this.originalname = originalname;
        this.name = name;
        this.popularity = popularity;
        this.votecount = votecount;
        this.genreIds = genreIds;
        this.id = id;
        this.firstairdate = firstairdate;
        this.backdroppath = backdroppath;
        this.originallanguage = originallanguage;
        this.voteaverage = voteaverage;
        this.overview = overview;
        this.posterpath = posterpath;
        this.origincountry = origincountry;
    }

}
