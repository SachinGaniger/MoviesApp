package com.igpl.moviesapp.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.igpl.moviesapp.Database.PopularMovie;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.R;
import com.igpl.moviesapp.Utils.CommonRecyclerviewClickListener;
import com.igpl.moviesapp.Utils.CommonUtil;
import com.igpl.moviesapp.Utils.Constants;
import com.igpl.moviesapp.View.Adapters.CommonMovieAdapter;
import com.igpl.moviesapp.ViewModel.MoviesViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.igpl.moviesapp.View.Fragments.MoviesFragmentDirections.actionMoviesFragmentToMovieDetails;

public class MoviesFragment extends Fragment implements CommonRecyclerviewClickListener {

    private static final String TAG = "MoviesFragment";
    RecyclerView rv_movies;
    CommonMovieAdapter movieAdapter;
    MoviesViewModel moviesViewModel;
    List<Movie> popularMovieList = new ArrayList<>();
    List<PopularMovie> favouriteMovieList = new ArrayList<>();
    TextView toolbarTitle, tv_popularMovies;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesViewModel = new ViewModelProvider(requireActivity()).get(MoviesViewModel.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("api_key", Constants.API_KEY);
        map.put("page", "1");

        moviesViewModel.getPopularMoviesFromRepo(map);

        rv_movies = view.findViewById(R.id.rv_movies);

        toolbarTitle = view.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Movies");

        navController = Navigation.findNavController(requireActivity(), R.id.navHost);

        observeData();

        Log.i(TAG, "popularMovieSize: " + popularMovieList.size());

    }

    private void setRecyclerView() {

        GridLayoutManager layoutManager = new GridLayoutManager(requireActivity(), 2);

        rv_movies.setLayoutManager(layoutManager);
        movieAdapter = new CommonMovieAdapter(popularMovieList, requireActivity(), moviesViewModel, this);
        rv_movies.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

    private void observeData() {

        ////while observing always use viewLifeCycleOwner and not view Context
        moviesViewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                popularMovieList = movies;
                Log.i(TAG, "popularMovieSize: " + popularMovieList.size());
                Log.i(TAG, "popularMovieTitle: " + popularMovieList.get(0).getTitle());
                setRecyclerView();
//                movieAdapter.notifyDataSetChanged();
            }
        });

        moviesViewModel.getToastMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });

        moviesViewModel.getProgressStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (getActivity() != null) {
                    if (aBoolean != null && aBoolean) {
                        CommonUtil.showProgressBar(requireActivity());
                        rv_movies.setVisibility(View.GONE);
                    } else {
                        CommonUtil.dismissProgressDialog();
                        rv_movies.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        moviesViewModel.getFavouriteMovies().observe(getViewLifecycleOwner(), new Observer<List<PopularMovie>>() {
            @Override
            public void onChanged(List<PopularMovie> popularMovies) {
                favouriteMovieList = popularMovies;
                Log.i(TAG, "onChanged: FavouriteMovieList "+ favouriteMovieList.size());
                if (favouriteMovieList.size() > 0) {
                    Log.i(TAG, "onChanged: FavouriteMovie " + favouriteMovieList.get(0).getTitle());
                }
            }
        });

    }

    @Override
    public void OnItemClick(int position) {

        MoviesFragmentDirections.ActionMoviesFragmentToMovieDetails action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetails(popularMovieList.get(position).getId());
        navController.navigate(action);

    }
}