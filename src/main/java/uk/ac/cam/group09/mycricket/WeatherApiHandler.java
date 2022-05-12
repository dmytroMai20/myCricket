package uk.ac.cam.group09.mycricket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WeatherApiHandler {
    /*
        this class will handle the api requests and then parsing the json data.
     */
    private static String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private static String location="";
    private static String startDate=null; //optional (omit for forecast)
    private static String endDate=null; //optional (requires a startDate if present)
    private static String unitGroup="metric"; //us,metric,uk
    private static String apiKey="YOUR_API_KEY";
    private static URI buildReqString() throws URISyntaxException {
        StringBuilder requestBuilder=new StringBuilder(apiEndPoint);
        requestBuilder.append(location);

        if (startDate!=null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate!=null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }
        URIBuilder builder = new URIBuilder(requestBuilder.toString());
        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);
        return builder.build();
        //if ("GET".equals(method)) {
        //    requestBuilder.append("?").append(paramBuilder);
        //}
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
        return null;
    }
}
