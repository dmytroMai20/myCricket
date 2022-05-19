package uk.ac.cam.group09.mycricket;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Weather {

    public static WeatherConditions getWeather(String longitude, String latitude, LocalDateTime dateTime){
        //String handling to get the data in the required form.
        String latAndLong = longitude + "%2C" + latitude;
        String month = Integer.toString(dateTime.getMonthValue());
        if (month.length() == 1){
            month = "0" + month;
        }
        String day = "" + dateTime.getDayOfMonth();
        if (day.length() == 1){
            day = "0" + day;
        }
        String date = dateTime.getYear() + "-" + month +"-"+ day;
        String hour = "" + dateTime.getHour();
        if (hour.length() == 1){
            hour = "0" + hour;
        }

        //Requesting data from the API handler, creating a new weather
        // conditions object with this data.
        HashMap<String, HashMap<String, Object>> data =WeatherApiHandler.getData(latAndLong, date);
        String dateTimeHours = hour + ":00:00";
        HashMap<String, Object> resultConditions = data.get(dateTimeHours);
        return new WeatherConditions(resultConditions);
    }
}
