package com.example.android.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017-01-14.
 */

public class MainActivityFragment extends Fragment implements Serializable{

    CustomArrayAdapter adapter;

    public MainActivityFragment (){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment", "onCreate-MA-Fragment");
        //Bundle fbundle = new Bundle();
        //fbundle.getSerializable("movies");
        //String[][] test = (String [][]) savedInstanceState.getSerializable("movies");
        String[][] test = (String [][]) this.getArguments().getSerializable("movies");


        Log.i("Fragment", "serializable");
        //String [][] movies = (String [][]) fbundle.getSerializable("movies");
        //Log.i("Fragment", "fBundle: " + movies.toString());


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.i("Fragment", "inflate");

        //adapter = new CustomArrayAdapter(getActivity(), 0, movies);
        Log.i("Fragment", "customerarray");

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        Log.i("Fragment", "gridview");
        gridView.setAdapter(adapter);
        Log.i("Fragment", "last step");

        return rootView;

    }

}
