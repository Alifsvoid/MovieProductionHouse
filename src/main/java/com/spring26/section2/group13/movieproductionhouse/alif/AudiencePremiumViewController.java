package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.LoggedInSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;

public class AudiencePremiumViewController {

    @FXML private ComboBox<String> planCB;
    @FXML private TextField cardNumberTF, expiryDateTF, cvvTF;

    private Subscriber currentSubscriber;

    @FXML
    public void initialize() {
        planCB.getItems().addAll("Premium Monthly - $9.99", "Premium Annual - $99.99");
        loadUserFromBin();
    }

    private void loadUserFromBin() {
        String email = LoggedInSession.getInstance().getEmail();
        File f = new File("data/subscribers.bin");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ArrayList<Subscriber> subList = (ArrayList<Subscriber>) ois.readObject();
            for (Subscriber s : subList) {
                if (s.getEmail().equals(email)) {
                    currentSubscriber = s;
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void purchaseSubscription(ActionEvent actionEvent) {
        String plan = planCB.getValue();
        if (plan == null || cardNumberTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Missing information.");
            return;
        }

        double cost = plan.contains("99.99") ? 99.99 : 9.99;

        // CRA VR (Verification): Check if wallet has enough money
        if (currentSubscriber.getWalletBalance() >= cost) {
            currentSubscriber.setWalletBalance(currentSubscriber.getWalletBalance() - cost);
            currentSubscriber.setSubscriptionPlan("Premium");

            saveAllToBin(); // Update the file permanently

            showAlert(Alert.AlertType.INFORMATION, "Success", "You are now a Premium member!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Insufficient Balance", "Please top up your wallet first.");
        }
    }

    private void saveAllToBin() {
        File f = new File("data/subscribers.bin");
        try {
            ArrayList<Subscriber> subList;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                subList = (ArrayList<Subscriber>) ois.readObject();
            }
            for (int i = 0; i < subList.size(); i++) {
                if (subList.get(i).getEmail().equals(currentSubscriber.getEmail())) {
                    subList.set(i, currentSubscriber);
                    break;
                }
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
                oos.writeObject(subList);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
