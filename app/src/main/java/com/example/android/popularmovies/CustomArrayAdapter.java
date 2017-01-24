package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by admin on 2017-01-14.
 */

public class CustomArrayAdapter extends ArrayAdapter<PosterImage> {

    public CustomArrayAdapter(Context context, int resource, String[][] posterimage) {
        super(context, 0, 0);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PosterImage posterImage = getItem(position);

        if (convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item, parent, false);
        }

        ImageView posterImg = (ImageView) convertView.findViewById(R.id.poster_image);
        posterImg.setImageResource(posterImage.image);

        return convertView;

    }
}
