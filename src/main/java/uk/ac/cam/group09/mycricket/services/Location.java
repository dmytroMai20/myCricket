package uk.ac.cam.group09.mycricket.services;

import uk.ac.cam.group09.mycricket.services.Weather;
import uk.ac.cam.group09.mycricket.services.WeatherConditions;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Location{
    private HashMap<String,String> locationInfo;
    private String locationName;
    public WeatherConditions weather;

    public String getAddress(){
        return locationInfo.get("Address");
    }

    public String getCountry(){
        return locationInfo.get("Country");
    }

    public String getLocationName(){
        return locationName;
    }

    public Location(HashMap<String,String> locationInfo, String locationName){
        this.locationInfo = locationInfo;
        this.locationName = locationName;
        LocalDateTime currentDate = java.time.LocalDateTime.now();
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), currentDate);

    }
}