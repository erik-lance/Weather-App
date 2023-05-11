package com.weatherapp.weatherapp;

public record WeatherData(String city, String country, String description, String temperature, String humidity,
                          String pressure, String windSpeed, String windDirection) {
    public WeatherData(String city, String country, String description,
                       String temperature, String humidity, String pressure,
                       String windSpeed, String windDirection) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return city + ", " + country + "\n" +
                description + "\n" +
                temperature + "\n" +
                humidity + "\n" +
                pressure + "\n" +
                windSpeed + "\n" +
                windDirection;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
