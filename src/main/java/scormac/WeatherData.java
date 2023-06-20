package scormac;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class WeatherData {
    private String API;
    private final String API_KEY = "c264fde3cefb2270288bc14381a41ea9";

    private static HttpURLConnection connection = null;


    public WeatherData(String city) {
        API = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
    }


    public String getResponseContent() {
        String responseContent = null;

        try {
            URL url = new URL(API);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            responseContent = response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return responseContent;
    }


    public Weather getData() {
        JSONObject obj = new JSONObject(getResponseContent());

        String city = obj.getString("name");
        String country = obj.getJSONObject("sys").getString("country");
        double temp = obj.getJSONObject("main").getDouble("temp");
        double feelsLike = obj.getJSONObject("main").getDouble("feels_like");
        String desc = obj.getJSONArray("weather").getJSONObject(0).getString("description");
        double tempMin = obj.getJSONObject("main").getDouble("temp_min");
        double tempMax = obj.getJSONObject("main").getDouble("temp_max");
        int visibility = obj.getInt("visibility");
        String sunrise = convertTime(obj.getJSONObject("sys").getInt("sunrise"));
        String sunset = convertTime(obj.getJSONObject("sys").getInt("sunset"));

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setCountry(country);
        weather.setTemp(temp);
        weather.setFeelsLike(feelsLike);
        weather.setDescription(desc);
        weather.setTempMin(tempMin);
        weather.setTempMax(tempMax);
        weather.setVisibility(visibility);
        weather.setSunrise(sunrise);
        weather.setSunset(sunset);

        return weather;
    }

    private String convertTime(long unixTime) {
        Date date = new Date(unixTime * 1000);
        return date.getHours() + ":" + date.getMinutes();
    }


}
