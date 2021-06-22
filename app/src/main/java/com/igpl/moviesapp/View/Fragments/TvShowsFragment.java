package com.igpl.moviesapp.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igpl.moviesapp.R;

public class TvShowsFragment extends Fragment {

    TextView toolbarTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tvShowFragment =  inflater.inflate(R.layout.fragment_tv_shows, container, false);

        toolbarTitle = tvShowFragment.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("TV Shows");

        return tvShowFragment;
    }
}