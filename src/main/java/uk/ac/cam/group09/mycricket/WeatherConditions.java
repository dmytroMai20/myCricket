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

        Risk maxRisk = new Risk(Risk.RiskLevel.NONE, "Good playing conditions");

        if (24 <= temp && temp < 28) {
            if (humidity > 0.7) {
                Risk risk = new Risk(Risk.RiskLevel.LOW, "exercise caution");
                if (risk.compareTo(maxRisk) == 1) {
                    maxRisk = risk;
                }
            }
        } else if (28 <= temp && temp < 31) {
            if (humidity > 0.6) {
                Risk risk = new Risk(Risk.RiskLevel.MEDIUM, "increase vigilance and monitor players. consider increasing drink breaks");
                if (risk.compareTo(maxRisk) == 1) {
                    maxRisk = risk;
                }
            }
        } else if (31 <= temp && temp < 35) {
            if (humidity > 0.5) {
                Risk risk = new Risk(Risk.RiskLevel.HIGH, "uncomfortable for most. increase drink breaks, batters be wary");
                if (risk.compareTo(maxRisk) == 1) {
                    maxRisk = risk;
                }
            }
        } else if (35 <= temp && temp < 37) {
            if (humidity > 0.3) {
                Risk risk = new Risk(Risk.RiskLevel.HIGH, "consider reducing overs or delaying game");
                if (risk.compareTo(maxRisk) == 1) {
                    maxRisk = risk;
                }
            }
        } else if (37 <= temp) {
            if (humidity > 0.3) {
                Risk risk = new Risk(Risk.RiskLevel.EXTREME, "players should leave the field");
                if (risk.compareTo(maxRisk) == 1) {
                    maxRisk = risk;
                }
            }
        }

        /* Rainfall */
        /*
        Light rain — precipitation rate is between 0.5 mm and 2.5 mm (0.098 in) per hour
        Moderate rain — precipitation rate is between 2.5 mm (0.098 in) – 10 mm (0.39 in) per hour
        Heavy rain — when the precipitation rate is greater than 10 mm (0.39 in) per hour
         */
        if (0.5 <= precip && precip < 2.5) {
            Risk risk = new Risk(Risk.RiskLevel.LOW, "consider delays until rain stops");
            if (risk.compareTo(maxRisk) == 1) {
                maxRisk = risk;
            }
        } else if (2.5 <= precip && precip < 10) {
            Risk risk = new Risk(Risk.RiskLevel.MEDIUM, "delay game until conditions improve");
            if (risk.compareTo(maxRisk) == 1) {
                maxRisk = risk;
            }
        } else if (10 <= precip) {
            Risk risk = return new Risk(Risk.RiskLevel.HIGH, "postpone game due to excessive rainfall");
            if (risk.compareTo(maxRisk) == 1) {
                maxRisk = risk;
            }
        }

        // TODO: Check units that API returns for windspeed and windgust
        /* Wind
        Threat Level Descriptions (weather.gov)

        Extreme
        "An Extreme Threat to Life and Property from High Wind."
        "Damaging high wind" with sustained speeds greater than 58 mph, or frequent wind gusts greater than 58 mph. Damaging wind conditions are consistent with a high wind warning.

        High
        "A High Threat to Life and Property from High Wind."
        "High wind" with sustained speeds of 40 to 57 mph. Wind conditions consistent with a high wind warning.

        Moderate
        "A Moderate Threat to Life and Property from High Wind."
        "Very windy" with sustained speeds of 26 to 39 mph, or frequent wind gusts of 35 to 57 mph. Wind conditions consistent with a wind advisory.

        Low
        "A Low Threat to Life and Property from High Wind."
        "Windy" conditions. Sustained wind speeds of 21 to 25 mph, or frequent wind gusts of 30 to 35 mph.

        Very Low
        " A Very Low Threat to Life and Property from High Wind."
        "Breezy" to "Windy" conditions. Sustained wind speeds around 20 mph, or frequent gusts of 25 to 30 mph.
         */

//        if (windspeed >= 58 || windgust > 58) {
//            return new Risk(Risk.RiskLevel.EXTREME, "extreme threat to life and property from high wind.");
//        } else if (windspeed  >= 40) {
//            return new Risk(Risk.RiskLevel.HIGH, "high threat to life and property from high wind.");
//        } else if (windspeed  >= 26 || windgust > 35) {
//            return new Risk(Risk.RiskLevel.MEDIUM, "very windy. consider delaying game");
//        } else if (windspeed  >= 21 || windgust > 30) {
//            return new Risk(Risk.RiskLevel.LOW, "windy. exercise caution");
//        }

        return maxRisk;
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

    public float getPrecipProb() {
        return precipprob;
    }

    public float getWindGust() {
        return windgust;
    }

    public float getWindSpeed() {
        return windspeed;
    }

    public float getUvIndex() {
        return uvindex;
    }

    // TODO: this method somehow returns null
    public String getConditions() {
        return conditions;
    }
}
