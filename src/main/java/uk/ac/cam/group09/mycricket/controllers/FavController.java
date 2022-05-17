package uk.ac.cam.group09.mycricket.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.HashMap;
import java.util.LinkedList;

public class FavController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private LinkedList<CardManager> cardManagerList;

    @FXML private VBox favCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.cardManagerList = new LinkedList<>();

        try {
            exampleAddNewCard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void switchToHome() {
        for (CardManager cardManager : cardManagerList) {
            cardManager.refresh();
        }
        mainStage.setScene(homeView);
        mainStage.show();
    }

    protected void switchBack() {
        mainStage.setScene(favView);
        mainStage.show();
    }

    @FXML
    protected void openNewFav() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-fav-view.fxml"));
        Scene newFav = new Scene(fxmlLoader.load());
        NewFavController newFavController = fxmlLoader.getController();
        newFavController.setUp(this);

        mainStage.setScene(newFav);
        mainStage.show();
    }

    protected void addNewCard(CardManager cardManager) throws IOException {
        // load a new Node object representing a card
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("fav-card-view.fxml"));
        AnchorPane card = fxmlLoader.load();

        VBox mainContainer = (VBox) card.getChildren().get(0);
        HBox titleBox = (HBox) mainContainer.getChildren().get(0);
        VBox meta = (VBox) titleBox.getChildren().get(0);

        Label title = (Label) meta.getChildren().get(0);
        Label time = (Label) meta.getChildren().get(1);
        Label temperature = (Label) titleBox.getChildren().get(1);

        // add the CardManager object to the cardManagerList
        cardManagerList.add(cardManager);

        // TODO: bind the relevant fields with their corresponding properties
        title.textProperty().bind(cardManager.getTitle());
        time.textProperty().bind(cardManager.getTime());
        temperature.textProperty().bind(cardManager.getTemperature());

        // setting the color of the card based on risk level
        if (cardManager.getRiskLevel().getValue().equals(2)) {
            // extreme or high risk
            card.setStyle("-fx-background-color: #CE2029");
        } else if (cardManager.getRiskLevel().getValue().equals(1)) {
            // medium or low risk
            card.setStyle("-fx-background-color: #FFB034");
        }

        // a listener method to change the color of the card based on risk level
        cardManager.getRiskLevel().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                if (newVal.equals(2)) {
                    // extreme or high risk
                    card.setStyle("-fx-background-color: #CE2029");
                } else if (newVal.equals(1)) {
                    // medium or low risk
                    card.setStyle("-fx-background-color: #FFB034");
                } else {
                    // default: no risk
                    card.setStyle("-fx-background-color: #679BF1");
                }
            }
        });

        // add the card to the cardBox
        favCardBox.getChildren().add(card);
    }

    private void exampleAddNewCard() throws IOException {
        CardManager exampleCardManager = new CardManager(new HashMap<String,String>() {{
            put("Address", "William Gates Building J J Thomson Avenue 15, CB3 0FD Cambridge");
            put("Country", "United Kingdom");
            put("Latitude", "0.09202940714286707");
            put("Longitude", "52.21093155");
        }}, "Computer Lab");

        addNewCard(exampleCardManager);
    }
}