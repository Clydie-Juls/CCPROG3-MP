package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.fxml.FXML;

/**
 * Controller class for the display transactions view.
 */
public class DisplayTransactionsController {
    private WindowManager windowManager;

    /**
     * Handles the action when the "Exit" button is clicked.
     * Goes back to the main features view.
     */
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
