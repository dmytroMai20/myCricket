package uk.ac.cam.group09.mycricket.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.CricketApp;

import java.io.IOException;

public class HomeController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;

    private Stage dialogStage;

    private Parent root;

    @FXML private Label welcomeText;
    @FXML private VBox watchCardBox;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");

    }

    public void setUp(Stage mainStage, Scene homeView, Scene favView, Stage dialogStage) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.dialogStage = dialogStage;

        this.root = homeView.getRoot();
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
        newWatchController.setUp(dialogStage);

        dialogStage.setScene(newWatch);
        dialogStage.show();
    }

    @FXML
    protected void addNewBox() {
        // load a Node object representing a card
        // fill the card with info
        // add the card to the cardBox
        watchCardBox.getChildren().addAll(new Label("Names:"));
    }
}