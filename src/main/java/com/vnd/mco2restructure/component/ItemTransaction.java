package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ItemTransaction extends VBox {
    private Label itemNameLabel;
    private Label itemPriceLabel;
    private Label itemCaloriesLabel;

    public ItemTransaction() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/ItemTransaction.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            itemPriceLabel = (Label) this.lookup("#itemPriceLabel");
            itemCaloriesLabel = (Label) this.lookup("#itemCaloriesLabel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    public Label getItemPriceLabel() {
        return itemPriceLabel;
    }

    public Label getItemCaloriesLabel() {
        return itemCaloriesLabel;
    }
}
