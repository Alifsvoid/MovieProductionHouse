package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class ComplianceGuidelinesViewController {

    @FXML private TextArea guidelinesTA;

    @FXML
    public void initialize() {
        // Load the current guidelines into the text area when the screen opens
        String currentGuidelines = "MOVIE PRODUCTION HOUSE - COMPLIANCE GUIDELINES\n\n" +
                "1. AGE RATINGS:\n" +
                "   - G: General Audiences. No strong language or violence.\n" +
                "   - PG/PG-13: Parental Guidance. Mild violence or language permitted.\n" +
                "   - R: Restricted. Requires explicit content warnings.\n\n" +
                "2. SUSPENSION POLICY:\n" +
                "   - Any movie receiving more than 50 user complaints within a week must be temporarily suspended and flagged for review.\n\n" +
                "3. PROHIBITED CONTENT:\n" +
                "   - Unsimulated harmful acts and hate speech are strictly prohibited across all ratings.";

        guidelinesTA.setText(currentGuidelines);
    }

    @FXML
    public void saveGuidelines(ActionEvent actionEvent) {
        String updatedText = guidelinesTA.getText();

        if (updatedText == null || updatedText.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Guidelines cannot be completely empty.");
            alert.showAndWait();
            return;
        }

        // In a real app, this would save the updated String to a 'guidelines.txt' or '.bin' file
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guidelines Updated");
        alert.setHeaderText(null);
        alert.setContentText("The new compliance guidelines have been successfully saved and published to the platform.");
        alert.showAndWait();
    }
}
