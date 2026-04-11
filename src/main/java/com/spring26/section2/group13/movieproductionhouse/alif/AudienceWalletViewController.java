package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.LoggedInSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;

public class AudienceWalletViewController {

    @FXML private Label currentBalanceLabel;
    @FXML private TextField rechargeAmountTF;
    @FXML private ComboBox<String> paymentMethodCB;

    private Subscriber currentSubscriber;

    @FXML
    public void initialize() {
        paymentMethodCB.getItems().addAll("Credit Card", "Debit Card", "bKash", "Nagad");
        loadUserFromBin();
    }

    @SuppressWarnings("unchecked")
    private void loadUserFromBin() {
        String email = LoggedInSession.getInstance().getEmail();
        File f = new File("data/subscribers.bin");
        if (!f.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ArrayList<Subscriber> subList = (ArrayList<Subscriber>) ois.readObject();
            for (Subscriber s : subList) {
                if (s.getEmail().equals(email)) {
                    currentSubscriber = s;
                    currentBalanceLabel.setText("$" + String.format("%.2f", s.getWalletBalance()));
                    break;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void topUpWallet(ActionEvent actionEvent) {
        try {
            double amount = Double.parseDouble(rechargeAmountTF.getText());
            if (amount > 0 && paymentMethodCB.getValue() != null) {
                currentSubscriber.setWalletBalance(currentSubscriber.getWalletBalance() + amount);
                saveAllToBin();
                currentBalanceLabel.setText("$" + String.format("%.2f", currentSubscriber.getWalletBalance()));
                rechargeAmountTF.clear();
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    @SuppressWarnings("unchecked")
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
}