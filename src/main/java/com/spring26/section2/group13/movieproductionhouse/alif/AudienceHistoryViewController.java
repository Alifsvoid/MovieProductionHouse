package com.spring26.section2.group13.movieproductionhouse.alif;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AudienceHistoryViewController {

    @FXML private TableView<HistoryItem> historyTV;
    @FXML private TableColumn<HistoryItem, String> historyTitleCol;
    @FXML private TableColumn<HistoryItem, String> historyDateCol;

    @FXML
    public void initialize() {
        // Tell columns where to get their data
        historyTitleCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        historyDateCol.setCellValueFactory(new PropertyValueFactory<>("dateWatched"));

        // Load dummy data
        ObservableList<HistoryItem> historyData = FXCollections.observableArrayList(
                new HistoryItem("The Batman", "2024-04-01"),
                new HistoryItem("Dune: Part Two", "2024-04-05"),
                new HistoryItem("Deadpool", "2024-04-08")
        );

        historyTV.setItems(historyData);
    }

    // --- Helper Class for the Table (You can move this to its own file later!) ---
    public static class HistoryItem {
        private String movieTitle;
        private String dateWatched;

        public HistoryItem(String movieTitle, String dateWatched) {
            this.movieTitle = movieTitle;
            this.dateWatched = dateWatched;
        }

        public String getMovieTitle() { return movieTitle; }
        public String getDateWatched() { return dateWatched; }
    }
}