package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import com.vnd.mco2restructure.controller.NavbarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Represents a custom HBox component for the navigation bar.
 */
public class Navbar extends HBox {

    private NavbarController navbarController;

    /**
     * Constructs a new Navbar component and sets its view.
     */
    public Navbar() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/Navbar.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        navbarController = view.getController();
    }

    /**
     * Get the controller associated with the Navbar.
     *
     * @return The NavbarController for handling Navbar interactions.
     */
    public NavbarController getNavbarController() {
        return navbarController;
    }
}
