package com.igpl.moviesapp.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igpl.moviesapp.R;

public class WatchListFragment extends Fragment {

    TextView toolbarTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View watchListView = inflater.inflate(R.layout.fragment_watch_list, container, false);

        toolbarTitle = watchListView.findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("TV Shows");

        return watchListView;
    }
}