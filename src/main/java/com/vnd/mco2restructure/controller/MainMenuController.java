package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.Navbar;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainMenuController {
    private WindowManager windowManager;
    @FXML private Navbar navbar;
    @FXML private BorderPane mainContent;

    private void setNavbarWindowManager(WindowManager windowManager) {
        navbar.getNavbarController().setWindowManager(windowManager);
    }
    public BorderPane getMainContent() {
        return mainContent;
    }

    public void setWindowManager(WindowManager windowManager) {
        System.out.println(windowManager == null);
        this.windowManager = windowManager;
        setNavbarWindowManager(windowManager);
    }
}
