package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * This class is a custom javafx component that represents the stock interface
 */
public class StockInterface extends VBox {
    private ImageView itemImageView;
    private Label itemNameLabel;
    private Button addButton;

    /**
     * Initializes the view
     */
    public StockInterface() {
        setView();
    }

    /**
     * prepares the gui of  this class
     */
    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/StockInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemImageView = (ImageView) this.lookup("#itemImageView");
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            addButton = (Button) this.lookup("#addButton");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method returns the item image view
     * @return item image view
     */
    public ImageView getItemImageView() {
        return itemImageView;
    }

    /**
     * This method returns the item name label
     * @return item name label
     */
    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    /**
     This method returns the add button
     * @return add button
     */
    public Button getAddButton() {
        return addButton;
    }
}
