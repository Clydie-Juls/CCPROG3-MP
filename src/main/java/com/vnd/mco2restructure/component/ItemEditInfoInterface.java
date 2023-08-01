package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ItemEditInfoInterface extends VBox {
    private CheckBox itemCheckbox;
    private NumberField numberField;
    private Label itemPriceLabel;
    private Label itemCaloriesLabel;
    private Label currentItemAmount;

    public ItemEditInfoInterface() {
        setView();
    }

    private void setView() {
        FXMLLoader view = new FXMLLoader(HelloApplication.class.getResource("components/ItemEditInfoInterface.fxml"));
        view.setRoot(this);

        try {
            view.load();
            itemCheckbox = (CheckBox) this.lookup("#itemCheckbox");
            numberField = (NumberField) this.lookup("#numberField");
            itemPriceLabel = (Label) this.lookup("#itemPriceLabel");
            itemCaloriesLabel = (Label) this.lookup("#itemCaloriesLabel");
            currentItemAmount = (Label) this.lookup("#currentItemAmount");
            itemCheckbox.setSelected(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public CheckBox getItemCheckbox() {
        return itemCheckbox;
    }

    public NumberField getNumberField() {
        return numberField;
    }

    public Label getItemPriceLabel() {
        return itemPriceLabel;
    }

    public Label getItemCaloriesLabel() {
        return itemCaloriesLabel;
    }

    public Label getCurrentItemAmount() {
        return currentItemAmount;
    }
}
