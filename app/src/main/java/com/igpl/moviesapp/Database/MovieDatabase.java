package com.igpl.moviesapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PopularMovie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract PopularMoviesDao popularMoviesDao();

}
