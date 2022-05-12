package uk.ac.cam.group09.mycricket.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class NewWatchController {

    private Stage dialogStage;

    @FXML ChoiceBox hourChoiceBox;
    @FXML ChoiceBox minChoiceBox;

    public void setUp(Stage dialogStage) {
        this.dialogStage = dialogStage;

        initialize();
    }

    protected void initialize() {
        // setting up the ChoiceBox options
        ObservableList<Integer> hours = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        hourChoiceBox.setItems(hours);

        ObservableList<String> minutes = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
        minChoiceBox.setItems(minutes);
    }

    @FXML
    protected void close() {
        dialogStage.close();
    }
}
