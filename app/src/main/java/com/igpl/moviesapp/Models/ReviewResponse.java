package com.igpl.moviesapp.Models;

import java.util.List;

public class ReviewResponse {

    int id;

    private List<Review> results;

    public ReviewResponse() {
    }

    public ReviewResponse(int id, List<Review> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public List<Review> getReviews() {
        return results;
    }
}
