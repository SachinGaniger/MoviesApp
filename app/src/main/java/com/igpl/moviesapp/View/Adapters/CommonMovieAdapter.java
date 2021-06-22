package com.igpl.moviesapp.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.igpl.moviesapp.Database.PopularMovie;
import com.igpl.moviesapp.Models.Movie;
import com.igpl.moviesapp.R;
import com.igpl.moviesapp.Utils.CommonRecyclerviewClickListener;
import com.igpl.moviesapp.Utils.Constants;
import com.igpl.moviesapp.ViewModel.MoviesViewModel;

import java.util.List;

public class CommonMovieAdapter extends RecyclerView.Adapter<CommonMovieAdapter.ViewHolder> {

    List<Movie> moviesList;
    Context context;
    MoviesViewModel moviesViewModel;
    CommonRecyclerviewClickListener commonRecyclerviewClickListener;

    public CommonMovieAdapter(List<Movie> moviesList, Context context, MoviesViewModel moviesViewModel, CommonRecyclerviewClickListener commonRecyclerviewClickListener) {

        this.moviesList = moviesList;
        this.context = context;
        this.moviesViewModel = moviesViewModel;
        this.commonRecyclerviewClickListener = commonRecyclerviewClickListener;

    }

    @NonNull
    @Override
    public CommonMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.individual_movie_adapter_layout, parent, false);

        return new CommonMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = moviesList.get(position);

        PopularMovie favMovie = new PopularMovie(movie.getId(), movie.getPoster_path(), movie.getTitle(), movie.getBackdrop_path());

        holder.tv_movieTitle.setText(movie.getTitle());

        Glide.with(context).load(Constants.ImageBaseURLw780 + movie.getPoster_path())
                .into(holder.iv_movieImage);

        holder.cb_fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                holder.fav = isChecked;
                favMovie.setFav(isChecked);
                if (isChecked){

                    moviesViewModel.insertMovies(favMovie);

                } else {

                    moviesViewModel.deleteMovie(movie.getId());

                }

                notifyDataSetChanged();

            }
        });

        holder.commonRecyclerviewClickListener = commonRecyclerviewClickListener;

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_movieImage;
        TextView tv_movieTitle;
        CheckBox cb_fav;
        boolean fav;
        CommonRecyclerviewClickListener commonRecyclerviewClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_movieImage = itemView.findViewById(R.id.iv_movieImage);
            cb_fav = itemView.findViewById(R.id.cb_fav);
            tv_movieTitle = itemView.findViewById(R.id.tv_movieTitle);
//            this.commonRecyclerviewClickListener = commonRecyclerviewClickListener;

            itemView.setOnClickListener(ViewHolder.this);

        }


        @Override
        public void onClick(View v) {
            commonRecyclerviewClickListener.OnItemClick(getAdapterPosition());
        }
    }

}
