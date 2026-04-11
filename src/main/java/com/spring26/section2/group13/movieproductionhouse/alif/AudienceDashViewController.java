package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import com.spring26.section2.group13.movieproductionhouse.helpers.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AudienceDashViewController {

    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn<Movie, String> titleCol;
    @FXML
    private TableColumn<Movie, String> genreCol;
    @FXML
    private TableColumn<Movie, String> languageCol;
    @FXML
    private TableColumn<Movie, Integer> durationCol;
    @FXML
    private TableColumn<Movie, String> ratingCol;
    @FXML
    private javafx.scene.control.TextField keywordTextField;
    @FXML
    private ComboBox<String> genreComboBox;
    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private ComboBox<String> ratingComboBox;

    private ArrayList<Movie> movieList = new ArrayList<>();

    @FXML
    public void initialize() {

        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        File file = new File("data/movies.bin");
        movieList = BinaryFileHelper.readAllObjects(file);
        if (movieList != null) {
            movieTableView.getItems().addAll(movieList);
        }

        genreComboBox.getItems().addAll("All", "Action", "Sci-Fi", "Family", "Comedy", "Drama", "Horror");
        languageComboBox.getItems().addAll("All", "English", "Bengali", "Spanish", "Hindi");
        ratingComboBox.getItems().addAll("All", "G", "PG", "PG-13", "R");

        genreComboBox.setValue("Genre");
        languageComboBox.setValue("Language");
        ratingComboBox.setValue("Rating");
    }

    @FXML
    public void handleWatchMovieButton(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.sceneSwitch(actionEvent, "alif/audience-player-view.fxml", "Movie Player");
    }

    @FXML
    public void handleGoToWalletButton(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.sceneSwitch(actionEvent, "alif/audience-wallet-view.fxml", "Premium Wallet");
    }

    // HERE IS THE NEW BUTTON METHOD
    @FXML
    public void handleGoToComplaintButton(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.sceneSwitch(actionEvent, "alif/audience-complaint-view.fxml", "Submit a Complaint");
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {

        String keyword = keywordTextField.getText().toLowerCase();
        String selectedGenre = genreComboBox.getValue();
        String selectedLanguage = languageComboBox.getValue();
        String selectedRating = ratingComboBox.getValue();

        ArrayList<Movie> filteredList = new ArrayList<>();

        for (Movie movie : movieList) {

            boolean matchesKeyword = keyword.isEmpty() || movie.getTitle().toLowerCase().contains(keyword);

            boolean matchesGenre = selectedGenre == null || selectedGenre.equals("Genre") || selectedGenre.equals("All") || movie.getGenre().equals(selectedGenre);
            boolean matchesLanguage = selectedLanguage == null || selectedLanguage.equals("Language") || selectedLanguage.equals("All") || movie.getLanguage().equals(selectedLanguage);
            boolean matchesRating = selectedRating == null || selectedRating.equals("Rating") || selectedRating.equals("All") || movie.getRating().equals(selectedRating);

            if (matchesKeyword && matchesGenre && matchesLanguage && matchesRating) {
                filteredList.add(movie);
            }
        }

        movieTableView.getItems().clear();
        movieTableView.getItems().addAll(filteredList);
    }
}