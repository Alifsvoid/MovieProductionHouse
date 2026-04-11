package com.spring26.section2.group13.movieproductionhouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // The file name goes INSIDE the quotes right here!
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("alif/compliance-dash-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Complaint Screen Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}