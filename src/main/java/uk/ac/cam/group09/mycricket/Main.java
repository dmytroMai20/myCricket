package uk.ac.cam.group09.mycricket;

public class Main {
    public static void exampleRetrieveWeather(){    //example method to see how to retrieve certain conditions at a certain time
        WeatherConditions weather = Weather.getWeather("london","15/05/2022", "12:00:00");
        weather.getTemp();  //returns temperature

    }
    /*
        https://www.visualcrossing.com/resources/documentation/weather-api/how-to-fetch-weather-forecast-data-from-a-restful-web-service-in-java/
        visualcrossing weather api tutorial for java

        when requesting weather you will receive a json response back containing records for each day which contains records for each hour
        thus class weatherConditions contains all the conditions for a specified hour of a day. This implementation could be changed.
     */
}
