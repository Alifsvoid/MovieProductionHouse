package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;

public class ComplianceComplaintsViewController {

    @FXML private TableView<Complaint> complaintsTV;
    @FXML private TableColumn<Complaint, String> compIdCol;
    @FXML private TableColumn<Complaint, String> compCategoryCol;
    @FXML private TableColumn<Complaint, String> compStatusCol;

    @FXML private ComboBox<String> statusCB;

    private ArrayList<Complaint> complaintList = new ArrayList<>();
    private final String FILE_PATH = "data/complaints.bin";

    @FXML
    public void initialize() {
        // Link columns
        compIdCol.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
        compCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        compStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Setup Dropdown
        statusCB.getItems().addAll("Under Review", "Resolved");

        // Load Real Data from the bin file!
        loadComplaints();
    }

    private void loadComplaints() {
        File file = new File(FILE_PATH);
        complaintList = BinaryFileHelper.readAllObjects(file);

        if (complaintList != null) {
            complaintsTV.getItems().clear();
            complaintsTV.getItems().addAll(complaintList);
        }
    }

    @FXML
    public void updateComplaintStatus(ActionEvent actionEvent) {
        Complaint selectedComplaint = complaintsTV.getSelectionModel().getSelectedItem();
        String newStatus = statusCB.getValue();

        if (selectedComplaint == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a complaint from the table.");
            return;
        }

        if (newStatus == null) {
            showAlert(Alert.AlertType.WARNING, "No Status", "Please select a new status to apply.");
            return;
        }

        // Update the status of the selected object
        selectedComplaint.setStatus(newStatus);

        // Save the updated list back to the bin file
        File file = new File(FILE_PATH);
        BinaryFileHelper.writeAllObjects(file, complaintList);

        // Refresh table and UI
        complaintsTV.refresh();
        statusCB.setValue(null);
        showAlert(Alert.AlertType.INFORMATION, "Success", "Complaint ID: " + selectedComplaint.getComplaintId() + " updated to " + newStatus);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
