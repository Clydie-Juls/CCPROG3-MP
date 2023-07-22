package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.fxml.FXML;

public class RestockController {
    private WindowManager windowManager;
    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
