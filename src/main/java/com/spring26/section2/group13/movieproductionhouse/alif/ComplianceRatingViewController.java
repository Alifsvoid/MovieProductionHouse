package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.*;
import java.util.ArrayList;

public class ComplianceRatingViewController {

    @FXML private Label movieDetailsLabel;
    @FXML private ComboBox<String> ageRatingCB;
    @FXML private TextArea justificationTA;

    private Movie movieUnderReview;

    @FXML
    public void initialize() {
        ageRatingCB.getItems().addAll("G", "PG", "PG-13", "R");
        // Removed the hardcoded Matrix 4 line and replaced it with this:
        loadNextUnratedMovie();
    }

    @SuppressWarnings("unchecked")
    private void loadNextUnratedMovie() {
        File f = new File("data/movies.bin");
        if (!f.exists()) {
            movieDetailsLabel.setText("No movies found in database.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ArrayList<Movie> list = (ArrayList<Movie>) ois.readObject();

            for (Movie m : list) {
                // Find the first movie that needs a rating
                if (m.getRating() == null || m.getRating().equals("Unrated") || m.getRating().isEmpty()) {
                    movieUnderReview = m;
                    movieDetailsLabel.setText("Currently Reviewing: " + m.getTitle() + " (" + m.getGenre() + ")");
                    return;
                }
            }

            movieDetailsLabel.setText("All movies have been rated!");
            movieUnderReview = null; // Clear out the object so we don't accidentally save

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    @SuppressWarnings("unchecked")
    public void assignRating(ActionEvent actionEvent) {
        if (movieUnderReview == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No movie available to review.");
            return;
        }

        String selectedRating = ageRatingCB.getValue();
        String justification = justificationTA.getText();

        // Validation
        if (selectedRating == null || justification == null || justification.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "You must select an age rating and provide justification remarks.");
            return;
        }

        // Data Processing (Saving to File)
        File f = new File("data/movies.bin");
        try {
            ArrayList<Movie> list;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                list = (ArrayList<Movie>) ois.readObject();
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTitle().equals(movieUnderReview.getTitle())) {
                    movieUnderReview.setRating(selectedRating);
                    movieUnderReview.setComplianceFlags("Officer Remarks: " + justification);
                    list.set(i, movieUnderReview);
                    break;
                }
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
                oos.writeObject(list);
            }

            showAlert(Alert.AlertType.INFORMATION, "Rating Assigned",
                    "Successfully assigned rating [" + selectedRating + "] to the movie.\nJustification recorded.");

            // Clear the form and load the next movie
            ageRatingCB.setValue(null);
            justificationTA.clear();
            loadNextUnratedMovie();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "System Error", "Could not save the rating.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}