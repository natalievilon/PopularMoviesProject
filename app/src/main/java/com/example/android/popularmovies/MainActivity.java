package com.example.android.popularmovies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends ActionBarActivity implements Serializable{

    private TextView mResultsTextView;
    private MenuItem mPopularFilter;
    private MenuItem mTopRatedFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainA", "onCreate-MainActivity");
        super.onCreate(savedInstanceState);
        getPopularURL();
        setContentView(R.layout.activity_main);
        //mResultsTextView = (TextView) findViewById(R.id.results);
        mPopularFilter = (MenuItem) findViewById(R.id.popular_filter);
        mTopRatedFilter = (MenuItem) findViewById(R.id.top_rated_filter);
    }

    private void getPopularURL(){
        String mPopularURL = "popular";
        URL popularURL = NetworkUtils.buildURL(mPopularURL);
        new MovieQueryTask().execute(popularURL);
    }

    private void getTopRatedURL(){
        String mTopRatedURL = "top_rated";
        URL topratedURL = NetworkUtils.buildURL(mTopRatedURL);
        new MovieQueryTask().execute(topratedURL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("MainA", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("MainA", "onOptionsItemSelected");
        int itemThatWasClicked = item.getItemId();
        if(itemThatWasClicked == R.id.popular_filter){
            getPopularURL();
        }
        else{
            getTopRatedURL();
        }
        return super.onOptionsItemSelected(item);
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL APIURLS = urls[0];
            String APIResults = null;
            Log.i("MainA", "doInBackground");
            try {
                APIResults = NetworkUtils.getResponseFromHttpUrl(APIURLS);
                Log.i("MainA", APIResults);

            }
            catch (IOException e){
                Log.i("MainA", "doInBackground has Failed");
                e.printStackTrace();
            }
            return APIResults;
        }

        @Override
        protected void onPostExecute(String URLResults) {
            Log.i("MainA", "onPostExecute: ");
            String [][] moviesMetaData = null ;
            if (URLResults != null && !URLResults.equals("")){
                try {
                    //
                    moviesMetaData = JsonUtils.getJSONdata(MainActivity.this, URLResults);

                    Fragment fragment = new MainActivityFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movies", moviesMetaData);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManger = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManger.beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment);
                    fragmentTransaction.commit();

                    //String movies = ((String [][]) bundle.getSerializable("movies"))[0][1];
                    //Log.i("BigD", "movies: " + movies);

                } catch (JSONException e) {
                    Log.i("MainA", "error OnPostExecute");
                    e.printStackTrace();
                }
            }
            //mResultsTextView.setText(moviesMetaData[1][0]);
        }
    }
}


