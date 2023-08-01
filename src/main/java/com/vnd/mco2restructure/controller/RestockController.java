package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.fxml.FXML;

/**
 * Controller class for restocking.
 */
public class RestockController {
    private WindowManager windowManager;

    /**
     * Method to exit from the restock view.
     */
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
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
