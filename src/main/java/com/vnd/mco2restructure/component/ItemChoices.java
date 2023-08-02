package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a custom VBox component that displays item choices.
 */
public class ItemChoices extends VBox {

    private Label itemTypeLabel;
    private FlowPane itemChoicesLayout;

    /**
     * Constructs a new ItemChoices component and sets its view.
     */
    public ItemChoices() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/ItemChoices.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemTypeLabel = (Label) this.lookup("#itemTypeLabel");
            itemChoicesLayout = (FlowPane) this.lookup("#itemChoicesLayout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
     * Get the layout displaying the item choices.
     *
     * @return The itemChoicesLayout displaying the item choices.
     */
    public FlowPane getItemChoicesLayout() {
        return itemChoicesLayout;
    }
}
