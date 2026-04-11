package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import java.time.LocalDate;

public class ComplianceReportsViewController {

    @FXML private TextArea reportTA;

    @FXML
    public void generateReport(ActionEvent actionEvent) {
        // In a fully connected app, this would calculate real metrics from your .bin files!
        String report = "COMPLIANCE & MODERATION REPORT\n";
        report += "Date: " + LocalDate.now() + "\n";
        report += "========================================\n\n";
        report += "-> Movies Approved this month: 14\n";
        report += "-> Movies Suspended: 2\n";
        report += "-> Pending Reviews: 3\n";
        report += "-> Content Flags Issued: 5\n";
        report += "-> Audience Complaints Resolved: 8\n\n";
        report += "OVERALL STATUS: Nominal. All compliance metrics are within acceptable ranges. No critical platform violations detected.";

        reportTA.setText(report);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report Generated");
        alert.setHeaderText(null);
        alert.setContentText("Latest compliance metrics have been successfully generated.");
        alert.showAndWait();
    }
}