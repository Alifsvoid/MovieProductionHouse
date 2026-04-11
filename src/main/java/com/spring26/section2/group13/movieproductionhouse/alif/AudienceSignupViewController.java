package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;

public class AudienceSignupViewController {

    @FXML private TextField nameTF;
    @FXML private TextField emailTF;
    @FXML private ComboBox<String> genreComboBox;
    @FXML private DatePicker dobDatePicker;
    @FXML private PasswordField passwordPF;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        genreComboBox.getItems().addAll("Action", "Comedy", "Drama", "Sci-Fi", "Horror", "Thriller");
    }

    @FXML
    public void handleRegisterButton(ActionEvent event) {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String genre = genreComboBox.getValue();
        LocalDate dob = dobDatePicker.getValue();
        String password = passwordPF.getText();

        // CRA Event 3: Validation (VL) - Check for empty fields and basic email format
        if (name.isEmpty() || email.isEmpty() || genre == null || dob == null || password.isEmpty()) {
            if (statusLabel != null) statusLabel.setText("Error: All fields are required!");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            if (statusLabel != null) statusLabel.setText("Error: Enter a valid email address!");
            return;
        }

        try {
            File f = new File("data/subscribers.bin");
            ArrayList<Subscriber> subList = new ArrayList<>();

            // CRA Event 4: Data Processing (DP) - Load existing list
            if (f.exists() && f.length() > 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    subList = (ArrayList<Subscriber>) ois.readObject();
                } catch (Exception e) {
                    System.out.println("Starting new list.");
                }
            }

            // CRA Event 4: Verification (VR) - Ensure the email is not already taken
            for (Subscriber s : subList) {
                if (s.getEmail().equalsIgnoreCase(email)) {
                    if (statusLabel != null) statusLabel.setText("Error: Email already registered!");
                    return;
                }
            }

            // CRA Event 5: Create Object and Store (DP)
            Subscriber newSubscriber = new Subscriber(
                    name,
                    email,
                    password,
                    dob,
                    genre,
                    0.0,      // Default balance
                    "Free"    // Default plan
            );

            subList.add(newSubscriber);

            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
                oos.writeObject(subList);
            }

            // CRA Event 6: Output (OP) - Show success and redirect
            System.out.println("Registration Successful for: " + name);
            SceneSwitcher.sceneSwitch(event, "hello-view.fxml", "Log In");

        } catch (Exception e) {
            e.printStackTrace();
            if (statusLabel != null) statusLabel.setText("Error: Could not save account.");
        }
    }
}