package com.example.nblenovo.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    public TextView overviewText;
    public TextView titleText;
    public TextView dateText;
    public RatingBar ratingText;
    public ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        titleText = findViewById(R.id.movie_title);
        posterImageView = findViewById(R.id.moviePoster_iv);
        overviewText = findViewById(R.id.overView_tv);
        dateText = findViewById(R.id.release_date_tv);
        ratingText = findViewById(R.id.user_rating_tv);

        Intent movieIntent = getIntent();
        int position = movieIntent.getIntExtra("position", 0);

        if (MainActivity.movieList.get(position).getOverview() != null){
            overviewText.setText(MainActivity.movieList.get(position).getOverview());
    }
        if(MainActivity.movieList.get(position).getOriginal_title()!=null) {
            titleText.setText(MainActivity.movieList.get(position).getOriginal_title());
        }
        if(MainActivity.movieList.get(position).getVote_average().toString()!=null) {
          //  ratingText.setStepSize(MainActivity.movieList.get(position).getVote_average().byteValue());
            ratingText.setRating((MainActivity.movieList.get(position).getVote_average().byteValue())/2);
        }
        if(MainActivity.movieList.get(position).getRelease_date()!=null) {
            dateText.setText(MainActivity.movieList.get(position).getRelease_date());
        }
        if(MainActivity.images.get(position)!=null) {
            Picasso.with(getApplicationContext()).load(MainActivity.images.get(position)).into(posterImageView);
        }


    }
}
