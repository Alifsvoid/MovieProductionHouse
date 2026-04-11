package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class FinanceTrackExpensesController
{
    @javafx.fxml.FXML
    private TextField expenseNameField;
    @javafx.fxml.FXML
    private DatePicker expenseDatePicker;
    @javafx.fxml.FXML
    private TableColumn dateCol;
    @javafx.fxml.FXML
    private TableColumn descCol;
    @javafx.fxml.FXML
    private TableColumn amountCol;
    @javafx.fxml.FXML
    private Label totalDisplayLabel;
    @javafx.fxml.FXML
    private TableColumn catCol;
    @javafx.fxml.FXML
    private TableView expenseTable;
    @javafx.fxml.FXML
    private TextField expenseAmountField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleAddExpense(ActionEvent actionEvent) {
    }
}