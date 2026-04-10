package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class FinanceGenerateReportsController {

    @FXML
    private RadioButton incomeRadio;

    @FXML
    private ToggleGroup reportGroup;

    @FXML
    private RadioButton balanceRadio;

    @FXML
    private RadioButton cashflowRadio;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button generateBtn;

    @FXML
    private Button downloadBtn;

    @FXML
    public void initialize() {
        // Initialization logic
    }

    @FXML
    void handleGenerateReport(ActionEvent event) {
        System.out.println("Generating report...");
    }

    @FXML
    void handleDownloadPDF(ActionEvent event) {
        System.out.println("Downloading PDF...");
    }
}