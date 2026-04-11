package com.spring26.section2.group13.movieproductionhouse.Fuad;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class ActorShootScheduleController
{
    @javafx.fxml.FXML
    private TableView shootScheduleTable;
    @javafx.fxml.FXML
    private TableColumn locationColumn;
    @javafx.fxml.FXML
    private TableColumn shootTimeColumn;
    @javafx.fxml.FXML
    private TableColumn callTimeColumn;
    @javafx.fxml.FXML
    private TableColumn dateColumn;
    @javafx.fxml.FXML
    private TableColumn sceneInfoColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handlerexportbutton(ActionEvent actionEvent) {
    }
}