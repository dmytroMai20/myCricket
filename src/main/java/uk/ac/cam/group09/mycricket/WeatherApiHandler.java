package uk.ac.cam.group09.mycricket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.*;


public class WeatherApiHandler {
    /*
        this class will handle the api requests and then parsing the json data.
     */
    private final static String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final static String unitGroup="metric"; //us,metric,uk
    private final static String apiKey="CLWNVGL8ZMZSCGUBCY2A6K7UY";
    private final static String include = "hours";
    private static URI buildReqString(String date, String location) throws URISyntaxException {
        StringBuilder requestBuilder=new StringBuilder(apiEndPoint);
        requestBuilder.append(location);
        requestBuilder.append("/").append(date);
        requestBuilder.append("/").append(date);
        URIBuilder builder = new URIBuilder(requestBuilder.toString());
        builder.setParameter("unitGroup", unitGroup)
                .setParameter("include", include)
                .setParameter("key", apiKey);
        return builder.build();
    }
    private static String getJSON(URI uri) throws IOException {
        HttpGet get = new HttpGet(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(get);
        String rawResult=null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n",
                        response.getStatusLine().getStatusCode());
                return "Bad response status code:%d%n" + response.getStatusLine().getStatusCode();
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult= EntityUtils.toString(entity, Charset.forName("utf-8"));
            }

        } finally {
            response.close();
        }
        return rawResult;
    }
    public static HashMap<String,HashMap<String, Object>> getData(String location, String dateTime){
        //TODO retrieve fields required for WeatherConditions class and return it as a hashmap of date time and conditions at that date(hashmap of condition names and values) i.e - "12:00:00": ("temp":11.1,"precip":21...),...
        //https://www.visualcrossing.com/weather/weather-data-services/London?v=api# api request and json response example
        //make sure to select the box "hourly"
        HashMap<String, HashMap<String, Object>> res = new HashMap<String,HashMap<String, Object>>();
        String Json = "";
        URI uri = null;
        try {
            uri = buildReqString(dateTime, location);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            Json = getJSON(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject timelineResponse = new JSONObject(Json);
        JSONArray values=timelineResponse.getJSONArray("days");
        JSONObject day = values.getJSONObject(0);
        JSONArray hours = day.getJSONArray("hours");
        for (int i = 0; i < hours.length();i++){
            JSONObject hour = hours.getJSONObject(i);
            HashMap<String,Object> hourlyCond = new HashMap<String,Object>();

            hourlyCond.put("datetime",hour.getString("datetime"));
            String[] keys = new String[] {
                    "temp",
                    "precip",
                    "precipprob",
                    "humidity",
                    "snow",
                    "windgust",
                    "windspeed",
                    "visibility",
                    "uvindex",
                    "severerisk",
                    "conditions"
            };
            for (String key : keys) {
                try {
                    hourlyCond.put(key,hour.getFloat(key));
                }
                catch (Exception e) {
                    System.out.println("Exception occured when processing" + key);
                    hourlyCond.put(key,0.0f);
                }
            }

            res.put(hour.getString("datetime"), hourlyCond);
        }

        return res;
    }
    public static void main(String[] args) {
        //Example forecast for tomorrow 12:00:00
        LocalDateTime date = LocalDateTime.now();
        date = LocalDateTime.of(2022,5,14,12,0,0);
        WeatherConditions weatherConds = Weather.getWeather("0.1276","51.5072",date);
        System.out.println(weatherConds.getTemp());
        System.out.println(weatherConds.goodToPlay());


    }
}
