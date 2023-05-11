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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public Controller() {
        System.out.println("Controller loaded");
    }

    public void initialize() {
        startClockUpdate();
        weatherClient = new WeatherClient();
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