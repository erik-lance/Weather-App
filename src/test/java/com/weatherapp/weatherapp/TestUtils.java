package com.weatherapp.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestUtils {
    public static JSONObject MandaluyongWeatherJSON() {
        JSONObject data = new JSONObject();
        data.put("name", "Mandaluyong City");

        data.put("sys", new JSONObject());
        ((JSONObject) data.get("sys")).put("country", "PH");

        data.put("weather", new JSONArray());
        JSONObject description = new JSONObject();
        description.put("description", "Cloudy");
        ((JSONArray) data.get("weather")).add(description);

        data.put("main", new JSONObject());
        ((JSONObject) data.get("main")).put("temp", "30");
        ((JSONObject) data.get("main")).put("humidity", "50");
        ((JSONObject) data.get("main")).put("pressure", "100");

        data.put("wind", new JSONObject());
        ((JSONObject) data.get("wind")).put("speed", "10");
        ((JSONObject) data.get("wind")).put("deg", "90");

        return data;
    }
}
