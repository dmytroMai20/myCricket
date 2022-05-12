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

    public WeatherConditions(HashMap<String,Object> data){ //constructor
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

    public Risk goodToPlay(){
        /*
        Heat illness risk
        | Temp  | humidity |   risk   | message
        |-------|----------|----------|------------------------
        | 24-28 |  > 70%   |   low    | exercise caution
        | 28-31 |  > 60%   | moderate | increase vigilance and monitor players. consider increasing drink breaks
        | 31-35 |  > 50%   |   high   | uncomfortable for most. increase drink breaks, batters be wary
        | 35-37 |  > 30%   | v. high  | consider reducing overs or delaying game
        |  37+  |  > 30%   |  extreme | players should leave the field
        */

        if (24 <= temp && temp < 28) {
            if (humidity > 0.7) {
                return new Risk(Risk.RiskLevel.LOW, "exercise caution");
            }
        } else if (28 <= temp && temp < 31) {
            if (humidity > 0.6) {
                return new Risk(Risk.RiskLevel.MEDIUM, "increase vigilance and monitor players. consider increasing drink breaks");
            }
        } else if (31 <= temp && temp < 35) {
            if (humidity > 0.5) {
                return new Risk(Risk.RiskLevel.HIGH, "uncomfortable for most. increase drink breaks, batters be wary");
            }
        } else if (35 <= temp && temp < 37) {
            if (humidity > 0.3) {
                return new Risk(Risk.RiskLevel.HIGH, "consider reducing overs or delaying game");
            }
        } else if (37 <= temp) {
            if (humidity > 0.3) {
                return new Risk(Risk.RiskLevel.EXTREME, "players should leave the field");
            }
        }

        /* Rainfall */
        /*
        Light rain — precipitation rate is between 0.5 mm and 2.5 mm (0.098 in) per hour
        Moderate rain — precipitation rate is between 2.5 mm (0.098 in) – 10 mm (0.39 in) per hour
        Heavy rain — when the precipitation rate is greater than 10 mm (0.39 in) per hour
         */
        if (0.5 <= precip && precip < 2.5) {
            return new Risk(Risk.RiskLevel.LOW, "consider delays until rain stops");
        } else if (2.5 <= precip && precip < 10) {
            return new Risk(Risk.RiskLevel.MEDIUM, "delay game until conditions improve");
        } else if (10 <= precip) {
            return new Risk(Risk.RiskLevel.HIGH, "postpone game due to excessive rainfall");
        }

        return new Risk(Risk.RiskLevel.NONE);

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
