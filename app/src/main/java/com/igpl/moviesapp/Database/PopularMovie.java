package com.igpl.moviesapp.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "popular_movies_table")
public class PopularMovie {

    @NonNull
    @PrimaryKey
    private Integer id;
    private String poster_path,title,backdrop_path;
    private boolean fav;

    public PopularMovie() {
    }

    public PopularMovie(Integer id, String poster_path, String title, String backdrop_path) {
        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.backdrop_path = backdrop_path;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
