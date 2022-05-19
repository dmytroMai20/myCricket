package uk.ac.cam.group09.mycricket;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Match {
    private WeatherConditions weather;
    private LocalDateTime dateTime;
    private HashMap<String,String> locationInfo;
    private String matchName;
    private Boolean isMatch = Boolean.TRUE;

    public String getCountry(){
        return locationInfo.get("Country");
    }

    public String getAddress(){
        return locationInfo.get("Address");
    }

    public String getHour(){
        return dateTime.getHour() + ":00";
    }

    public String getDay(){
        return dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() +
                "/" + dateTime.getYear();
    }

    public String getTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getMatchName(){
        return matchName;
    }

    /** Constructor for regular Match */
    public Match(HashMap<String,String> locationInfo, String matchName,
                 LocalDateTime dateTime){
        this.locationInfo = locationInfo;
        this.matchName = matchName;
        this.dateTime = dateTime;
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), dateTime);
    }

    /** Constructor for Favourite */
    public Match(HashMap<String,String> locationInfo, String matchName){
        this.isMatch = Boolean.FALSE;
        this.locationInfo = locationInfo;
        this.matchName = matchName;
        this.dateTime = LocalDateTime.now();
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), dateTime);
    }

    public WeatherConditions getWeather() {
        return weather;
    }

    public void update() {
        // if this is a Fav, update the dateTime to the current time
        if (!isMatch) {
            this.dateTime = LocalDateTime.now();
        }

        // weather update blocked for now
        /**
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), dateTime);
         */
    }
}
