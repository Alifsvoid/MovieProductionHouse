package com.spring26.section2.group13.movieproductionhouse;

import com.spring26.section2.group13.movieproductionhouse.alif.Complaint;
import com.spring26.section2.group13.movieproductionhouse.alif.ComplianceTask;
import com.spring26.section2.group13.movieproductionhouse.alif.Movie;
import com.spring26.section2.group13.movieproductionhouse.alif.Subscriber;
import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class DummyDataGenerator {

    public static void main(String[] args) {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        addMovies();
        addSubscribers();
        addComplaints();
        addComplianceTasks();

        System.out.println("Dummy data generated successfully!");
    }

    public static void addMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie("The Batman", "Action", "English", 176, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Dune: Part Two", "Sci-Fi", "English", 166, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Deadpool", "Action", "English", 108, "R", "Pending", "Flagged for Violence"));
        movieList.add(new Movie("Inside Out 2", "Family", "English", 96, "G", "Approved", "None"));

        File movieFile = new File("data/movies.bin");
        BinaryFileHelper.writeAllObjects(movieFile, movieList);
    }

    public static void addSubscribers() {
        ArrayList<Subscriber> subscriberList = new ArrayList<>();

        subscriberList.add(new Subscriber("John Doe", "john@gmail.com", "1234", LocalDate.of(1995, 5, 20), "Action", 50.0, "Premium"));
        subscriberList.add(new Subscriber("Jane Smith", "jane@gmail.com", "abcd", LocalDate.of(2001, 8, 15), "Sci-Fi", 0.0, "Free"));

        File subscriberFile = new File("data/subscribers.bin");
        BinaryFileHelper.writeAllObjects(subscriberFile, subscriberList);
    }

    public static void addComplaints() {
        ArrayList<Complaint> complaintList = new ArrayList<>();

        complaintList.add(new Complaint("C-101", "Playback Issue", "The audio is out of sync on Dune.", "Pending"));
        complaintList.add(new Complaint("C-102", "Billing Issue", "I was charged twice for Premium.", "Reviewed"));

        File complaintFile = new File("data/complaints.bin");
        BinaryFileHelper.writeAllObjects(complaintFile, complaintList);
    }

    public static void addComplianceTasks() {
        ArrayList<ComplianceTask> taskList = new ArrayList<>();

        taskList.add(new ComplianceTask("T-001", "Deadpool", "Assign Age Rating", "Pending"));
        taskList.add(new ComplianceTask("T-002", "Joker 2", "Review Flagged Content", "In Progress"));

        File taskFile = new File("data/complianceTasks.bin");
        BinaryFileHelper.writeAllObjects(taskFile, taskList);
    }
}