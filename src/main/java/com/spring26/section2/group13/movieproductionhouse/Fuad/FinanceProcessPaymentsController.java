package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;

public class FinanceProcessPaymentsController
{
    @javafx.fxml.FXML
    private TextArea paymentNotes;
    @javafx.fxml.FXML
    private RadioButton cheque;
    @javafx.fxml.FXML
    private TextField recipientField;
    @javafx.fxml.FXML
    private AnchorPane banktransfer;
    @javafx.fxml.FXML
    private RadioButton cash;
    @javafx.fxml.FXML
    private TextField amountField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleConfirmPayment(ActionEvent actionEvent) {
    }
}