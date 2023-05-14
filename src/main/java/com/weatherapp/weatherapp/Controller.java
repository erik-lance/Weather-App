package com.weatherapp.weatherapp;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private static WeatherClient weatherClient;

    @FXML
    private Label welcomeText;

    @FXML
    private Label clockText;

    @FXML
    private Label weatherText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void updateWeatherDetails(WeatherData weatherData) {
        weatherText.setText(weatherData.toString());
    }

    public Controller() {
        System.out.println("Controller loaded");
    }

    public void initialize() {
        startClockUpdate();
        weatherClient = new WeatherClient();
        WeatherData data = weatherClient.getWeatherData("Mandaluyong,PH");
        System.out.println("Collected weather data: ");
        System.out.println(data.toString());


        updateWeatherDetails(data);
    }

    private void startClockUpdate() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) { updateTime(); }
        };
        timer.start();
    }

    private void updateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(new Date());
        clockText.setText(time);
    }


}