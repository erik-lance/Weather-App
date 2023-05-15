package com.weatherapp.weatherapp;

public record WeatherData(String city, String country, String description, String temperature, String humidity,
                          String pressure, String windSpeed, String windDirection) {

    @Override
    public String toString() {
        String template = """
                City: %s
                Country: %s
                Description: %s
                Temperature: %s
                Humidity: %s
                Pressure: %s
                Wind speed: %s
                Wind direction: %s
                """;
        String result = String.format(template,
                city, country, description,
                temperature, humidity, pressure,
                windSpeed, windDirection);
        return result;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
