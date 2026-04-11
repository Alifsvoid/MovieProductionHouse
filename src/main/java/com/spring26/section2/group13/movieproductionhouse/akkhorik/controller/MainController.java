package com.spring26.section2.group13.movieproductionhouse.akkhorik.controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void openExhibitorPortal() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ExhibitorPortal.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Exhibitor Portal");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openExecutivePortal() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ExecutivePortal.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Studio Executive Portal");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApplication() {
        System.exit(0);
    }
}