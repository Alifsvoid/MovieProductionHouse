package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;

public class ComplianceFlagViewController {

    @FXML private TextField timestampTF;
    @FXML private ComboBox<String> violationCategoryCB;
    @FXML private TextArea descriptionTA;

    // Placeholder for the movie being reviewed
    private String currentMovieTitle = "Deadpool 3";

    @FXML
    public void initialize() {
        violationCategoryCB.getItems().addAll("Excessive Violence", "Explicit Language", "Nudity", "Substance Abuse", "Other");
    }

    @FXML
    public void flagContent(ActionEvent actionEvent) {
        String timestamp = timestampTF.getText();
        String category = violationCategoryCB.getValue();
        String description = descriptionTA.getText();

        if (timestamp.isEmpty() || category == null || description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in the timestamp, category, and description.");
            return;
        }

        // Create a new ComplianceFlag object (You will need to create a simple ComplianceFlag.java class for this!)
        ComplianceFlag newFlag = new ComplianceFlag(currentMovieTitle, timestamp, category, description);

        // Save it to a .bin file
        File file = new File("data/complianceFlags.bin");
        ArrayList<ComplianceFlag> flagList = BinaryFileHelper.readAllObjects(file);
        if (flagList == null) {
            flagList = new ArrayList<>();
        }

        flagList.add(newFlag);
        BinaryFileHelper.writeAllObjects(file, flagList);

        showAlert(Alert.AlertType.INFORMATION, "Content Flagged", "Flag recorded for " + currentMovieTitle + " at " + timestamp);

        timestampTF.clear();
        violationCategoryCB.setValue(null);
        descriptionTA.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
