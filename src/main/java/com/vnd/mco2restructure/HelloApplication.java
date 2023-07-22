package com.vnd.mco2restructure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        WindowManager windowManager = new WindowManager(stage);
        windowManager.gotoHomeView();

        //Stage setup
        stage.setTitle("Vending Machine Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}