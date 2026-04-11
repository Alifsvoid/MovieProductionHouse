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

        // Bengali Movies
        movieList.add(new Movie("Toofan", "Action", "Bengali", 145, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Hawa", "Drama", "Bengali", 131, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Pather Panchali", "Drama", "Bengali", 125, "G", "Approved", "None"));

        // Hindi Movies
        movieList.add(new Movie("3 Idiots", "Comedy", "Hindi", 170, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Sholay", "Action", "Hindi", 204, "PG-13", "Approved", "None"));
        movieList.add(new Movie("PK", "Comedy", "Hindi", 152, "PG-13", "Approved", "None"));

        // Spanish Movies
        movieList.add(new Movie("The Platform", "Sci-Fi", "Spanish", 94, "R", "Approved", "None"));
        movieList.add(new Movie("Coco", "Family", "Spanish", 105, "PG", "Approved", "None"));

        // More English Movies
        movieList.add(new Movie("The Conjuring", "Horror", "English", 112, "R", "Approved", "None"));
        movieList.add(new Movie("A Quiet Place", "Horror", "English", 90, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Spider-Man: Across the Spider-Verse", "Action", "English", 140, "PG", "Approved", "None"));
        movieList.add(new Movie("Superbad", "Comedy", "English", 113, "R", "Approved", "None"));
        movieList.add(new Movie("Interstellar", "Sci-Fi", "English", 169, "PG-13", "Approved", "None"));
        movieList.add(new Movie("Paddington 2", "Family", "English", 104, "PG", "Approved", "None"));

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