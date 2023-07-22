package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import com.vnd.mco2restructure.controller.NavbarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Navbar extends HBox {

    private NavbarController navbarController;
    public Navbar() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/Navbar.fxml"));
        view.setRoot(this);

        try {
            view.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        navbarController = view.getController();
    }

    public NavbarController getNavbarController() {
        return navbarController;
    }
}
