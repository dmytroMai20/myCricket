package uk.ac.cam.group09.mycricket.services;

import java.util.HashMap;

public class WeatherConditions {
    private String datetime;
    private float temp,humidity;
    private float precip,precipprob;
    private float snow;
    private float windgust,windspeed;
    private float visibility;
    private float uvindex,severerisk;
    private String conditions;
    public WeatherConditions(String dateTime){ //constructor
        datetime = dateTime;
    }
    public boolean goodToPlay(){
        //TODO logic to decide if the conditions are playable
        return true;
    }
    public void setData(HashMap<String,Object> data){
        //TODO assign all the class variables the hashmap values
        datetime = data.get("datetime").toString();
        temp = (float)data.get("temp");
        humidity = (float)data.get("humidity");
        precip = (float)data.get("precip");
        precipprob = (float)data.get("precipprob");
        snow = (float)data.get("snow");
        windgust = (float)data.get("windgust");
        windspeed = (float)data.get("windspeed");
        visibility = (float)data.get("visibility");
        uvindex = (float)data.get("uvindex");
        severerisk = (float)data.get("severerisk");
    }
    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPrecip() {
        return precip;
    }

    public float getPrecipprob() {
        return precipprob;
    }

    public float getWindgust() {
        return windgust;
    }

    public float getWindspeed() {
        return windspeed;
    }

    public float getUvindex() {
        return uvindex;
    }

    public String getConditions() {
        return conditions;
    }
}
