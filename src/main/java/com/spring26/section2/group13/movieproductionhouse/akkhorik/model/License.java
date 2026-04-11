package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


import java.time.LocalDate;

public class License {
    private String licenseId;
    private String filmId;
    private String exhibitorName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String region;
    private int numberOfScreens;
    private double revenueSharePercent;
    private String status; // Pending, Approved, Active, Expired
    private String kdmKey;

    public License(String licenseId, String filmId, String exhibitorName, LocalDate startDate,
                   LocalDate endDate, String region, int numberOfScreens, double revenueSharePercent) {
        this.licenseId = licenseId;
        this.filmId = filmId;
        this.exhibitorName = exhibitorName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.region = region;
        this.numberOfScreens = numberOfScreens;
        this.revenueSharePercent = revenueSharePercent;
        this.status = "Pending";
    }

    public String getLicenseId() { return licenseId; }
    public String getFilmId() { return filmId; }
    public String getExhibitorName() { return exhibitorName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getRegion() { return region; }
    public int getNumberOfScreens() { return numberOfScreens; }
    public double getRevenueSharePercent() { return revenueSharePercent; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getKdmKey() { return kdmKey; }
    public void setKdmKey(String kdmKey) { this.kdmKey = kdmKey; }

    @Override
    public String toString() {
        return String.format("%s | Film: %s | Exhibitor: %s | %s - %s | %s",
                licenseId, filmId, exhibitorName, startDate, endDate, status);
    }
}