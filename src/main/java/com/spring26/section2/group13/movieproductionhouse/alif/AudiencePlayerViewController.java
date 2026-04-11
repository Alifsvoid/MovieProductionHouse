package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;

public class AudiencePlayerViewController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    public void initialize() {
        // CRA Workflow Event: Display the movie details.
        // (In a fully connected app, this title would be passed over from the Dashboard table selection)
        movieTitleLabel.setText("The Batman (2022) - PG-13");
    }

    @FXML
    public void playMovie(ActionEvent actionEvent) {
        // CRA Workflow Event 4: Simulate playback
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Media Player");
        alert.setHeaderText("Now Playing...");
        alert.setContentText("The movie is now playing in fullscreen. Enjoy the show!");
        alert.showAndWait();
    }

    @FXML
    public void goToReview(ActionEvent actionEvent) {
        // CRA Workflow Event 5: Navigate to the review screen
        try {
            SceneSwitcher.sceneSwitch(actionEvent, "alif/audience-review-view.fxml", "Rate & Review Movie");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Could not load Review Screen");
            alert.setContentText("Please ensure 'audience-review-view.fxml' exists in the alif folder.");
            alert.showAndWait();
        }
    }
}
