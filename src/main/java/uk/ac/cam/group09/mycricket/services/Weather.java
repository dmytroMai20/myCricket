package uk.ac.cam.group09.mycricket.services;

import java.time.LocalDateTime;

public class Weather {
    public static String getWeather(String location, LocalDateTime dateTime){
        return null;
    }
    public static WeatherConditions getWeather(String longitude,
                                               String latitude,
                                               LocalDateTime dateTime){
        //TODO
        WeatherConditions result;
        //HashMap<String, HashMap<String, Object>> data =WeatherApiHandler.getData(location, dateTime);
        //HashMap<String, Object> resultConditions = data.get(dateTimeHours);
        return null;
    }
}
