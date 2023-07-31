package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ItemPriceInterface extends VBox {
    private Label itemNameLabel;
    private Label itemPriceLabel;
    private NumberField itemPriceNumberField;
    private Button changePriceButton;

    private ImageView itemImageView;
    public ItemPriceInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/ItemPriceInterface.fxml"));
        view.setRoot(this);
        try {
            view.load();
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            itemPriceLabel = (Label) this.lookup("#itemPriceLabel");
            itemPriceNumberField = (NumberField) this.lookup("#itemPriceNumberField");
            changePriceButton = (Button) this.lookup("#changePriceButton");
            itemImageView = (ImageView) this.lookup("#itemImageView");
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

    public NumberField getItemPriceNumberField() {
        return itemPriceNumberField;
    }

    public Button getChangePriceButton() {
        return changePriceButton;
    }

    public ImageView getItemImageView() {
        return itemImageView;
    }
}
