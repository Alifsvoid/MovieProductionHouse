package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


import java.time.LocalDateTime;

public class Screening {
    private String screeningId;
    private String licenseId;
    private String filmId;
    private LocalDateTime showtime;
    private int screenNumber;
    private int capacity;
    private int ticketsSold;
    private double ticketPrice;

    public Screening(String screeningId, String licenseId, String filmId, LocalDateTime showtime,
                     int screenNumber, int capacity, double ticketPrice) {
        this.screeningId = screeningId;
        this.licenseId = licenseId;
        this.filmId = filmId;
        this.showtime = showtime;
        this.screenNumber = screenNumber;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = 0;
    }

    public String getScreeningId() { return screeningId; }
    public String getLicenseId() { return licenseId; }
    public String getFilmId() { return filmId; }
    public LocalDateTime getShowtime() { return showtime; }
    public int getScreenNumber() { return screenNumber; }
    public int getCapacity() { return capacity; }
    public int getTicketsSold() { return ticketsSold; }
    public void setTicketsSold(int ticketsSold) { this.ticketsSold = ticketsSold; }
    public double getTicketPrice() { return ticketPrice; }
    public double getRevenue() { return ticketsSold * ticketPrice; }
    public double getOccupancyRate() { return (double) ticketsSold / capacity * 100; }

    @Override
    public String toString() {
        return String.format("%s | Film: %s | %s | Screen %d | Sold: %d/%d",
                screeningId, filmId, showtime, screenNumber, ticketsSold, capacity);
    }
}