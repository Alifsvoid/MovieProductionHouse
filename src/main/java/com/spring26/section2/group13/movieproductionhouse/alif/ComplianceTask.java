package com.spring26.section2.group13.movieproductionhouse.alif;

import java.io.Serializable;

public class ComplianceTask implements Serializable {
    private String taskID;
    private String movieTitle;
    private String taskType;
    private String status;

    public ComplianceTask(String taskID, String movieTitle, String taskType, String status) {
        this.taskID = taskID;
        this.movieTitle = movieTitle;
        this.taskType = taskType;
        this.status = status;
    }

    public String getTaskID() { return taskID; }
    public void setTaskID(String taskID) { this.taskID = taskID; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ComplianceTask{" +
                "taskID='" + taskID + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", taskType='" + taskType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}