package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private String language;
    private int duration;
    private String rating;
    private String status;
    private String complianceFlags;

    public Movie(String title, String genre, String language, int duration, String rating, String status, String complianceFlags) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.rating = rating;
        this.status = status;
        this.complianceFlags = complianceFlags;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComplianceFlags() { return complianceFlags; }
    public void setComplianceFlags(String complianceFlags) { this.complianceFlags = complianceFlags; }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration +
                ", rating='" + rating + '\'' +
                ", status='" + status + '\'' +
                ", complianceFlags='" + complianceFlags + '\'' +
                '}';
    }
}