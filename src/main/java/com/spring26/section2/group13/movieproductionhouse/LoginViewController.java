package com.spring26.section2.group13.movieproductionhouse;

import com.spring26.section2.group13.movieproductionhouse.alif.Subscriber;
import com.spring26.section2.group13.movieproductionhouse.helpers.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.*;
import java.util.ArrayList;

public class LoginViewController {

    @FXML private TextField emailTF;
    @FXML private PasswordField passwordPF;
    @FXML private Label errorLabel;

    @FXML
    public void handleLogin(ActionEvent event) {
        String enteredEmail = emailTF.getText();
        String enteredPassword = passwordPF.getText();

        if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
            errorLabel.setText("Please enter both Email and Password.");
            return;
        }

        if (enteredEmail.equals("officer") && enteredPassword.equals("123")) {
            LoggedInSession.login("OFF-001", "Alif", "ComplianceOfficer", "officer@test.com");
            try {
                SceneSwitcher.sceneSwitch(event, "alif/compliance-dash-view.fxml", "Compliance Dashboard");
            } catch (Exception e) {
                errorLabel.setText("Error loading Compliance Dashboard.");
            }
            return;
        }

        File f = new File("data/subscribers.bin");
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                @SuppressWarnings("unchecked")
                ArrayList<Subscriber> subList = (ArrayList<Subscriber>) ois.readObject();

                for (Subscriber sub : subList) {
                    if (sub.getEmail().equals(enteredEmail) && sub.getPassword().equals(enteredPassword)) {
                        // Pass email to session here
                        LoggedInSession.login("AUD-00X", sub.getName(), "Audience", sub.getEmail());
                        SceneSwitcher.sceneSwitch(event, "alif/audience-dash-view.fxml", "Audience Dashboard");
                        return;
                    }
                }
            } catch (Exception e) {
                errorLabel.setText("Database error.");
            }
        }
        errorLabel.setText("Invalid Email or Password.");
    }

    @FXML
    public void goToSignUp(ActionEvent event) {
        try {
            SceneSwitcher.sceneSwitch(event, "alif/audience-signup-view.fxml", "Sign Up");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}