package com.vnd.mco2restructure.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the NumberField component.
 */
public class NumberFieldController implements Initializable {

    @FXML private TextField textField;

    /**
     * Increments the value in the NumberField by 1.
     */
    @FXML
    private void incrementValue() {
        int currentValue = Integer.parseInt(textField.getText());
        setValue(currentValue + 1);
    }

    /**
     * Decrements the value in the NumberField by 1.
     */
    @FXML
    private void decrementValue() {
        int currentValue = Integer.parseInt(textField.getText());
        setValue(currentValue - 1);
    }

    /**
     * Gets the current value from the NumberField.
     *
     * @return The current value in the NumberField.
     */
    public int getValue() {
        return Integer.parseInt(textField.getText());
    }

    /**
     * Sets the value of the NumberField.
     *
     * @param value The value to set in the NumberField.
     */
    public void setValue(int value) {
        textField.setText(Integer.toString(value));
    }

    /**
     * Initializes the NumberField with default values and preferences.
     *
     * @param location  The URL location used to resolve relative paths.
     * @param resources The resource bundle containing localized objects.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.setText("0"); // Default value for the text field
        textField.setPrefColumnCount(5); // Set preferred width for the text field
    }
}
