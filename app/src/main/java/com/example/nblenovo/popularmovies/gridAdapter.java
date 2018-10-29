package com.example.nblenovo.popularmovies;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class gridAdapter extends BaseAdapter {
    public LayoutInflater inflater;
    public ArrayList<Movie> movieList;
    public ArrayList<String> images;
    public Context context;


    public gridAdapter(ArrayList<Movie> movieList, ArrayList<String> images, Context context) {
        this.movieList = movieList;
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridLayout= convertView;

        if (convertView == null){
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridLayout=inflater.inflate(R.layout.movie_item,null);
        }

        ImageView movieImage=(ImageView)gridLayout.findViewById(R.id.movieImage);

        //
        Picasso.with(context).load(MainActivity.images.get(position)).into(movieImage);
       // movieName.setText(MainActivity.movieList.get(position).original_title);


        return gridLayout;


    }
}
