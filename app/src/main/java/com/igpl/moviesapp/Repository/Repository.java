package com.igpl.moviesapp.Repository;

import androidx.lifecycle.LiveData;

import com.igpl.moviesapp.Database.PopularMovie;
import com.igpl.moviesapp.Database.PopularMoviesDao;
import com.igpl.moviesapp.Models.CreditsResponse;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.Models.MovieResponse;
import com.igpl.moviesapp.Models.ReviewResponse;
import com.igpl.moviesapp.Network.MoviesApiServices;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private static final String TAG = "Repository";

    MoviesApiServices moviesApiServices;
    PopularMoviesDao popularMoviesDao;

    @Inject
    public Repository(MoviesApiServices moviesApiServices, PopularMoviesDao popularMoviesDao) {
        this.moviesApiServices = moviesApiServices;
        this.popularMoviesDao = popularMoviesDao;
    }

    public Observable<MovieResponse> getPopularMoviesFromApi(HashMap<String, String> popularMoviesMap){
        return moviesApiServices.getPopularMovies(popularMoviesMap);
    }

    public Observable<Movie> getMovieDetails(int movieId, HashMap<String, String> moviesMap){
        return moviesApiServices.getMovieDetails(movieId, moviesMap);
    }

    public Observable<CreditsResponse> getCreditsFromApi(int movieId, HashMap<String, String> movieMap){
        return moviesApiServices.getCredits(movieId, movieMap);
    }

    public Observable<ReviewResponse> getReviewsFromApi(int movieId, HashMap<String, String> movieMap){
        return moviesApiServices.getReviews(movieId, movieMap);
    }

    public void insertMovieIntoDb(PopularMovie movie){
        popularMoviesDao.insert(movie);
    }

    public void deleteMovieFrom(int movieId){
        popularMoviesDao.deleteMovie(movieId);
    }

    public LiveData<List<PopularMovie>> getAllMoviesFromDb(){
        return popularMoviesDao.getAllPopularMovies();
    }

    public void deleteAllMoviesFromDb(){
        popularMoviesDao.delete();
    }

}
