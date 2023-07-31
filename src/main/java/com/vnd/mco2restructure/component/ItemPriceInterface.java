package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a custom VBox component that displays item price information.
 */
public class ItemPriceInterface extends VBox {
    private Label itemNameLabel;
    private Label itemPriceLabel;
    private NumberField itemPriceNumberField;
    private Button changePriceButton;

    /**
     * Constructs a new ItemPriceInterface component and sets its view.
     */
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the label displaying the item name.
     *
     * @return The itemNameLabel displaying the item name.
     */
    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    /**
     * Get the label displaying the item price.
     *
     * @return The itemPriceLabel displaying the item price.
     */
    public Label getItemPriceLabel() {
        return itemPriceLabel;
    }

    /**
     * Get the NumberField for item price input.
     *
     * @return The itemPriceNumberField for item price input.
     */
    public NumberField getItemPriceNumberField() {
        return itemPriceNumberField;
    }

    /**
     * Get the button to change the item price.
     *
     * @return The changePriceButton to change the item price.
     */
    public Button getChangePriceButton() {
        return changePriceButton;
    }
}
