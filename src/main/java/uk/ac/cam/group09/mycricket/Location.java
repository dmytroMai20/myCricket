package uk.ac.cam.group09.mycricket;

import java.util.HashMap;

public class Location{
    private HashMap<String,String> locationInfo;
    public WeatherConditions weather;

    public String getAddress(){
        return locationInfo.get("Address");
    }

    public String getCity(){
        return locationInfo.get("City");
    }
}