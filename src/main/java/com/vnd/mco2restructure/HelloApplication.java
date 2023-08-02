package com.vnd.mco2restructure;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Application class is the main entry point of the vending machine simulator application.
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        WindowManager windowManager = new WindowManager(stage);
        windowManager.gotoHomeView();

        // Stage setup
        stage.setTitle("Vending Machine Simulator");
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }
}
