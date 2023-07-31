package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the navigation bar.
 */
public class NavbarController {

    private WindowManager windowManager;

    /**
     * Method to navigate to the Vending Features View.
     */
    @FXML
    private void gotoVndFeaturesView() {
        Platform.runLater(() -> windowManager.gotoVndFeaturesView());
    }

    /**
     * Method to navigate to the Maintenance Features View.
     */
    @FXML
    private void gotoMntFeaturesView() {
        Platform.runLater(() -> windowManager.gotoCurrentMntLayout());
    }

    /**
     * Sets the WindowManager instance for this controller.
     *
     * @param windowManager The WindowManager instance to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
