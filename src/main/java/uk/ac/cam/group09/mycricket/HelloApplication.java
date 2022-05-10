package uk.ac.cam.group09.mycricket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader =new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.show();
        WebView map = new WebView();
        setupMap (map);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(map);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
    }

    public void setupMap (WebView webview){
        String html = "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1" +
                ".8.0/dist/leaflet.css\"\n" +
                "   integrity=\"sha512-hoalWLoI8r4UszCkZ5kL8vayOGVae1oxXe" +
                "/2A4AO6J9+580uKHDO3JdHb7NzwwzK5xr/Fs0W40kiNHxM9vyTtQ==\"\n" +
                "   crossorigin=\"\"/>\n" +
                "<script src=\"https://unpkg.com/leaflet@1.8.0/dist/leaflet" +
                ".js\"\n" +
                "   integrity=\"sha512-BB3hKbKWOc9Ez" +
                "/TAwyWxNXeoV9c1v6FIeYiBieIWkpLjauysF18NzgR1MBNBXf8" +
                "/KABdlkX68nAhlwcDFLGPCQ==\"\n" +
                "   crossorigin=\"\"></script>\n" +
                "\n" +
                "<link rel=\"stylesheet\" href=\"https://unpkg" +
                ".com/leaflet-control-geocoder/dist/Control.Geocoder.css\" " +
                "/>\n" +
                "<script src=\"https://unpkg" +
                ".com/leaflet-control-geocoder/dist/Control.Geocoder" +
                ".js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<div id=\"map\" style = \"height: 180px\"></div>\n" +
                "<script>\n" +
                "var map = L.map('map').setView([51.505, -0.09], 13);\n" +
                "L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {\n" +
                "attribution: '&copy; <a href=\"http://osm" +
                ".org/copyright\">OpenStreetMap</a> contributors'\n" +
                "}).addTo(map);\n" +
                "L.Control.geocoder().addTo(map);\n" +
                "</script>\n" +
                "\n" +
                "</html>";
        webview.getEngine().loadContent(html);
    }

    public static void main(String[] args) {
        launch();
    }
}