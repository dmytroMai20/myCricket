package uk.ac.cam.group09.mycricket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button submitButton = new Button("Print current lat and long to " +
                "console");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                getLongLatFromMap(map);
            }
        });
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(map);
        stackPane.getChildren().add(submitButton);
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
                "var map = L.map('map').setView([0, 0], 0);\n" +
                "L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {\n" +
                "attribution: '&copy; <a href=\"http://osm" +
                ".org/copyright\">OpenStreetMap</a> contributors'\n" +
                "}).addTo(map);\n" +
                "\n" +
                "let currentMarker = new L.Marker([0, 0]);\n" +
                "var geocoder = L.Control.geocoder()\n" +
                "                .on('markgeocode', function(event) {\n" +
                "                    var center = event.geocode.center;\n" +
                "\t\t    currentMarker = L.marker(center);\n" +
                "  \t\t    currentMarker.addTo(map);\n" +
                "                    map.setView(center, map.getZoom());\n" +
                "                })\n" +
                "                .addTo(map);\n" +
                "\n" +
                "function returnLongAndLat(){\n" +
                "\tvar longitude = currentMarker.getLatLng().lng;\n" +
                "        var latitude = currentMarker.getLatLng().lat;\n" +
                "        return longitude + \",\" + latitude\n" +
                "}\n" +
                "</script>\n" +
                "</html>";
        webview.getEngine().loadContent(html);
    }

    public void getLongLatFromMap(WebView map) {
        System.out.println(map.getEngine().executeScript("returnLongAndLat();"));
    }
    public static void main(String[] args) {
        launch();
    }
}