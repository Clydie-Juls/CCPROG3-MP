package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ItemChoices extends VBox {

    private Label itemTypeLabel;
    private FlowPane itemChoicesLayout;

    public ItemChoices() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/ItemChoices.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemTypeLabel = (Label) this.lookup("#itemTypeLabel");
            itemChoicesLayout = (FlowPane) this.lookup("#itemChoicesLayout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Label getItemTypeLabel() {
        return itemTypeLabel;
    }

    public FlowPane getItemChoicesLayout() {
        return itemChoicesLayout;
    }
}
