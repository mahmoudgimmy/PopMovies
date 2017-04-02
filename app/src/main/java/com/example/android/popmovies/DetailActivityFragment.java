package com.example.android.popmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.android.popmovies.R.id.toolbar;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    public DetailActivityFragment() {
    }
    private View rootView;
    private Movie model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        model = (Movie) getActivity().getIntent().getSerializableExtra("movie");

        ImageView image=(ImageView) rootView.findViewById(R.id.imageView2) ;
        Picasso.with(getActivity()).load(model.getPoster_path()).into(image);
        ((TextView) rootView.findViewById(R.id.textView2)).setText("Rating: "+model.getVote_average());
        ((TextView) rootView.findViewById(R.id.textView3)).setText(model.getOver_view());
        ((TextView) rootView.findViewById(R.id.textView4)).setText("Release Date: "+model.getRelease_date());
      getActivity().setTitle(model.getOriginal_title());

        return rootView;
    }



}
