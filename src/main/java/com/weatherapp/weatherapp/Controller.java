package com.weatherapp.weatherapp;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private static WeatherClient weatherClient;


    @FXML
    private Label clockText;

    @FXML
    private Label weatherText;

    @FXML
    private TextField apiTextField;

    @FXML
    private Button weatherButton;

    @FXML
    protected void onGetWeatherButtonClick() {
        WeatherData data = weatherClient.getWeatherData("Mandaluyong,PH");
        System.out.println("Collected weather data: ");
        System.out.println(data.toString());
        updateWeatherDetails(data);
    }

    @FXML
    protected void onUpdateAPIKeyClick() {
        String key = apiTextField.getText();
        updateKey(key);
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
        Dotenv dotenv = Dotenv.load();
        String API_KEY = dotenv.get("API_KEY");
        if (API_KEY == null) {
            System.out.println("API_KEY not found in .env file");
        } else {
            System.out.println("API_KEY found in .env file: "+ API_KEY);
            updateKey(API_KEY);
        }
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

    private void updateKey(String key) {
        weatherClient.setAPIKey(key);
        weatherButton.setDisable(false);
        apiTextField.setText(key);
    }


}