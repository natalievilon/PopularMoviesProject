package com.example.android.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by admin on 2017-01-02.
 */

public final class NetworkUtils {

    private static final String MOVIE_API_URL =
            "http://api.themoviedb.org/3/movie/";
    private static final String API_VALUE = "";
    private static final String LANGUAGE_VALUE = "en-us";
    private static final int PAGE_NUM = 1;

    private static final String API_KEY = "api_key";
    private static final String LANGUAGE_KEY = "language";
    private static final String PAGE_KEY = "page";

    public static URL buildURL(String categoryQuery) {

        Uri builtUri = Uri.parse(MOVIE_API_URL).buildUpon()
                .appendPath(categoryQuery)
                .appendQueryParameter(API_KEY, API_VALUE)
                .appendQueryParameter(LANGUAGE_KEY, LANGUAGE_VALUE)
                .appendQueryParameter(PAGE_KEY, Integer.toString(PAGE_NUM))
                .build();
        Log.i("NetworkUtils", "buildURL: " + builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.i("NetworkUtils", "url:"+url);
        try {
            InputStream in = urlConnection.getInputStream();
            Log.i("NetworkUtils", "getResponseFromHttpUrl in:"+in);
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}


