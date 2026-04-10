package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FinanceOverrunController {

    @FXML
    private Label totalOverageLabel;

    @FXML
    private TableView<?> overrunTable; // You can replace ? with your Data Model class later

    @FXML
    private TableColumn<?, String> deptCol;

    @FXML
    private TableColumn<?, Double> actualCol;

    @FXML
    private TableColumn<?, Double> varianceCol;

    @FXML
    private TableColumn<?, Double> allocatedCol;

    @FXML
    private Button applyBtn;

    @FXML
    public void initialize() {
        // This runs when the screen loads
        System.out.println("Budget Overrun Screen Initialized.");
    }

    @FXML
    public void handleApplyAction(ActionEvent actionEvent) {
        // Add your logic here
        System.out.println("Apply Action button clicked!");
    }
}