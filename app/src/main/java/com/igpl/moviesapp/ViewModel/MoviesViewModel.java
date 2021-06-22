package com.igpl.moviesapp.ViewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.igpl.moviesapp.Database.PopularMovie;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.Models.MovieResponse;
import com.igpl.moviesapp.Repository.Repository;
import com.igpl.moviesapp.View.MainActivity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class MoviesViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    Repository repository;

    MutableLiveData<List<Movie>> popularList = new MutableLiveData<>();
    MutableLiveData<String> toastMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    boolean action = false;
    public final CompositeDisposable disposable = new CompositeDisposable();

    @ViewModelInject
    public MoviesViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Movie>> getPopularMoviesList(){
        return popularList;
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public LiveData<Boolean> getProgressStatus(){
        return showProgress;
    }

    public LiveData<List<PopularMovie>> getFavouriteMovies(){

        return repository.getAllMoviesFromDb();

    }

    public void getPopularMoviesFromRepo(HashMap<String, String> map){

        showProgress.postValue(true);

        disposable.add(repository.getPopularMoviesFromApi(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(movieResponse -> {
                    popularList.setValue(movieResponse.getResults());
                    showProgress.postValue(false);
                }, error -> {
                    toastMessage.setValue(error.getMessage());
                    showProgress.postValue(false);
                })
        );

    }

    public void insertMovies(PopularMovie movie){

        repository.insertMovieIntoDb(movie);

    }

    public void deleteMovie(int id){

        repository.deleteMovieFrom(id);

    }

}
