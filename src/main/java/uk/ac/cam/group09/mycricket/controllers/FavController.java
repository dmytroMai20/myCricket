package uk.ac.cam.group09.mycricket.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.CricketApp;

import java.io.IOException;

public class FavController {
    private Stage mainStage;
    private Scene homeView;
    private Scene favView;
    private Stage dialogStage;

    public void setUp(Stage mainStage, Scene homeView, Scene favView, Stage dialogStage) {
        this.mainStage = mainStage;
        this.homeView = homeView;
        this.favView = favView;
        this.dialogStage = dialogStage;
    }

    @FXML
    protected void switchToHome() throws IOException {
        mainStage.setScene(homeView);
        mainStage.show();
    }

    @FXML
    protected void openNewFav() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("new-fav-view.fxml"));
        Scene newFav = new Scene(fxmlLoader.load());
        NewFavController newFavController = fxmlLoader.getController();
        newFavController.setUp(dialogStage);

        dialogStage.setScene(newFav);
        dialogStage.show();
    }

}