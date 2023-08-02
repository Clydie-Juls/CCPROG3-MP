package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a custom BorderPane component that displays slot information.
 */
public class SlotInterface extends BorderPane {

    private Label itemTypeLabel;
    private Label itemNameLabel;
    private Label amountLabel;
    private Button changeButton;
    private ImageView itemImageView;
    private VBox bottomLayout;

    /**
     * Constructs a new SlotInterface component and sets its view.
     */
    public SlotInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/SlotInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            changeButton = (Button) this.lookup("#changeButton");
            itemTypeLabel = (Label) this.lookup("#itemTypeLabel");
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            amountLabel = (Label) this.lookup("#amountLabel");
            itemImageView = (ImageView) this.lookup("#itemImageView");
            bottomLayout = (VBox) this.lookup("#bottomLayout");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the button to change the item.
     *
     * @return The changeButton to change the item.
     */
    public Button getChangeButton() {
        return changeButton;
    }

    /**
     * Get the label displaying the item type.
     *
     * @return The itemTypeLabel displaying the item type.
     */
    public Label getItemTypeLabel() {
        return itemTypeLabel;
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
     * Get the label displaying the item amount.
     *
     * @return The amountLabel displaying the item amount.
     */
    public Label getAmountLabel() {
        return amountLabel;
    }

    /**
     * Get the VBox layout for the bottom part of the slot.
     *
     * @return The bottomLayout for the bottom part of the slot.
     */
    public VBox getBottomLayout() {
        return bottomLayout;
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
