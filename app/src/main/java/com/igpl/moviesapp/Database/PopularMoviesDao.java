package com.igpl.moviesapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.igpl.moviesapp.Models.Movie;

import java.util.List;

@Dao
public interface PopularMoviesDao {

    @Insert
    void insert(PopularMovie movie);

    @Query("DELETE FROM popular_movies_table")
    void delete();

    @Query("SELECT * FROM popular_movies_table")
    LiveData<List<PopularMovie>> getAllPopularMovies();

    @Query("DELETE FROM popular_movies_table WHERE id = :movieId")
    void deleteMovie(int movieId);

}
