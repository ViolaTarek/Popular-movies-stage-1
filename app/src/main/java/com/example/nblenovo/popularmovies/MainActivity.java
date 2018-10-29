package com.example.nblenovo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public ArrayList<Movie> movieList;
    static public ArrayList<String> images;
    public Context currentContext;
    public static boolean enableConnect;
    static public gridAdapter mAdapter;
    static public GridView mgridView;
    public String mostPopular = "http://api.themoviedb.org/3/movie/popular?api_key=c2922d6e6c83600cad7a872acf059b18";
    public String TopRated = "http://api.themoviedb.org/3/movie/top_rated?api_key=c2922d6e6c83600cad7a872acf059b18";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        images = new ArrayList<>();
        currentContext = getApplicationContext();
        if (isNetworkAvailable() != false) {
            enableConnect = true;
            getJsonData(0); //popular
            new fetchMovies(currentContext);
            mgridView = (GridView) findViewById(R.id.gridview_main);
            mAdapter = new gridAdapter(movieList,images,MainActivity.this);
            mgridView.setAdapter(mAdapter);

            mgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent movieIntent = new Intent(getApplicationContext(), MovieDetails.class);
                    Log.i("Default position ", String.valueOf(position));
                    movieIntent.putExtra("position", position);
                    startActivity(movieIntent);
                }
            });

        } else {
            Toast.makeText(this, "Network Is Not Available", Toast.LENGTH_LONG).show();
            enableConnect = false;
        }
    }


    public void getJsonData(int i) {
        fetchMovies download = new fetchMovies(currentContext);
        try {
            if (i == 0)
                download.execute(mostPopular);
            else if (i == 1)
                download.execute(TopRated);

        }
           catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwinfo = connectivityManager.getActiveNetworkInfo();
        return nwinfo != null && nwinfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.popular_sort) {
            if (isNetworkAvailable() != false) {
                new fetchMovies(currentContext).execute(mostPopular);
                mAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Network is not available!", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.rating_sort) {
            if (isNetworkAvailable() != false) {
                new fetchMovies(currentContext).execute(TopRated);
                mAdapter.notifyDataSetChanged();
            } else
                Toast.makeText(this, "Network is not available!", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }

}
