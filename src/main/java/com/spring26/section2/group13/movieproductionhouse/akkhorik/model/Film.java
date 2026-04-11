package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


public class Film {
    private String id;
    private String title;
    private String genre;
    private int duration; // minutes
    private String rating; // G, PG, PG-13, R
    private String status; // Available, Licensed, In Production
    private double licensingFee;
    private String studioSharePercent;

    public Film(String id, String title, String genre, int duration, String rating,
                String status, double licensingFee, String studioSharePercent) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.status = status;
        this.licensingFee = licensingFee;
        this.studioSharePercent = studioSharePercent;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public String getRating() { return rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getLicensingFee() { return licensingFee; }
    public String getStudioSharePercent() { return studioSharePercent; }

    @Override
    public String toString() {
        return String.format("%s - %s (%s, %d min, %s)", id, title, genre, duration, rating);
    }
}