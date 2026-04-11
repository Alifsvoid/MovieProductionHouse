package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class ComplianceOfficer implements Serializable {

    private String officerId;
    private String name;
    private String email;
    private String password;
    private String clearanceLevel;

    public ComplianceOfficer(String officerId, String name, String email, String password, String clearanceLevel) {
        this.officerId = officerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.clearanceLevel = clearanceLevel;
    }

    // --- Getters and Setters ---

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(String clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    @Override
    public String toString() {
        return "ComplianceOfficer{" +
                "officerId='" + officerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", clearanceLevel='" + clearanceLevel + '\'' +
                '}';
    }
}