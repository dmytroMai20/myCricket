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
import uk.ac.cam.group09.mycricket.Match;
import uk.ac.cam.group09.mycricket.Weather;
import uk.ac.cam.group09.mycricket.WeatherConditions;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class HomeController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private static class WatchCard {
        Match match;
        WatchCard(Match match) {
            this.match = match;
        }

        AnchorPane getCard() throws java.io.IOException {

            // Get WeatherCondition
            WeatherConditions weatherCond =  this.match.getWeather();

            // Create card
            FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("watch-card-view.fxml"));
            AnchorPane card = fxmlLoader.load();

            VBox mainContainer = (VBox) card.getChildren().get(0);
            HBox titleBox = (HBox) mainContainer.getChildren().get(0);

            VBox meta = (VBox) titleBox.getChildren().get(0);


            Label title = (Label) meta.getChildren().get(0);
            title.setText(this.match.getMatchName());

            Label time = (Label) meta.getChildren().get(1);
            time.setText(this.match.getTime());

            Label location = (Label) meta.getChildren().get(2);
            location.setText(this.match.getAddress());

            Label temperature = (Label) titleBox.getChildren().get(1);
            String temperature_value = Math.round(weatherCond.getTemp()) + "˚";
            temperature.setText(temperature_value);

            return card;
        }
    }

    private LinkedList<WatchCard> watchCards = new LinkedList<>();
    @FXML private VBox watchCardBox;

    public void setUp(Stage mainStage, Scene homeView, Scene favView) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
    }

    @FXML
    protected void switchToFav() {
        mainStage.setScene(favView);
        mainStage.show();
    }

    @FXML
    public void switchTo() {
        mainStage.setScene(homeView);
        watchCardBox.getChildren().clear();
        try {
            for (WatchCard card : watchCards) {
                watchCardBox.getChildren().add(card.getCard());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        mainStage.show();
    }

    @FXML
    protected void openNewWatch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-watch-view.fxml"));
        Scene newWatch = new Scene(fxmlLoader.load());
        NewWatchController newWatchController = fxmlLoader.getController();

        newWatchController.setUp(mainStage, this);
        mainStage.setScene(newWatch);
        mainStage.show();

        // newWatchController.setUp(dialogStage, this);
        // dialogStage.setScene(newWatch);
        // #dialogStage.show();
    }

    protected void addNewCard(Match match) throws IOException {
        watchCards.add(new WatchCard(match));
    }
}
