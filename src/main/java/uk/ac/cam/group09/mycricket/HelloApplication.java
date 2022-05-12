package uk.ac.cam.group09.mycricket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // The aspect ratio of iPhone 13 screen is 9:19.5
        Scene scene = new Scene(fxmlLoader.load(), 90*3, 195*3);
        stage.setTitle("MyCricket");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}