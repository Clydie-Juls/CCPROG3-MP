package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemEditInfoInterface;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.menu.NonCustomizable;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.vendingmachine.RegularVendingMachine;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
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
                        createItemInfo(independentItemEnum.toString(), entry.getValue()[i],
                                independentItemEnum.getPrice(), independentItemEnum.getCalories(), ((IndependentItemEnum) ingredientItem).enumToItem());
                    } else if (ingredientItem instanceof DependentItemEnum dependentItemEnum) {
                        createItemInfo(dependentItemEnum.toString(), entry.getValue()[i], dependentItemEnum.getPrice(),
                                dependentItemEnum.getCalories(), ((DependentItemEnum) ingredientItem).enumToItem());
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
    private void createItemInfo(String s, StockEditInfo.ItemEditInfo itemEditInfo, int price, int calories,
                                NonCustomizableItem ingredientItem) {
        ItemEditInfoInterface itemEditInfoInterface = new ItemEditInfoInterface();
        String name = s.toLowerCase().replace('_', ' ');
        itemEditInfoInterface.getNumberField().getTextField().setText(itemEditInfo.getAmount().toString());
        itemEditInfoInterface.getItemPriceLabel().setText("Price: " + price);
        itemEditInfoInterface.getItemCaloriesLabel().setText("Calories: " + calories);
         if(programData.getCurrentVendingMachine() instanceof SpecialVendingMachine specialVendingMachine) {
             int amount = specialVendingMachine.getItemStorage().containsKey(ingredientItem) ?
                     specialVendingMachine.getItemStorage().get(ingredientItem).size() : 0;
            itemEditInfoInterface.getCurrentItemAmount().setText("Amount: " + amount);
        }

        itemEditInfoInterface.getNumberField().getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            // Try to parse the text into an integer
            try {
                int value = Integer.parseInt(newValue);
                itemEditInfo.setAmount(value);
            } catch (NumberFormatException ignored) {

            }
        });
        itemEditInfoInterface.getItemCheckbox().setText(name);
        itemEditInfoInterface.getNumberField().setContentDisable(!itemEditInfo.isChecked());

        itemEditInfoInterface.getItemCheckbox().setOnAction(event -> {
            itemEditInfo.setChecked(itemEditInfoInterface.getItemCheckbox().isSelected());
                    itemEditInfoInterface.getNumberField().setContentDisable(!itemEditInfoInterface.getItemCheckbox().isSelected());
        });

        infoLayout.getChildren().add(itemEditInfoInterface);
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
