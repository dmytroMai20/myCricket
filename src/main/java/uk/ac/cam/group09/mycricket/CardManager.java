package uk.ac.cam.group09.mycricket;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.util.HashMap;

/** This is a wrapper class designed to hand over properties to the controllers */
public class CardManager {
    private Match match;
    private SimpleStringProperty title;
    private SimpleStringProperty time;
    private SimpleStringProperty location;
    private SimpleStringProperty temperature;

    // Case where it is called by a new watch
    public CardManager(HashMap<String,String> locationInfo, String matchName, LocalDateTime dateTime) {
        this.match = new Match(locationInfo, matchName, dateTime);

        this.title = new SimpleStringProperty(match.getMatchName());
        this.time = new SimpleStringProperty(match.getTime());
        this.location = new SimpleStringProperty(match.getAddress());
        this.temperature = new SimpleStringProperty(Math.round(match.getWeather().getTemp()) + "˚");
    }

    // Case where it is called by a new fav
    public CardManager(HashMap<String,String> locationInfo, String matchName) {
        this.match = new Match(locationInfo, matchName);

        this.title = new SimpleStringProperty(match.getMatchName());
        this.time = new SimpleStringProperty(match.getTime());
        this.location = new SimpleStringProperty(match.getAddress());
        this.temperature = new SimpleStringProperty(Math.round(match.getWeather().getTemp()) + "˚");
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getTime() {
        return time;
    }

    public StringProperty getLocation() {
        return location;
    }

    public StringProperty getTemperature() {
        return temperature;
    }

    public void refresh() {
        match.update();
        time.set(match.getTime());
        temperature.set(Math.round(match.getWeather().getTemp()) + "˚");
    }
}
