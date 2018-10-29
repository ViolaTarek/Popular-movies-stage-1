package com.example.nblenovo.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class fetchMovies extends AsyncTask<String, Void, String> {
    public Context currentContext;

    public fetchMovies(Context Context) {
        currentContext = Context;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (MainActivity.enableConnect) {
            MainActivity.movieList = new ArrayList<>();
            MainActivity.images = new ArrayList<>();
            try {
                JSONObject movieObject = new JSONObject(s);
                JSONArray movieArray = movieObject.getJSONArray("results");
                final int nOfMovies = movieArray.length();

                for (int i = 0; i < nOfMovies; i++) {
                    JSONObject movie = movieArray.getJSONObject(i);
                    Movie movieItem = new Movie();
                    movieItem.setOriginal_title(movie.getString("original_title"));
                    if (movie.getString("overview") == null) {
                        movieItem.setOverview("No over View found");
                    } else {
                        movieItem.setOverview(movie.getString("overview"));
                    }
                    if (movie.getString("release_date") == null) {
                        movieItem.setRelease_date("no release date is found");
                    } else {
                        movieItem.setRelease_date(movie.getString("release_date"));
                    }
                    movieItem.setVote_average(movie.getDouble("vote_average"));
                    movieItem.setPoster_path(movie.getString("poster_path"));
                    if (movie.getString("poster_path") == null) {
                        MainActivity.images.add(Constants.IMAGE_NOT_FOUND);
                        movieItem.setPoster_path(Constants.IMAGE_NOT_FOUND);
                    } else {
                        MainActivity.images.add(Constants.IMAGE_URL + Constants.IMAGE_SIZE_185 + movie.getString("poster_path"));
                    }
                    MainActivity.movieList.add(movieItem);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            MainActivity.mAdapter.notifyDataSetChanged();
        } else {
            //network is not working
            Toast.makeText(currentContext, "Network Is Not Available", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
