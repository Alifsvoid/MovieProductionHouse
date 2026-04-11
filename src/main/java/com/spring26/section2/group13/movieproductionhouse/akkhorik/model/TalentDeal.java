package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;


public class TalentDeal {
    private String dealId;
    private String projectId;
    private String talentName;
    private String role; // Actor, Director, Writer
    private double baseSalary;
    private double backendPoints; // percentage
    private String status; // Pending, Approved, Rejected

    public TalentDeal(String dealId, String projectId, String talentName, String role,
                      double baseSalary, double backendPoints) {
        this.dealId = dealId;
        this.projectId = projectId;
        this.talentName = talentName;
        this.role = role;
        this.baseSalary = baseSalary;
        this.backendPoints = backendPoints;
        this.status = "Pending";
    }

    public String getDealId() { return dealId; }
    public String getProjectId() { return projectId; }
    public String getTalentName() { return talentName; }
    public String getRole() { return role; }
    public double getBaseSalary() { return baseSalary; }
    public double getBackendPoints() { return backendPoints; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double calculateBreakEvenImpact() {
        return baseSalary * (1 + backendPoints / 100);
    }

    @Override
    public String toString() {
        return String.format("%s | %s (%s) | Salary: $%.2f | Points: %.1f%% | %s",
                dealId, talentName, role, baseSalary, backendPoints, status);
    }
}