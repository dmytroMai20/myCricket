package uk.ac.cam.group09.mycricket;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Match{
    public WeatherConditions weather;
    private LocalDateTime dateTime;
    private HashMap<String,String> locationInfo;
    private String matchName;

    public StringProperty temperature = new SimpleStringProperty();

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
        return dateTime.toString();
    }

    public String getMatchName(){
        return matchName;
    }

    public Match(HashMap<String,String> locationInfo, String matchName,
                 LocalDateTime dateTime){
        this.locationInfo = locationInfo;
        this.matchName = matchName;
        this.dateTime = dateTime;
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), dateTime);

        this.temperature.set(Integer.toString(Math.round(this.weather.getTemp())) + '˚');
    }

    public void update() {
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), dateTime);
        this.temperature.set(Integer.toString(Math.round(this.weather.getTemp())) + '˚');
    }

}
