package uk.ac.cam.group09.mycricket.services;

import java.util.HashMap;

public class WeatherApiHandler {
    /*
        this class will handle the api requests and then parsing the json data.
     */
    public static HashMap<String,HashMap<String, Object>> getData(String location, String dateTime){
        //TODO retrieve fields required for WeatherConditions class and return it as a hashmap of date time and conditions at that date(hashmap of condition names and values) i.e - "12:00:00": ("temp":11.1,"precip":21...),...
        //https://www.visualcrossing.com/weather/weather-data-services/London?v=api# api request and json response example
        //make sure to select the box "hourly"
        return null;
    }
}
