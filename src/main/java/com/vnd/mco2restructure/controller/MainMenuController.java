package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.Navbar;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * Controller class for the main menu view.
 */
public class MainMenuController {
    private WindowManager windowManager;
    @FXML private Navbar navbar;
    @FXML private BorderPane mainContent;

    private void setNavbarWindowManager(WindowManager windowManager) {
        navbar.getNavbarController().setWindowManager(windowManager);
    }

    /**
     * Gets the main content area of the view.
     *
     * @return The BorderPane representing the main content area.
     */
    public BorderPane getMainContent() {
        return mainContent;
    }

    /**
     * Sets the WindowManager for this controller.
     *
     * @param windowManager The WindowManager instance.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
        setNavbarWindowManager(windowManager);
    }
}
