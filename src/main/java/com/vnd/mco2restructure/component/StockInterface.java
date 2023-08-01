package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StockInterface extends VBox {
    private ImageView itemImageView;
    private Label itemNameLabel;
    private Button addButton;

    public StockInterface() {
        setView();
    }

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

    public ImageView getItemImageView() {
        return itemImageView;
    }

    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    public Button getAddButton() {
        return addButton;
    }
}
