package uk.ac.cam.group09.mycricket;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Favourite {

    private final String favName;
    private WeatherConditions weather;
    private final HashMap<String,String> locationInfo;

    public Favourite(HashMap<String,String> locationInfo, String favName) {
        this.locationInfo = locationInfo;
        this.favName = favName;
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), LocalDateTime.now());
    }

    public String getFavName() {
        return favName;
    }

    private void updateWeather() {
        this.weather = Weather.getWeather(locationInfo.get("Longitude"),
                locationInfo.get("Latitude"), LocalDateTime.now());
    }

    public WeatherConditions getWeather() {
        updateWeather();
        return weather;
    }
}
