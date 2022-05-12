package uk.ac.cam.group09.mycricket.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.CricketApp;

import java.io.IOException;

public class HomeController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private Stage dialogStage;

    @FXML private VBox watchCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView, Stage dialogStage) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.dialogStage = dialogStage;
    }

    @FXML
    protected void switchToFav() {
        mainStage.setScene(favView);
        mainStage.show();
    }

    @FXML
    protected void openNewWatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-watch-view.fxml"));
        Scene newWatch = new Scene(fxmlLoader.load());
        NewWatchController newWatchController = fxmlLoader.getController();
        newWatchController.setUp(dialogStage, this);

        dialogStage.setScene(newWatch);
        dialogStage.show();
    }

    protected void addNewCard() throws IOException {
        // TODO: rewrite method signature as addNewCard(Info info)?
        // load a new Node object representing a card
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("watch-card-view.fxml"));
        AnchorPane card = fxmlLoader.load();

        VBox mainContainer = (VBox) card.getChildren().get(0);
        HBox titleBox = (HBox) mainContainer.getChildren().get(0);

        Label temperature = (Label) titleBox.getChildren().get(1);
        temperature.setText("25˚");

        // TODO: bind the relevant fields with their corresponding properties


        // add the card to the cardBox
        watchCardBox.getChildren().add(card);
    }
}
