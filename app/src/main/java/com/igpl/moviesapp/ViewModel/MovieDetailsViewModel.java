package com.igpl.moviesapp.ViewModel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.igpl.moviesapp.Models.CreditsResponse;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.Models.ReviewResponse;
import com.igpl.moviesapp.Repository.Repository;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends ViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    private Repository repository;
    public final CompositeDisposable disposable = new CompositeDisposable();

    @ViewModelInject
    public MovieDetailsViewModel(Repository repository) {
        this.repository = repository;
    }

    private MutableLiveData<Movie> movieDetails = new MutableLiveData<>();
    MutableLiveData<String> toastMessage = new MutableLiveData<>();
    MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    private MutableLiveData<CreditsResponse> credits = new MutableLiveData<>();
    private MutableLiveData<ReviewResponse> reviews = new MutableLiveData<>();

    boolean action = false;

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public LiveData<Boolean> getProgressStatus(){
        return showProgress;
    }

    public LiveData<Movie> getMovieDetail(){
        return movieDetails;
    }

    public LiveData<CreditsResponse> getCredits(){
        return credits;
    }

    public LiveData<ReviewResponse> getReviews(){
        return reviews;
    }

    public void getMovieDetailFromRepo(int movieId, HashMap<String, String> movieMap){

        showProgress.postValue(true);

        disposable.add(repository.getMovieDetails(movieId, movieMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    movieDetails.setValue(movie);
                    showProgress.postValue(false);
                }, error -> {
                    toastMessage.setValue(error.getMessage());
                    showProgress.postValue(false);
                })
        );

    }

    public void getCreditsFromRepo(int movieId,HashMap<String, String> map){

        disposable.add(repository.getCreditsFromApi(movieId, map)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(creditsResponse -> {
            credits.setValue(creditsResponse);
        }, error-> {
                    toastMessage.setValue(error.getMessage());
                })
        );

    }

    public void getReviewsFromRepo(int movieId, HashMap<String, String> map){

        disposable.add(repository.getReviewsFromApi(movieId, map)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(reviewResponse -> {
                reviews.setValue(reviewResponse);
            }, error -> {
                toastMessage.setValue(error.getMessage());
            })
        );

    }

}
