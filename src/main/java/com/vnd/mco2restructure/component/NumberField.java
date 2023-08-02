package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Represents a custom HBox component containing a numeric text field and buttons to modify its value.
 */
public class NumberField extends HBox {

    private FXMLLoader loader;
    private TextField textField;
    private Button minusButton;
    private Button addButton;

    /**
     * Constructs a new NumberField component and sets its view.
     */
    public NumberField() {
        setView();
    }

    private void setView() {
        loader = new FXMLLoader(Application.class.getResource("components/NumberField.fxml"));
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        textField = (TextField) this.lookup("#textField");
        minusButton = (Button) this.lookup("#minusButton");
        addButton = (Button) this.lookup("#addButton");
    }

    /**
     * Set whether the content of the NumberField is disabled or not.
     *
     * @param isDisable true to disable the content, false otherwise.
     */
    public void setContentDisable(boolean isDisable) {
        textField.setDisable(isDisable);
        minusButton.setDisable(isDisable);
        addButton.setDisable(isDisable);
    }

    /**
     * Get the FXMLLoader used for loading the NumberField component.
     *
     * @return The FXMLLoader used for this NumberField.
     */
    public FXMLLoader getLoader() {
        return loader;
    }

    /**
     * Get the TextField used for numeric input.
     *
     * @return The TextField used for numeric input.
     */
    public TextField getTextField() {
        return textField;
    }
}
