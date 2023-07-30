package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ItemInterface extends VBox {

    private Label itemNameLabel;
    private ImageView itemImageView;

    public ItemInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/ItemInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemNameLabel = (Label) this.lookup("#itemNameLabel");
            itemImageView = (ImageView) this.lookup("#itemImageView");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Label getItemNameLabel() {
        return itemNameLabel;
    }

    public ImageView getItemImageView() {
        return itemImageView;
    }
}
