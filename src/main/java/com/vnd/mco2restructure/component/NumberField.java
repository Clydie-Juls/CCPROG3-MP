package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class NumberField extends HBox {

    private FXMLLoader loader;
    private TextField textField;
    private Button minusButton;
    private Button addButton;
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
        textField =(TextField)this.lookup("#textField");
        minusButton =(Button) this.lookup("#minusButton");
        addButton =(Button) this.lookup("#addButton");
    }

    public void setContentDisable(boolean isDisable) {
        textField.setDisable(isDisable);
        minusButton.setDisable(isDisable);
        addButton.setDisable(isDisable);
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public TextField getTextField() {
        return textField;
    }
}
