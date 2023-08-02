package com.vnd.mco2restructure.component;

import com.vnd.mco2restructure.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * This class is a custom javafx component representing item infos during stock edit
 */
public class ItemEditInfoInterface extends VBox {
    private CheckBox itemCheckbox;
    private NumberField numberField;
    private Label itemPriceLabel;
    private Label itemCaloriesLabel;
    private Label currentItemAmount;

    /**
     * Initializes the view
     */
    public ItemEditInfoInterface() {
        setView();
    }

    /**
     * prepares the gui of  this class
     */
    private void setView() {
        FXMLLoader view = new FXMLLoader(Application.class.getResource("components/ItemEditInfoInterface.fxml"));
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

    /**
     * This method returns the item checkbox
     * @return item checkbox
     */
    public CheckBox getItemCheckbox() {
        return itemCheckbox;
    }

    /**
     * This method returns the item checkbox
     * @return item checkbox
     */
    public NumberField getNumberField() {
        return numberField;
    }

    /**
     * This method returns the item price label
     * @return item price label
     */
    public Label getItemPriceLabel() {
        return itemPriceLabel;
    }

    /**
     * This method returns the item calories label
     * @return item checkbox
     */
    public Label getItemCaloriesLabel() {
        return itemCaloriesLabel;
    }

    /**
     * This method returns the current item amount label
     * @return current item amount label
     */
    public Label getCurrentItemAmount() {
        return currentItemAmount;
    }
}
