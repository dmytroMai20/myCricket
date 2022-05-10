package uk.ac.cam.group09.mycricket;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Match{
    public WeatherConditions weather;
    private LocalDateTime dateTime;
    private HashMap<String,String> locationInfo;

    public String getCity(){
        return locationInfo.get("City");
    }

    public String getAddress(){
        return locationInfo.get("Address");
    }

    public String getHour(){
        return dateTime.getHour() + ":00";
    }

    public String getDay(){
        return dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() +
                "/" + dateTime.getYear();
    }

}
