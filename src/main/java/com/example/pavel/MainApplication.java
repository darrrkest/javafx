package com.example.pavel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("WelcomeScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Man-bat Java Application");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("/icons/bat_icon.png"))));
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
