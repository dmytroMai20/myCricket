package uk.ac.cam.group09.mycricket.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.CardManager;
import uk.ac.cam.group09.mycricket.CricketApp;
import uk.ac.cam.group09.mycricket.MatchHandler;

import java.io.IOException;
import java.util.LinkedList;

public class FavController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private Stage dialogStage;
    private LinkedList<CardManager> cardManagerList;

    @FXML private VBox favCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView, Stage dialogStage) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.dialogStage = dialogStage;
    }

    @FXML
    protected void switchToHome() {
        for (CardManager cardManager : cardManagerList) {
            cardManager.refresh();
        }

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

    protected void addNewCard(CardManager cardManager) throws IOException {
        // TODO: rewrite method signature as addNewCard(Info info)?
        // load a new Node object representing a card
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("fav-card-view.fxml"));
        AnchorPane card = fxmlLoader.load();

        VBox mainContainer = (VBox) card.getChildren().get(0);
        HBox titleBox = (HBox) mainContainer.getChildren().get(0);

        VBox meta = (VBox) titleBox.getChildren().get(0);
        Label temperature = (Label) titleBox.getChildren().get(1);

        Label title = (Label) meta.getChildren().get(0);

        Label time = (Label) meta.getChildren().get(1);

        Label location = (Label) meta.getChildren().get(2);

        // TODO: add the Info object to the cardManager <List>
        cardManagerList.add(cardManager);

        // TODO: bind the relevant fields with their corresponding properties
        title.textProperty().bind(cardManager.getTitle());
        time.textProperty().bind(cardManager.getTime());
        location.textProperty().bind(cardManager.getLocation());
        temperature.textProperty().bind(cardManager.getTemperature());

        // add the card to the cardBox
        favCardBox.getChildren().add(card);
    }
}