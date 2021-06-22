package com.igpl.moviesapp.View.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.igpl.moviesapp.Models.CreditsResponse;
import com.igpl.moviesapp.Models.Genre;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.Models.ReviewResponse;
import com.igpl.moviesapp.R;
import com.igpl.moviesapp.Utils.CommonUtil;
import com.igpl.moviesapp.Utils.Constants;
import com.igpl.moviesapp.View.Adapters.CommonCastAdapter;
import com.igpl.moviesapp.View.Adapters.CommonGenreAdapter;
import com.igpl.moviesapp.View.Adapters.CommonReviewsAdapter;
import com.igpl.moviesapp.ViewModel.MovieDetailsViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieDetails extends Fragment {

    TextView tv_movieTitle, toolbarTitle, tv_rating, tv_language, tv_releaseDate, tv_moviePlotText;
    ImageView iv_movieBanner, iv_moviePoster;
    MovieDetailsViewModel movieDetailsViewModel;
    RecyclerView rv_genre, rv_cast, rv_reviews;
    CommonGenreAdapter genreAdapter;
    ConstraintLayout cl_main;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDetailsViewModel = new ViewModelProvider(requireActivity()).get(MovieDetailsViewModel.class);

        int movieId = MovieDetailsArgs.fromBundle(getArguments()).getMovieId();

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        tv_movieTitle = view.findViewById(R.id.tv_movieTitle);
        cl_main = view.findViewById(R.id.cl_main);
        iv_movieBanner = view.findViewById(R.id.iv_movieBanner);
        iv_moviePoster = view.findViewById(R.id.iv_moviePoster);
        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        tv_rating = view.findViewById(R.id.tv_rating);
        tv_language = view.findViewById(R.id.tv_language);
        tv_releaseDate = view.findViewById(R.id.tv_releaseDate);
        tv_moviePlotText = view.findViewById(R.id.tv_moviePlotText);
        rv_genre = view.findViewById(R.id.rv_genre);
        rv_cast = view.findViewById(R.id.rv_cast);
        rv_reviews = view.findViewById(R.id.rv_reviews);

        movieDetailsViewModel.getMovieDetailFromRepo(movieId, map);
        movieDetailsViewModel.getCreditsFromRepo(movieId, map);
        movieDetailsViewModel.getReviewsFromRepo(movieId, map);

        observeData();

    }

    private void observeData() {

        movieDetailsViewModel.getToastMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });

        movieDetailsViewModel.getProgressStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (getActivity() != null) {
                    if (aBoolean != null && aBoolean) {
                        CommonUtil.showProgressBar(requireActivity());
                        cl_main.setVisibility(View.GONE);
                    } else {
                        CommonUtil.dismissProgressDialog();
                        cl_main.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        movieDetailsViewModel.getMovieDetail().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {

                tv_movieTitle.setText(movie.getTitle());
                toolbarTitle.setText(movie.getTitle());
                tv_rating.setText(String.valueOf(movie.getVote_average()));
                tv_language.setText(String.valueOf(movie.getOriginal_language()));
                tv_releaseDate.setText(String.valueOf(movie.getRelease_date()));
                tv_moviePlotText.setText(String.valueOf(movie.getOverview()));

                Glide.with(getActivity()).load(Constants.ImageBaseURL + movie.getPoster_path())
                        .into(iv_moviePoster);

                Glide.with(getActivity()).load(Constants.ImageBaseURLw780 + movie.getBackdrop_path())
                        .into(iv_movieBanner);

                setGenre(movie.getGenres());

            }
        });

        movieDetailsViewModel.getCredits().observe(getViewLifecycleOwner(), new Observer<CreditsResponse>() {
            @Override
            public void onChanged(CreditsResponse creditsResponse) {

                setCastList(creditsResponse);

            }
        });

        movieDetailsViewModel.getReviews().observe(getViewLifecycleOwner(), new Observer<ReviewResponse>() {
            @Override
            public void onChanged(ReviewResponse reviewResponse) {

                setReviews(reviewResponse);

            }
        });

    }

    private void setReviews(ReviewResponse reviewResponse) {

        rv_reviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommonReviewsAdapter commonReviewsAdapter = new CommonReviewsAdapter(reviewResponse.getReviews());
        rv_reviews.setAdapter(commonReviewsAdapter);

    }

    private void setCastList(CreditsResponse creditsResponse) {

        rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        CommonCastAdapter castAdapter = new CommonCastAdapter(getActivity(), creditsResponse.getCastList());
        rv_cast.setAdapter(castAdapter);

    }


    private void setGenre(ArrayList<Genre> genres) {

        rv_genre.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        genreAdapter = new CommonGenreAdapter(genres);
        rv_genre.setAdapter(genreAdapter);

    }

}