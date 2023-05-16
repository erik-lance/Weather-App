package com.weatherapp.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class WeatherClient {
    private static String API_KEY = "";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public WeatherClient() {
        System.out.println("WeatherClient loaded");
    }

    public String getWeatherDataURL(String location) {
        String parsedLocation = location.replace(" ", "_");
        return API_URL + parsedLocation + "&appid=" + API_KEY + "&units=metric";
    }

    public JSONObject getJSONDataOfWeatherData(String location) {
        try {
            URL url = new URL(getWeatherDataURL(location));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode + "on URL: " + url);
            }
            else {
                System.out.println("Connection successful");

                StringBuilder infoString = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                // Read the contents of the URL by line if applicable
                // OpenWeather API already returns a single line of JSON data
                while (sc.hasNext()) {
                    infoString.append(sc.nextLine());
                }

                sc.close();

                JSONParser parser = new JSONParser();
                JSONObject data = (JSONObject) parser.parse(infoString.toString());

                if (data == null) {
                    throw new RuntimeException("Data failed to parse");
                }

                return data;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WeatherData parseWeatherJSON(JSONObject data) {
        try {
            String city = data.get("name").toString();
            String country = ((JSONObject) data.get("sys")).get("country").toString();
            String description = ((JSONObject) ((JSONArray) data.get("weather")).get(0)).get("description").toString();
            String temperature = ((JSONObject) data.get("main")).get("temp").toString();
            String humidity = ((JSONObject) data.get("main")).get("humidity").toString();
            String pressure = ((JSONObject) data.get("main")).get("pressure").toString();
            String windSpeed = ((JSONObject) data.get("wind")).get("speed").toString();
            String windDirection = ((JSONObject) data.get("wind")).get("deg").toString();

            WeatherData weatherData = new WeatherData(city, country, description,
                    temperature, humidity, pressure, windSpeed, windDirection);

            if (!weatherData.city().equals(city)) { throw new RuntimeException("City mismatch"); }
            if (!weatherData.country().equals(country)) { throw new RuntimeException("Country mismatch"); }
            if (!weatherData.description().equals(description)) { throw new RuntimeException("Description mismatch"); }
            if (!weatherData.temperature().equals(temperature)) { throw new RuntimeException("Temperature mismatch"); }
            if (!weatherData.humidity().equals(humidity)) { throw new RuntimeException("Humidity mismatch"); }
            if (!weatherData.pressure().equals(pressure)) { throw new RuntimeException("Pressure mismatch"); }
            if (!weatherData.windSpeed().equals(windSpeed)) { throw new RuntimeException("Wind speed mismatch"); }
            if (!weatherData.windDirection().equals(windDirection)) { throw new RuntimeException("Wind direction mismatch"); }

            return weatherData;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public WeatherData getWeatherData(String location) {
        try {
                JSONObject data = getJSONDataOfWeatherData(location);

                if (data == null) {
                    throw new RuntimeException("No data found");
                }

                WeatherData weatherData = parseWeatherJSON(data);

                if (weatherData == null) {
                    throw new RuntimeException("WeatherData failed to parse");
                }

                return weatherData;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void setAPIKey(String key) {
        API_KEY = key;
    }

}
