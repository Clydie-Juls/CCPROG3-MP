package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class NumberField extends HBox {

    private FXMLLoader loader;
    public NumberField() {
        setView();
    }

    private void setView() {
        loader = new FXMLLoader(HelloApplication.class.getResource("components/NumberField.fxml"));
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FXMLLoader getLoader() {
        return loader;
    }
}
