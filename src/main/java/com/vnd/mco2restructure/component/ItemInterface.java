package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a custom VBox component that displays item information.
 */
public class ItemInterface extends VBox {

    private Label itemNameLabel;
    private ImageView itemImageView;

    /**
     * Constructs a new ItemInterface component and sets its view.
     */
    public ItemInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/ItemInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            itemImageView = (ImageView) this.lookup("#itemImageView");
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
     * Get the ImageView displaying the item image.
     *
     * @return The itemImageView displaying the item image.
     */
    public ImageView getItemImageView() {
        return itemImageView;
    }
}
