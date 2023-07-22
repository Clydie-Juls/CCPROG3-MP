package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController {

    private WindowManager windowManager;

    @FXML
    private void gotoVndFeaturesView() {
        Platform.runLater(() -> windowManager.gotoVndFeaturesView());
    }

    @FXML
    private void gotoMntFeaturesView() {
        Platform.runLater(() -> windowManager.gotoMainMenuView());
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
}
