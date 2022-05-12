package uk.ac.cam.group09.mycricket.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class NewWatchController {

    private Stage dialogStage;
    private HomeController homeController;

    @FXML TextField titleTextField;
    @FXML DatePicker datePicker;
    @FXML ChoiceBox hourChoiceBox;
    @FXML ChoiceBox minChoiceBox;
    @FXML WebView webView;
    @FXML Label promptLabel;

    public void setUp(Stage dialogStage, HomeController homeController) {
        this.dialogStage = dialogStage;
        this.homeController = homeController;
        initialize();
    }

    protected void initialize() {
        // setting up the ChoiceBox options
        ObservableList<String> hours = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        hourChoiceBox.setItems(hours);

        ObservableList<String> minutes = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55");
        minChoiceBox.setItems(minutes);

        // setting up the map
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
                "<div id=\"map\" style = \"height: 410px\"></div>\n" +
                "<script>\n" +
                "var map = L.map('map').setView([0, 0], 0);\n" +
                "L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {\n" +
                "attribution: '&copy; <a href=\"http://osm" +
                ".org/copyright\">OpenStreetMap</a> contributors'\n" +
                "}).addTo(map);\n" +
                "\n" +
                "let currentMarker = new L.Marker([0, 0]);\n" +
                "var popUpText = \"\";\n" +
                "var popUpName = \"\";\n" +
                "var geocoder = L.Control.geocoder()\n" +
                "                .on('markgeocode', function(event) {\n" +
                "                    var center = event.geocode.center;\n" +
                "\t\t    currentMarker = L.marker(center);\n" +
                "  \t\t    currentMarker.addTo(map);\n" +
                "                    map.setView(center, map.getZoom());\n" +
                "                    popUpText = event.geocode.html;\n" +
                "\t            popUpName = event.geocode.name;\n" +
                "                })\n" +
                "                .addTo(map);\n" +
                "\n" +
                "\n" +
                "function returnLongAndLat(){\n" +
                "\tvar longitude = currentMarker.getLatLng().lng;\n" +
                "        var latitude = currentMarker.getLatLng().lat;\n" +
                "        return longitude + \",\" + latitude\n" +
                "}\n" +
                "\n" +
                "function returnMarkerText(){\n" +
                "\treturn popUpText;\n" +
                "}\n" +
                "\n" +
                "function returnMarkerName(){\n" +
                "\treturn popUpName;\n" +
                "}\n" +
                "</script>\n" +
                "</html>";
        webView.getEngine().loadContent(html);
    }

    protected HashMap<String, String> getLocationInfo() {
        HashMap<String, String> locationInfo = new HashMap<>();
        String[] latAndLong = ((String) webView.getEngine().executeScript(
                "returnLongAndLat();")).split(",");
        locationInfo.put("Latitude",latAndLong[0]);
        locationInfo.put("Longitude",latAndLong[1]);
        String markerText = (String) webView.getEngine().executeScript(
                "returnMarkerText();");
        String [] splitMarkerText = markerText.split(
                "<span class=\"\">|</span>|<br/>|<span " +
                        "class=\"leaflet-control-geocoder-address-context" +
                        "\">|<span class=\"leaflet-control-geocoder" +
                        "-address-detail\">");
        String address = "";
        String address2 = "";
        for (int i = 0; i < splitMarkerText.length; i++) {
            if (!splitMarkerText[i].equals("")){
                address = address2;
                address2 =
                        address2 + ", " + splitMarkerText[i].strip().replaceAll("&#x27;","'");
            }
        }
        if (address.length() != 0){
            address = address.substring(2);
        }
        locationInfo.put("Address", address);
        String[] name = ((String) webView.getEngine().executeScript(
                "returnMarkerName();")).split(",");
        String country = name[name.length-1];
        locationInfo.put("Country",country.strip().replaceAll("&#x27;","'"));
        return locationInfo;
    }

    protected boolean checkCompletion() {
        String address = getLocationInfo().get("Address");
        LocalDate date = datePicker.getValue();
        String hour = (String) hourChoiceBox.getValue();
        String minute = (String) minChoiceBox.getValue();

        return (!address.isEmpty()) && (date != null) && (!hour.isEmpty()) && (!minute.isEmpty());
    }

    @FXML
    protected void add() throws IOException {
        if (checkCompletion()) {
            promptLabel.setText("");

            HashMap<String, String> locationInfo = getLocationInfo();
            String name = titleTextField.getText();

            if (name.isEmpty()) {
                name = locationInfo.get("Address").split(",")[0];
            }

            int hour = Integer.parseInt((String) hourChoiceBox.getValue());
            int minute = Integer.parseInt((String) minChoiceBox.getValue());
            LocalDateTime dateTime = datePicker.getValue().atTime(hour, minute);

            // TODO: hand the backroom the relevant info and receive the relevant info
            // TODO: hand the front stage the parsed info
            // homeController.addNewCard(info);
            homeController.addNewCard();

            close();
        } else {
            promptLabel.setText("Please fill in all the required fields.");
        }
    }

    @FXML
    protected void close() {
        dialogStage.close();
    }
}