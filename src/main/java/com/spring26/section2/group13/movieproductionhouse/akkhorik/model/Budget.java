package com.spring26.section2.group13.movieproductionhouse.akkhorik.model;



import java.util.HashMap;
import java.util.Map;

public class Budget {
    private String budgetId;
    private String projectId;
    private Map<String, Double> lineItems; // category -> amount
    private double totalBudget;
    private double spent;
    private String status; // Draft, Pending, Approved

    public Budget(String budgetId, String projectId) {
        this.budgetId = budgetId;
        this.projectId = projectId;
        this.lineItems = new HashMap<>();
        this.totalBudget = 0;
        this.spent = 0;
        this.status = "Draft";
    }

    public String getBudgetId() { return budgetId; }
    public String getProjectId() { return projectId; }
    public Map<String, Double> getLineItems() { return lineItems; }
    public double getTotalBudget() { return totalBudget; }
    public void setTotalBudget(double totalBudget) { this.totalBudget = totalBudget; }
    public double getSpent() { return spent; }
    public void setSpent(double spent) { this.spent = spent; }
    public double getRemaining() { return totalBudget - spent; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void addLineItem(String category, double amount) {
        lineItems.put(category, amount);
        totalBudget += amount;
    }
}
