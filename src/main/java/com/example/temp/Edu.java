package com.example.temp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Edu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Edu.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("EDU");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        this.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
