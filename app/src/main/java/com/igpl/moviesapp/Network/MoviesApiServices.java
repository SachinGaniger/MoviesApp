package com.igpl.moviesapp.Network;

import com.igpl.moviesapp.Models.Cast;
import com.igpl.moviesapp.Models.CreditsResponse;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.Models.MovieResponse;
import com.igpl.moviesapp.Models.ReviewResponse;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MoviesApiServices {

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(@QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetails(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/credits")
    Observable<CreditsResponse> getCredits(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewResponse> getReviews(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

}
