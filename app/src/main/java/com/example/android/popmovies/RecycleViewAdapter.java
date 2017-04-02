package com.example.android.popmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahmo on 31/03/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.Holder>
{
    private ArrayList<Movie> movies;
    private Context context;
    private AppCompatActivity appCompatActivity;
    public RecycleViewAdapter(Context context, ArrayList<Movie> movies, AppCompatActivity appCompatActivity)
    {
        this.context=context;
        this.appCompatActivity=appCompatActivity;
        this.movies=movies;

    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        Holder holder=new Holder(row);
        return holder;
    }




    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        Picasso.with(context).load(movies.get(position).getPoster_path()).into(holder.poster);
        holder.movieName.setText(movies.get(position).getOriginal_title());

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent move=new Intent(context,DetailActivity.class).putExtra("movie",movies.get(position));
                    context.startActivity(move);

            }
        });

    }




    @Override
    public int getItemCount() {
        return movies.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView movieName;
        public Holder(View itemView){
            super(itemView);
            poster=(ImageView) itemView.findViewById(R.id.movie_image);
            movieName=(TextView) itemView.findViewById(R.id.movie_name);
        }
    }

}
