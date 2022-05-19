package uk.ac.cam.group09.mycricket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uk.ac.cam.group09.mycricket.controllers.FavController;
import uk.ac.cam.group09.mycricket.controllers.HomeController;

import java.io.IOException;

public class CricketApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CricketApp.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        FXMLLoader fxmlLoader2 = new FXMLLoader(CricketApp.class.getResource("fav-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());

        HomeController homeController = fxmlLoader.getController();
        homeController.setUp(stage, scene, scene2);

        FavController favController = fxmlLoader2.getController();
        favController.setUp(stage, scene, scene2);

        // initialize to home
        // TODO: set minimum size of the stage
        stage.setScene(scene);
        stage.setTitle("Cricket Weather App");
        stage.show();

        /*
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(stage);
        */
    }

    public static void main(String[] args) {
        launch();
    }
}