package com.vnd.mco2restructure.controller;

import com.vnd.mco2restructure.ProgramData;
import com.vnd.mco2restructure.WindowManager;
import com.vnd.mco2restructure.component.SlotInterface;
import com.vnd.mco2restructure.menu.*;
import com.vnd.mco2restructure.model.Stocks;
import com.vnd.mco2restructure.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class StockManagerController {
    private Stocks stocks;
    private WindowManager windowManager;
    private ProgramData programData;
    @FXML
    private FlowPane menuLayout;

    public void updateView() {
        if (stocks.getItemEnums().size() == 0) {
            resetStockEnums();
        }
        menuLayout.getChildren().clear();
        for (int i = 0; i < stocks.getItemEnums().size(); i++) {
            SlotInterface slotInterface = new SlotInterface();
            int finalI = i;
            if (stocks.getItemEnums().get(finalI) != null) {
                slotInterface.getAmountLabel().setText("Amount : " + 0);
                if (stocks.getItemEnums().get(finalI) instanceof CustomizableItemEnum customizableItemEnum) {
                    slotInterface.getItemNameLabel().setText(customizableItemEnum.name().toLowerCase().
                            replaceAll("_", " "));
                    slotInterface.getItemTypeLabel().setText("Item type: Customizable Item");

                    VBox vBox = new VBox();
                    for (CustomizableItemEnum.ItemType ingredient : customizableItemEnum.getIngredients()) {
                        Label ingredientTypesName = new Label(ingredient.getItemTypeName());
                        vBox.getChildren().add(ingredientTypesName);
                        for (NonCustomizable ingredientItem : ingredient.getItems()) {
                            if (ingredientItem instanceof IndependentItemEnum independentItemEnum) {
                                String name = independentItemEnum.toString().toLowerCase().replace('_', ' ');
                                CheckBox itemCheckBox = new CheckBox(name);
                                itemCheckBox.setSelected(true);
                                vBox.getChildren().add(itemCheckBox);
                            } else if (ingredientItem instanceof DependentItemEnum dependentItemEnum) {
                                String name = dependentItemEnum.toString().toLowerCase().replace('_', ' ');
                                CheckBox itemCheckBox = new CheckBox(name);
                                itemCheckBox.setSelected(true);
                                vBox.getChildren().add(itemCheckBox);
                            }
                        }
                    }

                    slotInterface.setCenter(vBox);

                } else if (stocks.getItemEnums().get(finalI) instanceof IndependentItemEnum independentItemEnum) {
                    slotInterface.getItemNameLabel().setText(independentItemEnum.name().toLowerCase().
                            replaceAll("_", " "));
                    slotInterface.getItemTypeLabel().setText("Item type: Independent Item");
                }
            }
            slotInterface.getChangeButton().setOnAction(event -> windowManager.gotoStockView(finalI));
            menuLayout.getChildren().add(slotInterface);
        }
    }

    @FXML
    private void exit() {
        windowManager.gotoMntFeaturesView();
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public void setSlotItemEnum(ItemEnum<? extends Item> itemEnum, int index) {
        stocks.getItemEnums().set(index, itemEnum);
    }

    public void setProgramData(ProgramData programData) {
        this.programData = programData;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }

    public void resetStockEnums() {
        stocks.setItemEnums(programData.getCurrentVendingMachine().getSlots().length);
    }
}
