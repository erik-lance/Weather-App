module com.weatherapp.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;
    requires io.github.cdimascio.dotenv.java;

    opens com.weatherapp.weatherapp to javafx.fxml;
    exports com.weatherapp.weatherapp;
}