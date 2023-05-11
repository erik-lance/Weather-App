package com.weatherapp.weatherapp;

public class WeatherClient {
    private static final String API_KEY = "";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public WeatherClient() {
        System.out.println("WeatherClient loaded");
    }

    public String getWeatherDataURL(String location) {
        return API_URL + location + "&appid=" + API_KEY + "&units=metric";
    }

}
