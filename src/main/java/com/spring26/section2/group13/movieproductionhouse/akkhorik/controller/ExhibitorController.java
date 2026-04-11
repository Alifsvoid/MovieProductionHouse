package com.spring26.section2.group13.movieproductionhouse.akkhorik.controller;

import com.spring26.section2.group13.movieproductionhouse.akkhorik.model.*;
import com.spring26.section2.group13.movieproductionhouse.akkhorik.service.AlertHelper;
import com.spring26.section2.group13.movieproductionhouse.akkhorik.service.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExhibitorController {

    @FXML private TableView<Film> filmsTable;
    @FXML private TableView<Screening> screeningsTable;
    @FXML private TextArea outputArea;

    private DataService dataService = new DataService();
    private ObservableList<Film> filmList = FXCollections.observableArrayList();
    private ObservableList<Screening> screeningList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        setupFilmTableColumns();
        setupScreeningTableColumns();
        refreshData();
    }

    private void setupFilmTableColumns() {
        TableColumn<Film, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Film, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Film, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TableColumn<Film, String> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        TableColumn<Film, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Film, Double> feeCol = new TableColumn<>("License Fee");
        feeCol.setCellValueFactory(new PropertyValueFactory<>("licensingFee"));

        filmsTable.getColumns().setAll(idCol, titleCol, genreCol, ratingCol, statusCol, feeCol);
    }

    private void setupScreeningTableColumns() {
        TableColumn<Screening, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("screeningId"));
        TableColumn<Screening, String> filmCol = new TableColumn<>("Film ID");
        filmCol.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        TableColumn<Screening, LocalDateTime> timeCol = new TableColumn<>("Showtime");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("showtime"));
        TableColumn<Screening, Integer> screenCol = new TableColumn<>("Screen");
        screenCol.setCellValueFactory(new PropertyValueFactory<>("screenNumber"));
        TableColumn<Screening, Integer> soldCol = new TableColumn<>("Tickets Sold");
        soldCol.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
        TableColumn<Screening, Double> revenueCol = new TableColumn<>("Revenue");
        revenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        screeningsTable.getColumns().setAll(idCol, filmCol, timeCol, screenCol, soldCol, revenueCol);
    }

    @FXML
    private void browseFilms() {
        filmList.setAll(dataService.loadAvailableFilms());
        filmsTable.setItems(filmList);
        outputArea.setText("Displaying " + filmList.size() + " available films.");
    }

    @FXML
    private void requestLicense() {
        Film selected = filmsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertHelper.showWarning("No Film Selected", "Please select a film from the table.");
            return;
        }

        Dialog<License> dialog = new Dialog<>();
        dialog.setTitle("Request License");
        dialog.setHeaderText("Film: " + selected.getTitle());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField exhibitorField = new TextField();
        TextField regionField = new TextField();
        TextField screensField = new TextField();
        TextField shareField = new TextField();
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        grid.add(new Label("Exhibitor Name:"), 0, 0);
        grid.add(exhibitorField, 1, 0);
        grid.add(new Label("Region:"), 0, 1);
        grid.add(regionField, 1, 1);
        grid.add(new Label("Number of Screens:"), 0, 2);
        grid.add(screensField, 1, 2);
        grid.add(new Label("Revenue Share %:"), 0, 3);
        grid.add(shareField, 1, 3);
        grid.add(new Label("Start Date:"), 0, 4);
        grid.add(startDatePicker, 1, 4);
        grid.add(new Label("End Date:"), 0, 5);
        grid.add(endDatePicker, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    String exhibitor = exhibitorField.getText();
                    String region = regionField.getText();
                    int screens = Integer.parseInt(screensField.getText());
                    double share = Double.parseDouble(shareField.getText());
                    LocalDate start = startDatePicker.getValue();
                    LocalDate end = endDatePicker.getValue();

                    if (exhibitor.isEmpty() || region.isEmpty() || start == null || end == null) {
                        AlertHelper.showError("Invalid Input", "All fields are required.");
                        return null;
                    }
                    if (!end.isAfter(start)) {
                        AlertHelper.showError("Invalid Dates", "End date must be after start date.");
                        return null;
                    }

                    return new License(
                            UUID.randomUUID().toString().substring(0,8),
                            selected.getId(),
                            exhibitor,
                            start,
                            end,
                            region,
                            screens,
                            share
                    );
                } catch (NumberFormatException e) {
                    AlertHelper.showError("Invalid Number", "Screens and Share must be numeric.");
                    return null;
                }
            }
            return null;
        });

        Optional<License> result = dialog.showAndWait();
        result.ifPresent(license -> {
            dataService.saveLicense(license);
            outputArea.setText("License request submitted. ID: " + license.getLicenseId());
        });
    }

    @FXML
    private void downloadDCP() {
        License license = selectLicenseDialog();
        if (license == null) return;
        if (!license.getStatus().equals("Approved")) {
            AlertHelper.showError("Invalid License", "License must be approved to download DCP.");
            return;
        }
        outputArea.setText("Verifying license... OK\nInitiating secure download of encrypted DCP for Film ID: "
                + license.getFilmId() + "\nDownload complete.");
    }

    @FXML
    private void scheduleScreenings() {
        License license = selectLicenseDialog();
        if (license == null) return;
        if (!license.getStatus().equals("Approved")) {
            AlertHelper.showError("Invalid License", "License must be approved to schedule screenings.");
            return;
        }

        Dialog<Screening> dialog = new Dialog<>();
        dialog.setTitle("Schedule Screening");
        dialog.setHeaderText("License: " + license.getLicenseId());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField screenField = new TextField();
        TextField capacityField = new TextField();
        TextField priceField = new TextField();
        TextField dateField = new TextField();
        dateField.setPromptText("YYYY-MM-DD");
        TextField timeField = new TextField();
        timeField.setPromptText("HH:MM");

        grid.add(new Label("Screen Number:"), 0, 0);
        grid.add(screenField, 1, 0);
        grid.add(new Label("Capacity:"), 0, 1);
        grid.add(capacityField, 1, 1);
        grid.add(new Label("Ticket Price ($):"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Date:"), 0, 3);
        grid.add(dateField, 1, 3);
        grid.add(new Label("Time:"), 0, 4);
        grid.add(timeField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    int screen = Integer.parseInt(screenField.getText());
                    int capacity = Integer.parseInt(capacityField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    LocalDateTime showtime = LocalDateTime.parse(dateField.getText() + "T" + timeField.getText());

                    return new Screening(
                            UUID.randomUUID().toString().substring(0,8),
                            license.getLicenseId(),
                            license.getFilmId(),
                            showtime,
                            screen,
                            capacity,
                            price
                    );
                } catch (NumberFormatException | DateTimeParseException e) {
                    AlertHelper.showError("Invalid Input", "Check format of numbers and date/time.");
                    return null;
                }
            }
            return null;
        });

        Optional<Screening> result = dialog.showAndWait();
        result.ifPresent(screening -> {
            dataService.saveScreening(screening);
            outputArea.setText("Screening scheduled: " + screening.getScreeningId());
            refreshScreenings();
        });
    }

    @FXML
    private void trackBoxOffice() {
        License license = selectLicenseDialog();
        if (license == null) return;

        List<Screening> screenings = dataService.loadAllScreenings().stream()
                .filter(s -> s.getLicenseId().equals(license.getLicenseId()))
                .collect(Collectors.toList());

        if (screenings.isEmpty()) {
            outputArea.setText("No screenings found for this license.");
            return;
        }

        StringBuilder sb = new StringBuilder("Box Office Performance:\n");
        sb.append("Film ID: ").append(license.getFilmId()).append("\n");
        sb.append("Period: ").append(license.getStartDate()).append(" to ").append(license.getEndDate()).append("\n\n");

        double totalGross = 0;
        int totalTickets = 0;
        int totalCapacity = 0;

        for (Screening s : screenings) {
            sb.append(String.format("Screening %s: %d/%d tickets sold, Revenue: $%.2f\n",
                    s.getScreeningId(), s.getTicketsSold(), s.getCapacity(), s.getRevenue()));
            totalGross += s.getRevenue();
            totalTickets += s.getTicketsSold();
            totalCapacity += s.getCapacity();
        }

        double occupancy = totalCapacity > 0 ? (double) totalTickets / totalCapacity * 100 : 0;
        sb.append("\n--- Summary ---\n");
        sb.append(String.format("Total Gross: $%.2f\n", totalGross));
        sb.append(String.format("Average Occupancy: %.1f%%\n", occupancy));
        sb.append(String.format("Studio Share (%.1f%%): $%.2f\n", license.getRevenueSharePercent(),
                totalGross * license.getRevenueSharePercent() / 100));

        outputArea.setText(sb.toString());
    }

    @FXML
    private void manageKDM() {
        License license = selectLicenseDialog();
        if (license == null || !license.getStatus().equals("Approved")) {
            AlertHelper.showError("Invalid License", "License must be approved to generate KDM.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("KDM Generation");
        dialog.setHeaderText("Enter Projector Server Serial Number");
        dialog.setContentText("Serial:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(serial -> {
            if (serial.matches("^[A-Z0-9]{8,20}$")) {
                String kdm = "KDM-" + UUID.randomUUID().toString().substring(0,12).toUpperCase();
                license.setKdmKey(kdm);
                dataService.updateLicense(license);
                outputArea.setText("KDM generated: " + kdm + "\nValid from " + license.getStartDate() + " to " + license.getEndDate());
            } else {
                AlertHelper.showError("Invalid Serial", "Serial must be 8-20 uppercase letters/digits.");
            }
        });
    }

    @FXML
    private void endEngagement() {
        License license = selectLicenseDialog();
        if (license == null) return;

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("End Engagement");
        confirmation.setHeaderText("Confirm Film Return");
        confirmation.setContentText("This will confirm secure deletion of DCP files for license " + license.getLicenseId() + ".");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            license.setStatus("Expired");
            dataService.updateLicense(license);
            outputArea.setText("Engagement ended. Deletion confirmed. Receipt sent to studio.");
        }
    }

    @FXML
    private void generateSettlement() {
        License license = selectLicenseDialog();
        if (license == null) return;

        List<Screening> screenings = dataService.loadAllScreenings().stream()
                .filter(s -> s.getLicenseId().equals(license.getLicenseId()))
                .collect(Collectors.toList());

        double totalGross = screenings.stream().mapToDouble(Screening::getRevenue).sum();
        double studioShare = totalGross * license.getRevenueSharePercent() / 100;
        double exhibitorRetention = totalGross - studioShare;

        StringBuilder sb = new StringBuilder("=== SETTLEMENT REPORT ===\n");
        sb.append("Film ID: ").append(license.getFilmId()).append("\n");
        sb.append("Exhibitor: ").append(license.getExhibitorName()).append("\n");
        sb.append("Period: ").append(license.getStartDate()).append(" to ").append(license.getEndDate()).append("\n\n");
        sb.append(String.format("Total Box Office Gross: $%.2f\n", totalGross));
        sb.append(String.format("Exhibitor Retention (%.1f%%): $%.2f\n", 100 - license.getRevenueSharePercent(), exhibitorRetention));
        sb.append(String.format("Studio Share (%.1f%%): $%.2f\n", license.getRevenueSharePercent(), studioShare));
        sb.append("\n--- Payment Due to Studio ---\n");
        sb.append(String.format("Amount: $%.2f\n", studioShare));

        outputArea.setText(sb.toString());
    }

    @FXML
    private void refreshData() {
        filmList.setAll(dataService.loadAvailableFilms());
        filmsTable.setItems(filmList);
        refreshScreenings();
        outputArea.clear();
    }

    private void refreshScreenings() {
        screeningList.setAll(dataService.loadAllScreenings());
        screeningsTable.setItems(screeningList);
    }

    @FXML
    private void backToMain() {
        Stage stage = (Stage) filmsTable.getScene().getWindow();
        stage.close();
    }

    private License selectLicenseDialog() {
        List<License> licenses = dataService.loadAllLicenses();
        if (licenses.isEmpty()) {
            AlertHelper.showInfo("No Licenses", "No licenses found in the system.");
            return null;
        }

        ChoiceDialog<License> dialog = new ChoiceDialog<>(licenses.get(0), licenses);
        dialog.setTitle("Select License");
        dialog.setHeaderText("Choose a license");
        dialog.setContentText("License:");

        Optional<License> result = dialog.showAndWait();
        return result.orElse(null);
    }
}