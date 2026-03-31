package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;
import java.time.LocalDate;

public class Subscriber implements Serializable {
    private String name;
    private String email;
    private String password;
    private LocalDate dob;
    private String preferredGenre;
    private double walletBalance;
    private String subscriptionPlan;

    public Subscriber(String name, String email, String password, LocalDate dob, String preferredGenre, double walletBalance, String subscriptionPlan) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.preferredGenre = preferredGenre;
        this.walletBalance = walletBalance;
        this.subscriptionPlan = subscriptionPlan;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getPreferredGenre() { return preferredGenre; }
    public void setPreferredGenre(String preferredGenre) { this.preferredGenre = preferredGenre; }

    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }

    public String getSubscriptionPlan() { return subscriptionPlan; }
    public void setSubscriptionPlan(String subscriptionPlan) { this.subscriptionPlan = subscriptionPlan; }

    @Override
    public String toString() {
        return "Subscriber{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", preferredGenre='" + preferredGenre + '\'' +
                ", walletBalance=" + walletBalance +
                ", subscriptionPlan='" + subscriptionPlan + '\'' +
                '}';
    }
}