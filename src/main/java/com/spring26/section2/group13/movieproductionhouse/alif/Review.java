package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class Review implements Serializable {
    private String movieTitle;
    private int rating;
    private String reviewText;

    // This is the constructor the controller is looking for!
    public Review(String movieTitle, int rating, String reviewText) {
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    // Getters and Setters
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}