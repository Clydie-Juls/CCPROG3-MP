package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SlotInterface extends BorderPane {

    private Label itemTypeLabel;
    private Label itemNameLabel;
    private Label amountLabel;

    private Button changeButton;
    public SlotInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/SlotInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            changeButton = (Button) this.lookup("#changeButton");
            itemTypeLabel = (Label)this.lookup("#itemTypeLabel");
            itemNameLabel = (Label)this.lookup("#itemNameLabel");
            amountLabel = (Label)this.lookup("#amountLabel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public Label getItemTypeLabel() {
        return itemTypeLabel;
    }

    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    public Label getAmountLabel() {
        return amountLabel;
    }
}
