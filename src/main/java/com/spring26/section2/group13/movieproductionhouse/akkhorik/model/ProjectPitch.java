package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


public class ProjectPitch {
    private String id;
    private String title;
    private String producer;
    private String synopsis;
    private double estimatedBudget;
    private String genre;
    private String status; // Pending, Reviewed, Greenlit, Rejected
    private int rating;
    private String comments;
    private double approvedBudget;

    public ProjectPitch(String id, String title, String producer, String synopsis,
                        double estimatedBudget, String genre) {
        this.id = id;
        this.title = title;
        this.producer = producer;
        this.synopsis = synopsis;
        this.estimatedBudget = estimatedBudget;
        this.genre = genre;
        this.status = "Pending";
        this.rating = 0;
        this.comments = "";
        this.approvedBudget = 0;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getProducer() { return producer; }
    public String getSynopsis() { return synopsis; }
    public double getEstimatedBudget() { return estimatedBudget; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public double getApprovedBudget() { return approvedBudget; }
    public void setApprovedBudget(double approvedBudget) { this.approvedBudget = approvedBudget; }

    @Override
    public String toString() {
        return String.format("%s | %s by %s | Budget Est: $%.2f | Status: %s",
                id, title, producer, estimatedBudget, status);
    }
}
