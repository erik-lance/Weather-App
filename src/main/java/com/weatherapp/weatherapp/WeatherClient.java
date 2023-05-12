package com.weatherapp.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class WeatherClient {
    private static final String API_KEY = "";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public WeatherClient() {
        System.out.println("WeatherClient loaded");
    }

    public String getWeatherDataURL(String location) {
        // TODO: Parse spaces in location
        // Location: Mandaluyong, PH
        return API_URL + location + "&appid=" + API_KEY + "&units=metric";
    }

    public void getWeatherData(String location) {
        try {
            URL url = new URL(getWeatherDataURL(location));

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
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

                System.out.println("\nJSON data in string format");

                JSONParser parser = new JSONParser();
                JSONArray data = (JSONArray) parser.parse(String.valueOf(infoString));

                System.out.println(data);

            }

        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
