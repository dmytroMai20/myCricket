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
import uk.ac.cam.group09.mycricket.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class HomeController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private LinkedList<CardManager> cardManagerList;

    @FXML private VBox watchCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.cardManagerList = new LinkedList<>();
    }

    @FXML
    protected void switchToFav() {
        for (CardManager cardManager : cardManagerList) {
            cardManager.refresh();
        }
        mainStage.setScene(favView);
        mainStage.show();
    }

    @FXML
    public void switchBack() {
        mainStage.setScene(homeView);
        mainStage.show();
    }

    @FXML
    protected void openNewWatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-watch-view.fxml"));
        Scene newWatch = new Scene(fxmlLoader.load());
        NewWatchController newWatchController = fxmlLoader.getController();
        newWatchController.setUp(this);

        mainStage.setScene(newWatch);
        mainStage.show();
    }

    protected void addNewCard(CardManager cardManager) throws IOException {
        // load a new Node object representing a card
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("watch-card-view.fxml"));
        AnchorPane card = fxmlLoader.load();

        VBox mainContainer = (VBox) card.getChildren().get(0);
        HBox titleBox = (HBox) mainContainer.getChildren().get(0);
        VBox meta = (VBox) titleBox.getChildren().get(0);

        Label title = (Label) meta.getChildren().get(0);
        Label time = (Label) meta.getChildren().get(1);
        Label location = (Label) meta.getChildren().get(2);
        Label temperature = (Label) titleBox.getChildren().get(1);

        // add the CardManager object to the cardManagerList
        cardManagerList.add(cardManager);

        // TODO: bind the relevant fields with their corresponding properties
        title.textProperty().bind(cardManager.getTitle());
        time.textProperty().bind(cardManager.getTime());
        location.textProperty().bind(cardManager.getLocation());
        temperature.textProperty().bind(cardManager.getTemperature());

        // TODO: add a listener method to change the color of the card based on risk level


        // add the card to the cardBox
        watchCardBox.getChildren().add(card);
    }
}
