package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.ArrayList;

public class AudienceReviewViewController {

    @FXML private ComboBox<Integer> starRatingCB;
    @FXML private TextArea reviewTextTA;

    // Temporary placeholder for the movie being reviewed
    private String currentMovieTitle = "The Batman";

    @FXML
    public void initialize() {
        // CRA Workflow Event 2: Load ratings 1-5
        starRatingCB.getItems().addAll(1, 2, 3, 4, 5);
    }

    @FXML
    public void submitReview(ActionEvent actionEvent) {
        Integer rating = starRatingCB.getValue();
        String reviewText = reviewTextTA.getText();

        // CRA Workflow Event 3: Validation
        if (rating == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a star rating.");
            return;
        }

        if (reviewText == null || reviewText.length() < 10) {
            showAlert(Alert.AlertType.WARNING, "Review Too Short", "Please write at least 10 characters for your review.");
            return;
        }

        // CRA Workflow Event 4: Create Review object
        Review newReview = new Review(currentMovieTitle, rating, reviewText);

        // Save to file
        File file = new File("data/reviews.dat");
        ArrayList<Review> reviews = BinaryFileHelper.readAllObjects(file);
        if (reviews == null) reviews = new ArrayList<>();

        reviews.add(newReview);
        BinaryFileHelper.writeAllObjects(file, reviews);

        // CRA Workflow Event 5: Refresh and display
        showAlert(Alert.AlertType.INFORMATION, "Review Submitted", "Thank you! Your " + rating + "-star review for " + currentMovieTitle + " has been posted.");

        starRatingCB.setValue(null);
        reviewTextTA.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}