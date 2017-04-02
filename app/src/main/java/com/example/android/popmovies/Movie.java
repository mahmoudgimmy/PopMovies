package com.example.android.popmovies;

import java.io.Serializable;

import static android.R.attr.x;

/**
 * Created by mahmo on 31/03/2017.
 */

public class Movie implements Serializable {

    private String  poster_path;
    private String over_view;
    private String original_title;
    private String vote_average;
    private String release_date;
    private String backdrop_path;

    Movie( )
    {

    }

    String getPoster_path()
    {
        return poster_path;
    }

    String getOver_view()
    {
        return over_view;
    }
    String getOriginal_title()
    {
        return original_title;
    }
    String getVote_average()
    {
        return vote_average;
    }
    String getRelease_date()
    {
        return release_date;
    }


    void setPoster_path(String poster_path)
    {
        this.poster_path=poster_path;
    }
    void setOver_view(String over_view)
    {
        this.over_view=over_view;
    }
    void setOriginal_title(String original_title)
    {
        this.original_title=original_title;
    }
    void setVote_average(String vote_average)
    {
        this.vote_average=vote_average;
    }
    void setRelease_date(String release_date)
    {
        this.release_date=release_date;
    }

}
