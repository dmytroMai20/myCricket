package uk.ac.cam.group09.mycricket;

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
}