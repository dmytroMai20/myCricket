package uk.ac.cam.group09.mycricket;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.HashMap;

/** This is a wrapper class designed to hand over properties to the controllers */
public class CardManager {
    private Match match;
    private SimpleStringProperty title, time, location, temperature, humidity, precip, precipProb, windGust, windSpeed, uvIndex, riskMessage;
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
        this.humidity = new SimpleStringProperty(Math.round(weather.getHumidity()) + "%");
        this.precip = new SimpleStringProperty(Float.toString(weather.getPrecip()));
        this.precipProb = new SimpleStringProperty(Math.round(weather.getPrecipProb()) + "%");
        this.windGust = new SimpleStringProperty(Float.toString(weather.getWindGust()));
        this.windSpeed = new SimpleStringProperty(Float.toString(weather.getWindSpeed()));
        this.uvIndex = new SimpleStringProperty(Float.toString(weather.getUvIndex()));
        this.riskMessage = new SimpleStringProperty(weather.goodToPlay().getMessage());

        this.riskLevel = new SimpleIntegerProperty(0);
        Risk.RiskLevel matchRisk = weather.goodToPlay().getRisk();
        switch (matchRisk){
            case NONE: this.riskLevel.set(0);
            break;
            case LOW:
            case MEDIUM:
                this.riskLevel.set(1);
            break;
            case HIGH:
            case EXTREME:
                this.riskLevel.set(2);
                break;
        }
    }

    public void refresh() {
        time.set(match.getTime());

        WeatherConditions weather = match.getWeather();
        temperature.set(Math.round(weather.getTemp()) + "˚");
        // TODO: refresh the rest of the crew
        humidity.set(Math.round(weather.getHumidity()) + "%");
        precip.set(Float.toString(weather.getPrecip()));
        precipProb.set(Math.round(weather.getPrecipProb()) + "%");
        windGust.set(Float.toString(weather.getWindGust()));
        windSpeed.set(Float.toString(weather.getWindSpeed()));
        uvIndex.set(Float.toString(weather.getUvIndex()));
        riskMessage.set(weather.goodToPlay().getMessage());

        Risk.RiskLevel matchRisk = weather.goodToPlay().getRisk();
        switch (matchRisk){
            case NONE: this.riskLevel.set(0);
                break;
            case LOW:
            case MEDIUM:
                this.riskLevel.set(1);
                break;
            case HIGH:
            case EXTREME:
                this.riskLevel.set(2);
                break;
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
    public StringProperty getHumidity() {return humidity;}
    public StringProperty getPrecip() {return precip;}
    public StringProperty getPrecipProb() {return precipProb;}
    public StringProperty getWindGust() {return windGust;}
    public StringProperty getWindSpeed() {return windSpeed;}
    public StringProperty getUvIndex() {return uvIndex;}
    public StringProperty getRiskMessage() {return riskMessage;}
    public IntegerProperty getRiskLevel() {return riskLevel;}
}
