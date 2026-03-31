package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class Complaint implements Serializable {
    private String complaintID;
    private String category;
    private String description;
    private String status;

    public Complaint(String complaintID, String category, String description, String status) {
        this.complaintID = complaintID;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public String getComplaintID() { return complaintID; }
    public void setComplaintID(String complaintID) { this.complaintID = complaintID; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintID='" + complaintID + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}