package com.vnd.mco2restructure.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NumberFieldController implements Initializable {

    @FXML private TextField textField;

    @FXML
    private void incrementValue() {
        int currentValue = Integer.parseInt(textField.getText());
        setValue(currentValue + 1);
    }

    @FXML
    private void decrementValue() {
        int currentValue = Integer.parseInt(textField.getText());
        setValue(currentValue - 1);
    }

    public int getValue() {
        return Integer.parseInt(textField.getText());
    }

    public void setValue(int value) {
        textField.setText(Integer.toString(value));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.setText("1"); // Default value for the text field
        textField.setPrefColumnCount(5); // Set preferred width for the text field
    }
}
