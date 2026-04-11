package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class FinanceApprovePurchasesController
{
    @javafx.fxml.FXML
    private TableColumn departmentcol;
    @javafx.fxml.FXML
    private TableColumn itemnamecol;
    @javafx.fxml.FXML
    private TableColumn amountcol;
    @javafx.fxml.FXML
    private TableColumn requestcol;
    @javafx.fxml.FXML
    private TableView purchaseTable;
    @javafx.fxml.FXML
    private Button approveBtn;
    @javafx.fxml.FXML
    private Button rejectBtn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleApprovePurchase(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRejectPurchase(ActionEvent actionEvent) {
    }
}