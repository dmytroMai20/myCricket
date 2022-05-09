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
        //TODO logic to decide if the conditions are playable
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
