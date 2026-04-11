package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


import java.time.LocalDate;

public class BoxOfficeRecord {
    private String recordId;
    private String screeningId;
    private LocalDate date;
    private int ticketsSold;
    private double grossRevenue;

    public BoxOfficeRecord(String recordId, String screeningId, LocalDate date, int ticketsSold, double grossRevenue) {
        this.recordId = recordId;
        this.screeningId = screeningId;
        this.date = date;
        this.ticketsSold = ticketsSold;
        this.grossRevenue = grossRevenue;
    }

    public String getRecordId() { return recordId; }
    public String getScreeningId() { return screeningId; }
    public LocalDate getDate() { return date; }
    public int getTicketsSold() { return ticketsSold; }
    public double getGrossRevenue() { return grossRevenue; }
}