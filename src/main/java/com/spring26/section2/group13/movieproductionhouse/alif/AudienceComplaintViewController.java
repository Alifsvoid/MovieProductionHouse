package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.ArrayList;

public class AudienceComplaintViewController {

    @FXML
    private ComboBox<String> categoryCB;
    @FXML
    private TextArea descriptionTA;

    @FXML
    public void initialize() {
        // CRA Workflow Event 2: Load categories
        categoryCB.getItems().addAll("Playback Issue", "Billing Issue", "App Crash", "Content Flag", "Other");
    }

    @FXML
    public void submitComplaint(ActionEvent actionEvent) {
        String selectedCategory = categoryCB.getValue();
        String description = descriptionTA.getText();

        // CRA Workflow Event 3: System validates complaint input
        if (selectedCategory == null || description == null || description.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please select a category and enter an issue description.");
            return;
        }

        // CRA Workflow Event 4: Generate a complaint ID
        String newComplaintId = "C-" + System.currentTimeMillis();

        // CRA Workflow Event 5: Create object and store in bin file
        Complaint newComplaint = new Complaint(newComplaintId, selectedCategory, description, "Pending");

        File file = new File("data/complaints.bin");
        ArrayList<Complaint> complaintList = BinaryFileHelper.readAllObjects(file);

        if (complaintList == null) {
            complaintList = new ArrayList<>();
        }

        complaintList.add(newComplaint);
        BinaryFileHelper.writeAllObjects(file, complaintList);

        // Display confirmation message
        showAlert(Alert.AlertType.INFORMATION, "Complaint Submitted", "Your complaint has been submitted successfully.\nComplaint ID: " + newComplaintId);

        // Clear the form
        categoryCB.setValue(null);
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