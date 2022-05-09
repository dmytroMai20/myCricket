import java.util.HashMap;

public class Weather {
    public static String getWeather(String location, String dateTime){
        return null;
    }
    public static WeatherConditions getWeather(String location, String dateTime, String dateTimeHours){
        //TODO
        WeatherConditions result;
        HashMap<String, HashMap<String, Object>> data = WeatherApiHandler.getData(location, dateTime);
        HashMap<String, Object> resultConditions = data.get(dateTimeHours);
        return null;
    }
}
