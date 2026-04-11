package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class ActorAttendRehearsalController
{
    @javafx.fxml.FXML
    private TableColumn dateCol;
    @javafx.fxml.FXML
    private TableColumn statusCol;
    @javafx.fxml.FXML
    private TableView rehearsalTable;
    @javafx.fxml.FXML
    private TableColumn studioCol;
    @javafx.fxml.FXML
    private TableColumn timeCol;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleConfirmAttendance(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRequestAbsence(ActionEvent actionEvent) {
    }
}