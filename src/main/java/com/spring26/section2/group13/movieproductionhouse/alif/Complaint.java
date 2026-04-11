package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class Complaint implements Serializable {
    private String complaintId; // Changed to match controller
    private String category;
    private String description;
    private String status;

    public Complaint(String complaintId, String category, String description, String status) {
        this.complaintId = complaintId;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public String getComplaintId() { return complaintId; } // Changed to match controller
    public void setComplaintId(String complaintId) { this.complaintId = complaintId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId='" + complaintId + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}