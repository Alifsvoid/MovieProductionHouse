package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ActorDashboardController {

    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label ActorPortal;

    @FXML
    public void initialize() {
    }

    private void loadInterface(String fxmlFile) {
        try {
            Parent node = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll(node);
        } catch (IOException e) {
            System.out.println("Error loading " + fxmlFile);
        }
    }

    @FXML
    public void showViewScript(ActionEvent actionEvent) {
        loadInterface("ActorViewScript.fxml");
    }

    @FXML
    public void RehearsalSchedule(ActionEvent actionEvent) {
        loadInterface("ActorAttendRehearsal.fxml");
    }

    @FXML
    public void ShootingStatus(ActionEvent actionEvent) {
        loadInterface("ActorShootSchedule.fxml");
    }

    @FXML
    public void DirectorFeedback(ActionEvent actionEvent) {
        loadInterface("ActorPerformanceFeedback.fxml");
    }

    @FXML
    public void logout(ActionEvent actionEvent) {
        System.out.println("User logged out");
    }

    @FXML
    public void PromotionTasks(ActionEvent actionEvent) {
    }

    @FXML
    public void FullCalendar(ActionEvent actionEvent) {
    }

    @FXML
    public void CareerProgress(ActionEvent actionEvent) {
    }

    @FXML
    public void CostumeMakeup(ActionEvent actionEvent) {
    }
}