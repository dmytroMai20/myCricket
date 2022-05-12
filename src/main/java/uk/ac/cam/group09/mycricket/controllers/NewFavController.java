package uk.ac.cam.group09.mycricket.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class NewFavController {

    private Stage dialogStage;


    public void setUp(Stage dialogStage) {
        this.dialogStage = dialogStage;

        initialize();
    }

    protected void initialize() {


    }

    @FXML
    protected void close() {
        dialogStage.close();
    }
}