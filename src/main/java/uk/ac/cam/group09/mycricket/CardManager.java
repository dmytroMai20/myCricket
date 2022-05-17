package uk.ac.cam.group09.mycricket;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDateTime;
import java.util.HashMap;

/** This is a wrapper class designed to hand over properties to the controllers */
public class CardManager {
    private Match match;
    private SimpleStringProperty title, time, location, temperature, humidity, riskMessage;
    private SimpleIntegerProperty riskLevel;

    /** Constructor for a new watch. */
    public CardManager(HashMap<String,String> locationInfo, String matchName, LocalDateTime dateTime) {
        this.match = new Match(locationInfo, matchName, dateTime);
        initialize();
    }

    /** Constructor for a new fav. */
    public CardManager(HashMap<String,String> locationInfo, String matchName) {
        this.match = new Match(locationInfo, matchName);
        initialize();
    }

    private void initialize() {
        this.title = new SimpleStringProperty(match.getMatchName());
        this.time = new SimpleStringProperty(match.getTime());
        this.location = new SimpleStringProperty(match.getAddress());

        WeatherConditions weather = match.getWeather();
        this.temperature = new SimpleStringProperty(Math.round(weather.getTemp()) + "˚");

        // TODO: humidity, riskMessage
        this.riskMessage = new SimpleStringProperty(weather.goodToPlay().getMessage());


        this.riskLevel = new SimpleIntegerProperty(0);
        Risk.RiskLevel matchRisk = weather.goodToPlay().getRisk();
        switch (matchRisk){
            case NONE: this.riskLevel.set(0);
            case LOW: this.riskLevel.set(1);
            case MEDIUM: this.riskLevel.set(1);
            case HIGH: this.riskLevel.set(2);
            case EXTREME: this.riskLevel.set(2);
        }
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
    public StringProperty getRiskMessage() {return riskMessage;}
    public IntegerProperty getRiskLevel() {return riskLevel;}


    public void refresh() {
        match.update();
        time.set(match.getTime());

        WeatherConditions weather = match.getWeather();
        temperature.set(Math.round(weather.getTemp()) + "˚");

        riskMessage.set(weather.goodToPlay().getMessage());

        Risk.RiskLevel matchRisk = weather.goodToPlay().getRisk();
        switch (matchRisk){
            case NONE: this.riskLevel.set(0);
            case LOW: this.riskLevel.set(1);
            case MEDIUM: this.riskLevel.set(1);
            case HIGH: this.riskLevel.set(2);
            case EXTREME: this.riskLevel.set(2);
        }

        // TODO: refresh the rest of the crew

    }
}
