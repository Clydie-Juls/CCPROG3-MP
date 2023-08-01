package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.menu.NonCustomizable;
import com.vnd.mco2restructure.model.StockEditInfo;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * Controller class for editing stocks.
 */
public class StockEditController {
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML private VBox infoLayout;
    @FXML private Label itemName;

    /**
     * Updates the view for StockEditController.
     */
    public void updateView() {
        infoLayout.getChildren().clear();
        itemName.setText(programData.getCurrentSlotItem().name().toLowerCase().replaceAll("_", " "));
        for (Map.Entry<NonCustomizable, StockEditInfo.ItemEditInfo[]> entry : programData.getCurrentStockEditInfo().getItemAmount().entrySet()) {

            if(entry.getKey() instanceof CustomizableItemEnum.ItemType itemType) {
                Label ingredientTypesName = new Label(itemType.getItemTypeName());
                infoLayout.getChildren().add(ingredientTypesName);
                for (int i = 0; i < itemType.getItems().length; i++) {
                    NonCustomizable ingredientItem = itemType.getItems()[i];
                    if (ingredientItem instanceof IndependentItemEnum independentItemEnum) {
                        createItemInfo(independentItemEnum.toString(), entry.getValue()[i]);
                    } else if (ingredientItem instanceof DependentItemEnum dependentItemEnum) {
                        createItemInfo(dependentItemEnum.toString(), entry.getValue()[i]);
                    }
                }
            }
        }
    }

    /**
     * Creates an item information display in the Stock Edit view.
     *
     * @param s             The name of the item as a string.
     * @param itemEditInfo  The ItemEditInfo object containing information about the item.
     */
    private void createItemInfo(String s, StockEditInfo.ItemEditInfo itemEditInfo) {
        HBox hBox = new HBox();
        String name = s.toLowerCase().replace('_', ' ');

        NumberField numberField = new NumberField();
        numberField.getTextField().setText(itemEditInfo.getAmount().toString());
        numberField.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            // Try to parse the text into an integer
            try {
                int value = Integer.parseInt(newValue);
                itemEditInfo.setAmount(value);
            } catch (NumberFormatException ignored) {

            }
        });

        CheckBox itemCheckBox = new CheckBox(name);
        numberField.setContentDisable(!itemEditInfo.isChecked());
        itemCheckBox.setSelected(itemEditInfo.isChecked());
        itemCheckBox.setOnAction(event -> {
            itemEditInfo.setChecked(itemCheckBox.isSelected());
            numberField.setContentDisable(!itemCheckBox.isSelected());
        });

        hBox.getChildren().addAll(itemCheckBox, numberField);
        infoLayout.getChildren().add(hBox);
    }


    /**
     * Method to exit from the StockEditController view.
     */
    @FXML
    private void exit() {
        windowManager.gotoStockManagerView(false);
    }

    /**
     * Sets the WindowManager instance for this controller.
     *
     * @param windowManager The WindowManager instance to set.
     */
    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    /**
     * Sets the ProgramData instance for this controller.
     *
     * @param programData The ProgramData instance to set.
     */
    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

}
