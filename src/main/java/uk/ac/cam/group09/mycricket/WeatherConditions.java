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
        */

        if (24 <= temp && temp < 28) {
            if (humidity > 0.7) {

            }
        } else if (28 <= temp && temp < 31) {
            if (humidity > 0.6) {

            }
        } else if (31 <= temp && temp < 35) {
            if (humidity > 0.5) {

            }
        } else if (35 <= temp && temp < 37) {
            if (humidity > 0.3) {

            }
        } else if (37 <= temp) {
            if (humidity > 0.3) {
                return false;
            }
        }


        /* Rainfall */
        if (conditions.contains("Rain")) {
            return false;
        }
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
