package com.igpl.moviesapp.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igpl.moviesapp.Models.Review;
import com.igpl.moviesapp.R;

import java.util.List;

public class CommonReviewsAdapter extends RecyclerView.Adapter<CommonReviewsAdapter.ViewHolder> {

    List<Review> reviews;

    public CommonReviewsAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_review_container, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Review review = reviews.get(position);

        holder.tv_reviewAuthor.setText(review.getAuthor());
        holder.tv_review.setText(review.getContent());
        holder.tv_reviewDate.setText(review.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_reviewAuthor, tv_review, tv_reviewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_reviewAuthor = itemView.findViewById(R.id.tv_reviewAuthor);
            tv_review = itemView.findViewById(R.id.tv_review);
            tv_reviewDate = itemView.findViewById(R.id.tv_reviewDate);

        }
    }
}
