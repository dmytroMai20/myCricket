package uk.ac.cam.group09.mycricket;

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
        /*
        Heat illness risk
        | Temp  | humidity |   risk   | message
        |-------|----------|----------|------------------------
        | 24-28 |  > 70%   |   low    | exercise caution
        | 28-31 |  > 60%   | moderate | increase vigilance and monitor players. consider increasing drink breaks
        | 31-35 |  > 50%   |   high   | uncomfortable for most. increase drink breaks, batters esp. be wary
        | 35-37 |  > 30%   | v. high  | consider reducing overs or delaying game
        |  37+  |  > 30%   |  extreme | players should leave the field

        UV
        | uvindex | severrisk | message

        Rainfall
        | precip | message

        Visibility
        | visibility | message

        Wind
        | windgust | windspeed | message

        Conditions: (if snowy etc. then...)
         */
        return true;
    }
    public void setData(HashMap<String,Object> data){
        //TODO assign all the class variables the hashmap values
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
