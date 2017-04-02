package com.example.android.popmovies;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import static com.example.android.popmovies.R.id.item1;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivity mainActivity=new MainActivity();
    private View rootView;
    private Movie movie;
    private ArrayList<Movie> movies;
    private ProgressBar loadIndicator;
    private RecycleViewAdapter movieAdapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        loadIndicator=(ProgressBar) rootView.findViewById(R.id.loading_indicator);

        movies = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        movieAdapter = new RecycleViewAdapter(getContext(), movies, (AppCompatActivity) getActivity());
        recyclerView.setAdapter(movieAdapter);
        loadIndicator.setVisibility(View.VISIBLE);
        fetchDataFromInternet(getString(R.string.popular));
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== item1)
        {
            loadIndicator.setVisibility(View.VISIBLE);
            fetchDataFromInternet((String) item.getTitleCondensed());
            item.setChecked(true);

        }
        else if(id==R.id.item2)
        {
            loadIndicator.setVisibility(View.VISIBLE);
            fetchDataFromInternet((String) item.getTitleCondensed());
            item.setChecked(true);

        }
        return super.onOptionsItemSelected(item);
    }


    void fetchDataFromInternet(String sortType)
    {
        Ion.with(this)
                .load("http://api.themoviedb.org/3/movie/" + sortType + "?api_key=26f815524992597fd9ea20ab50c1e4fd")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if (e == null) {
                            JsonArray j = result.getAsJsonArray("results");
                            movies.clear();

                            for (int i = 0; i < j.size(); i++) {
                                movie=new Movie();
                                movie.setPoster_path("http://image.tmdb.org/t/p/w342/" + (((JsonObject) j.get(i)).get("poster_path").toString()).replace("\"", ""));
                                movie.setOver_view((((JsonObject) j.get(i)).get("overview").toString()).replace("\"", "").replace("\\", "").replace("/", ""));
                                movie.setOriginal_title((((JsonObject) j.get(i)).get("original_title").toString()).replace("\"", "").replace("\\", "").replace("/", ""));
                                movie.setVote_average((((JsonObject) j.get(i)).get("vote_average").toString()));
                                movie.setRelease_date((((JsonObject) j.get(i)).get("release_date").toString()).replace("\"", "").replace("\\", "").replace("/", ""));
                                movies.add(movie);
                            }

                            movieAdapter.notifyDataSetChanged();
                            loadIndicator.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            loadIndicator.setVisibility(View.INVISIBLE);
                            Toast toast =Toast.makeText(getContext(),"No internet connection has been available",Toast.LENGTH_LONG);
                            toast.show();

                        }

                    }
                });


    }


}
