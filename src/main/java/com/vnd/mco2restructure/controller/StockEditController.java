package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.NumberField;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.StockEditInfo;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

//        for (CustomizableItemEnum.ItemType ingredient : programData.getCurrentSlotItem().getIngredients()) {
//            Label ingredientTypesName = new Label(ingredient.getItemTypeName());
//            infoLayout.getChildren().add(ingredientTypesName);
//            for (NonCustomizable ingredientItem : ingredient.getItems()) {
//                if (ingredientItem instanceof IndependentItemEnum independentItemEnum) {
//                    createItemInfo(independentItemEnum.toString());
//                } else if (ingredientItem instanceof DependentItemEnum dependentItemEnum) {
//                    createItemInfo(dependentItemEnum.toString());
//                }
//            }
//        }
    }

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
