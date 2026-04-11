package com.spring26.section2.group13.movieproductionhouse.alif;

import com.spring26.section2.group13.movieproductionhouse.helpers.BinaryFileHelper;
import com.spring26.section2.group13.movieproductionhouse.helpers.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ComplianceDashViewController {

    @FXML
    private TableView<ComplianceTask> taskTableView;
    @FXML
    private TableColumn<ComplianceTask, String> taskIdCol;
    @FXML
    private TableColumn<ComplianceTask, String> movieTitleCol;
    @FXML
    private TableColumn<ComplianceTask, String> taskTypeCol;
    @FXML
    private TableColumn<ComplianceTask, String> statusCol;

    private ArrayList<ComplianceTask> taskList = new ArrayList<>();

    @FXML
    public void initialize() {
        taskIdCol.setCellValueFactory(new PropertyValueFactory<>("taskID"));
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        taskTypeCol.setCellValueFactory(new PropertyValueFactory<>("taskType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        File file = new File("data/complianceTasks.bin");
        taskList = BinaryFileHelper.readAllObjects(file);
        if (taskList != null) {
            taskTableView.getItems().addAll(taskList);
        }
    }

    @FXML
    public void handleRefreshButton(ActionEvent actionEvent) {
        taskTableView.getItems().clear();
        File file = new File("data/complianceTasks.bin");
        taskList = BinaryFileHelper.readAllObjects(file);
        if (taskList != null) {
            taskTableView.getItems().addAll(taskList);
        }
    }

    @FXML
    public void handleGoToComplaintsButton(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.sceneSwitch(actionEvent, "alif/compliance-complaints-view.fxml", "Review Complaints");
    }

    // HERE IS THE NEW BUTTON METHOD
    @FXML
    public void handleRateMoviesButton(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.sceneSwitch(actionEvent, "alif/compliance-rating-view.fxml", "Rate Movies");
    }
}