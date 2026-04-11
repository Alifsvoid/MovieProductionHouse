package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class ComplianceFlag implements Serializable {
    private String movieTitle;
    private String timestamp;
    private String category;
    private String description;

    public ComplianceFlag(String movieTitle, String timestamp, String category, String description) {
        this.movieTitle = movieTitle;
        this.timestamp = timestamp;
        this.category = category;
        this.description = description;
    }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}