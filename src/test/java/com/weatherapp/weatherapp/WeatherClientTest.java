package com.weatherapp.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.weatherapp.weatherapp.TestUtils.*;

class WeatherClientTest {

    WeatherClient client;

    @BeforeEach
    void setUp() {
        client = new WeatherClient();
    }

    @Test
    void parse_json_to_weather_data() {
        JSONObject data = MandaluyongWeatherJSON();

        System.out.println(data);

        WeatherData weatherData = client.parseWeatherJSON(data);

        if (weatherData == null) {
            throw new RuntimeException("WeatherData is null");
        }

        assertAll(
                () -> assertEquals(weatherData.city(), data.get("name")),
                () -> assertEquals(weatherData.country(), ((JSONObject) data.get("sys")).get("country")),
                () -> assertEquals(weatherData.description(), ((JSONObject) ((JSONArray) data.get("weather")).get(0)).get("description")),
                () -> assertEquals(weatherData.temperature(), ((JSONObject) data.get("main")).get("temp")),
                () -> assertEquals(weatherData.humidity(), ((JSONObject) data.get("main")).get("humidity")),
                () -> assertEquals(weatherData.pressure(), ((JSONObject) data.get("main")).get("pressure")),
                () -> assertEquals(weatherData.windSpeed(), ((JSONObject) data.get("wind")).get("speed")),
                () -> assertEquals(weatherData.windDirection(), ((JSONObject) data.get("wind")).get("deg"))
        );
    }

    @Test
    void getWeatherURL() {
        String location = "Mandaluyong, PH";
        String parsedURL = client.getWeatherDataURL(location);
        String expectedURL = "http://api.openweathermap.org/data/2.5/weather?q=Mandaluyong,_PH&appid=&units=metric";
        assertEquals(expectedURL, parsedURL);
    }

    @Test
    void getWeatherData() {
    }
}