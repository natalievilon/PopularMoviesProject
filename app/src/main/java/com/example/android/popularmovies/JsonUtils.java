package com.example.android.popularmovies;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by admin on 2017-01-09.
 */

public final class JsonUtils {

    public static String [][] getJSONdata(Context context, String moviedata)
            throws JSONException {

        JSONObject results = new JSONObject(moviedata);
        JSONArray array = results.getJSONArray("results");

        String[] parsedMovieData = null;
        parsedMovieData = new String[array.length()];
//
//        for (int i = 0; i < array.length(); i++){
//            JSONObject obj = array.getJSONObject(i);
//            String title = obj.getString("title");
//            String poster_image = obj.getString("poster_path");
//            String overview = obj.getString("overview");
//            String vote = obj.getString("vote_average");
//            String release_date = obj.getString("release_date");
//            parsedMovieData[0] = "test";
//            //System.out.print(title.toString());
//            //Log.i("JSON", "Movie Info says: " + title + poster_image + overview + vote + release_date);
//
//            //Log.i("JSON", "Parsed Movie Data: " + parsedMovieData[0]);
//        }

        // Definitions
        String[] metadataKeys = {"title", "poster_path", "overview", "vote_average", "release_date"};
        JSONArray filterResults = (new JSONObject(moviedata)).getJSONArray("results");
        String[][] movieMetaData = new String[filterResults.length()][metadataKeys.length];


        // Loops through keys in filterResults
        for (int i = 0; i < filterResults.length(); i++){
            for (int j = 0; j < metadataKeys.length; j++) {
                // Gets each Movie Metadata from filterResults JSONObject
                movieMetaData[i][j] = filterResults.getJSONObject(i).getString(metadataKeys[j]);
                Log.i("JSON", i +" > " + j + " > "+ movieMetaData[i][j]);
            }
        }

        return movieMetaData;
    }
}
