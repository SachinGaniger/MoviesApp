package com.igpl.moviesapp.DependencyInjection;

import android.app.Application;

import androidx.room.Room;

import com.igpl.moviesapp.Database.MovieDatabase;
import com.igpl.moviesapp.Database.PopularMoviesDao;
import com.igpl.moviesapp.Utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase(Application application){
        return Room.databaseBuilder(application, MovieDatabase.class, Constants.DataBaseName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

    @Provides
    @Singleton
    PopularMoviesDao providePopularMoviesDao(MovieDatabase movieDatabase){

        return movieDatabase.popularMoviesDao();

    }

}
