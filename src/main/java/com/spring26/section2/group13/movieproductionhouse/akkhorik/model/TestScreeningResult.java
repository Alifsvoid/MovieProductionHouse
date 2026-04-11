package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


import java.time.LocalDate;

public class TestScreeningResult {
    private String resultId;
    private String projectId;
    private LocalDate screeningDate;
    private double overallRating; // 1-10
    private int recommendPercent;
    private String topCriticisms;
    private String executiveDirective;

    public TestScreeningResult(String resultId, String projectId, LocalDate screeningDate,
                               double overallRating, int recommendPercent, String topCriticisms) {
        this.resultId = resultId;
        this.projectId = projectId;
        this.screeningDate = screeningDate;
        this.overallRating = overallRating;
        this.recommendPercent = recommendPercent;
        this.topCriticisms = topCriticisms;
        this.executiveDirective = "";
    }

    public String getResultId() { return resultId; }
    public String getProjectId() { return projectId; }
    public LocalDate getScreeningDate() { return screeningDate; }
    public double getOverallRating() { return overallRating; }
    public int getRecommendPercent() { return recommendPercent; }
    public String getTopCriticisms() { return topCriticisms; }
    public String getExecutiveDirective() { return executiveDirective; }
    public void setExecutiveDirective(String executiveDirective) { this.executiveDirective = executiveDirective; }
}