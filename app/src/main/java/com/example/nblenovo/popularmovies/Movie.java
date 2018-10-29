package com.example.nblenovo.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Movie implements Parcelable {
    int id;
    private String original_title;
   private String overview;
   private String poster_path;
   private Double vote_average;
   private String Release_date;


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return Release_date;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public void setRelease_date(String release_date) {
        Release_date = release_date;
    }


    private Movie(Parcel parcel) {
        this.original_title = parcel.readString();
        this.overview = parcel.readString();
        this.poster_path = parcel.readString();
        this.vote_average = parcel.readDouble();
        this.Release_date = parcel.readString();
    }

    public Movie() {
        id=0;
        original_title="";
        Release_date = "";
        overview ="";
        poster_path="";
        vote_average=0.0;

    }
    public static void showmovieData(Movie m){
        Log.i("ID :", String.valueOf(m.id));
        Log.i("Title :",m.original_title);
        Log.i("Poster :",m.poster_path);
        Log.i("Vote Avg :", String.valueOf(m.vote_average));
        Log.i("Release Date : :",m.Release_date);
        Log.i("over view : :",m.overview);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(vote_average);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(Release_date);
    }
}
