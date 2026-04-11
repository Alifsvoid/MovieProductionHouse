package com.spring26.section2.group13.movieproductionhouse.alif;

import java.time.LocalDate;

public class Audience extends User {
    private LocalDate dob;
    private String preferredGenre;
    private double walletBalance;
    private String subscriptionStatus; // e.g., "Free", "Premium"

    public Audience(String name, String email, String password, LocalDate dob, String preferredGenre, double walletBalance, String subscriptionStatus) {
        // 'super' calls the constructor of the User.java class to handle name, email, and password!
        super(name, email, password);
        this.dob = dob;
        this.preferredGenre = preferredGenre;
        this.walletBalance = walletBalance;
        this.subscriptionStatus = subscriptionStatus;
    }

    // --- Getters ---
    public LocalDate getDob() { return dob; }
    public String getPreferredGenre() { return preferredGenre; }
    public double getWalletBalance() { return walletBalance; }
    public String getSubscriptionStatus() { return subscriptionStatus; }

    // --- Setters (Important for updating wallet and subscription!) ---
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    // Helper method for the wallet top-up
    public void addFunds(double amount) {
        this.walletBalance += amount;
    }
}
