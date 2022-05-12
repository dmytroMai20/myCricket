package uk.ac.cam.group09.mycricket.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.CricketApp;

import java.io.IOException;

public class FavController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private Stage dialogStage;

    @FXML private VBox favCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView, Stage dialogStage) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.dialogStage = dialogStage;
    }

    @FXML
    protected void switchToHome() {
        mainStage.setScene(homeView);
        mainStage.show();
    }

    @FXML
    protected void openNewFav() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-fav-view.fxml"));
        Scene newFav = new Scene(fxmlLoader.load());
        NewFavController newFavController = fxmlLoader.getController();
        newFavController.setUp(dialogStage, this);

        dialogStage.setScene(newFav);
        dialogStage.show();
    }

    protected void addNewCard() throws IOException {
        // TODO: rewrite method signature as addNewCard(Info info)?
        // load a new Node object representing a card
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("fav-card-view.fxml"));
        AnchorPane card = fxmlLoader.load();

        VBox mainContainer = (VBox) card.getChildren().get(0);
        HBox titleBox = (HBox) mainContainer.getChildren().get(0);

        Label temperature = (Label) titleBox.getChildren().get(1);
        temperature.setText("25˚");

        // TODO: bind the relevant fields with their corresponding properties


        // add the card to the cardBox
        favCardBox.getChildren().add(card);
    }
}