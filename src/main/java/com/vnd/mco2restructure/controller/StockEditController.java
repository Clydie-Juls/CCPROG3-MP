package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.model.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.ItemEditInfoInterface;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.StockEditInfo;
import com.vnd.mco2restructure.model.items.NonCustomizableItem;
import com.vnd.mco2restructure.model.vendingmachine.SpecialVendingMachine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class StockEditController {
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML private VBox infoLayout;
    @FXML private Label itemName;

    public void updateView() {
        infoLayout.getChildren().clear();
        itemName.setText(programData.getCurrentSlotItem().name().toLowerCase().replaceAll("_", " "));
        for (Map.Entry<NonCustomizable, StockEditInfo.ItemEditInfo[]> entry : programData.getCurrentStockEditInfo().getItemAmount().entrySet()) {

            if(entry.getKey() instanceof CustomizableItemEnum.ItemType itemType) {
                Label ingredientTypesName = new Label(itemType.getItemTypeName());
                ingredientTypesName.getStyleClass().add("type-label");
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

    @FXML
    private void exit() {
        windowManager.gotoStockManagerView(false);
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

}
