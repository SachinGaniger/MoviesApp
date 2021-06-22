package com.igpl.moviesapp.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igpl.moviesapp.Models.Genre;
import com.igpl.moviesapp.R;

import java.util.List;

public class CommonGenreAdapter extends RecyclerView.Adapter<CommonGenreAdapter.ViewHolder> {

    List<Genre> genreList;

    public CommonGenreAdapter(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_genre_container, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Genre genre = genreList.get(position);

        holder.tv_genre.setText(genre.getName());

    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_genre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_genre = itemView.findViewById(R.id.tv_genre);
        }
    }
}
