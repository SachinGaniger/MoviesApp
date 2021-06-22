package com.igpl.moviesapp.Models;

public class Review {

    String id;

    String author;

    String content;

    String created_at;

    public Review() {
    }

    public Review(String id, String author, String content, String created_at) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }
}
