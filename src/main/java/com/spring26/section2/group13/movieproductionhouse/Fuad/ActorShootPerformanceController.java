package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class ActorShootPerformanceController
{
    @javafx.fxml.FXML
    private TableView performanceTable;
    @javafx.fxml.FXML
    private TableColumn takeCol;
    @javafx.fxml.FXML
    private Label dialogueStatusLabel;
    @javafx.fxml.FXML
    private Label dialogueBar;
    @javafx.fxml.FXML
    private Label emotionBar;
    @javafx.fxml.FXML
    private Label emotionStatusLabel;
    @javafx.fxml.FXML
    private TableColumn durationCol;
    @javafx.fxml.FXML
    private TableColumn gradeCol;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleDownloadReport(ActionEvent actionEvent) {
    }
}