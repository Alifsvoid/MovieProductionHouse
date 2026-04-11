package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ComplianceSuspendViewController {

    @FXML private ComboBox<String> activeMoviesCB;
    @FXML private TextArea suspensionReasonTA;

    @FXML
    public void initialize() {
        // Load movies that are currently active/released
        activeMoviesCB.getItems().addAll("The Batman", "Deadpool 3", "Dune: Part Two");
    }

    @FXML
    public void handleSuspendButton(ActionEvent actionEvent) {
        String selectedMovie = activeMoviesCB.getValue();
        String reason = suspensionReasonTA.getText();

        if (selectedMovie == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a movie to suspend.");
            return;
        }

        if (reason == null || reason.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "You MUST provide a reason for suspending a released movie.");
            return;
        }

        // In a fully connected app, this would update the movie's status to "Suspended" in movies.bin
        showAlert(Alert.AlertType.WARNING, "Movie Suspended",
                "CRITICAL ACTION: " + selectedMovie + " has been suspended and pulled from the platform.\nReason: " + reason);

        // Remove from the active list and clear the form
        activeMoviesCB.getItems().remove(selectedMovie);
        activeMoviesCB.setValue(null);
        suspensionReasonTA.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
