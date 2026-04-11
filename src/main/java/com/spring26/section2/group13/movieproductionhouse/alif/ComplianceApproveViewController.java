package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ComplianceApproveViewController {

    @FXML private ComboBox<String> pendingMoviesCB;
    @FXML private TextArea approvalNotesTA;

    @FXML
    public void initialize() {
        // Load some dummy movies that need approval
        pendingMoviesCB.getItems().addAll("The Matrix 4", "Toy Story 5", "Avatar 3");
    }

    @FXML
    public void handleApproveButton(ActionEvent actionEvent) {
        String selectedMovie = pendingMoviesCB.getValue();
        String notes = approvalNotesTA.getText();

        if (selectedMovie == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a movie to approve.");
            return;
        }

        // In a fully connected app, this would update the movie's status in movies.bin
        showAlert(Alert.AlertType.INFORMATION, "Movie Approved",
                selectedMovie + " has been officially approved for release!\nNotes: " +
                        (notes.isEmpty() ? "None" : notes));

        // Remove from the pending list and clear notes
        pendingMoviesCB.getItems().remove(selectedMovie);
        pendingMoviesCB.setValue(null);
        approvalNotesTA.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}